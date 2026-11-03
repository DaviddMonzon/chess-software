package software.ulpgc.chess.gameservice.architecture.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class Game {
    private final Board board;
    private final Color turn;
    private final List<Move> moves;
    private final GameStatus status;
    private final Result result;

    private Game(Board board, Color turn, List<Move> moves, GameStatus status, Result result) {
        this.board = board;
        this.turn = turn;
        this.moves = List.copyOf(moves);
        this.status = status;
        this.result = result;
    }

    public static Game start() {
        return new Game(Board.initial(), Color.White, List.of(), GameStatus.InProgress, null);
    }

    static Game from(Board board, Color turn) {
        return new Game(board, turn, List.of(), GameStatus.InProgress, null);
    }

    public static Game replay(List<Move> moves) {
        Game game = start();
        for (Move move : moves) game = game.play(move);
        return game;
    }

    public Game play(Move move) {
        checkCanPlay(move);
        List<Move> history = new ArrayList<>(moves);
        history.add(move);
        return new Game(board.move(move.from(), move.to()), turn.opponent(), history, status, result)
                .withEnding();
    }

    public Game resign(Color color) {
        if (isOver()) throw new IllegalStateException("The game is already over");
        return new Game(board, turn, moves, GameStatus.Resigned, Result.winner(color.opponent()));
    }

    private void checkCanPlay(Move move) {
        if (isOver()) throw new IllegalStateException("The game is already over");
        violationOf(move).ifPresent(reason -> {
            throw new IllegalArgumentException(reason);
        });
    }

    private Optional<String> violationOf(Move move) {
        Piece piece = board.pieces().get(move.from());
        if (piece == null)
            return Optional.of("No piece at " + move.from());
        if (piece.color() != turn)
            return Optional.of("It is " + turn + "'s turn");
        Piece target = board.pieces().get(move.to());
        if (target != null && target.color() == turn)
            return Optional.of("Cannot capture your own piece at " + move.to());
        if (!MoveRule.of(piece).allows(board, move))
            return Optional.of("Illegal move for " + piece + ": " + move.from() + " -> " + move.to());
        if (board.move(move.from(), move.to()).isInCheck(turn))
            return Optional.of("Move leaves the " + turn + " king in check: " + move.from() + " -> " + move.to());
        return Optional.empty();
    }

    private Game withEnding() {
        if (hasLegalMoves()) return this;
        if (isInCheck()) return new Game(board, turn, moves, GameStatus.Checkmate, Result.winner(turn.opponent()));
        return new Game(board, turn, moves, GameStatus.Stalemate, Result.Draw);
    }

    private boolean hasLegalMoves() {
        return board.pieces().keySet().stream()
                .flatMap(from -> allSquares().filter(to -> !to.equals(from)).map(to -> new Move(from, to)))
                .anyMatch(move -> violationOf(move).isEmpty());
    }

    private static Stream<Square> allSquares() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Square(file, rank)));
    }

    public boolean isInCheck() {
        return board.isInCheck(turn);
    }

    public boolean isOver() {
        return status != GameStatus.InProgress;
    }

    public Board board() {
        return board;
    }

    public Color turn() {
        return turn;
    }

    public List<Move> moves() {
        return moves;
    }

    public GameStatus status() {
        return status;
    }

    public Optional<Result> result() {
        return Optional.ofNullable(result);
    }
}
