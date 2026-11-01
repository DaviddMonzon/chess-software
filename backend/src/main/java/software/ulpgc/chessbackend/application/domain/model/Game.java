package software.ulpgc.chessbackend.application.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static Game replay(List<Move> moves) {
        Game game = start();
        for (Move move : moves) game = game.play(move);
        return game;
    }

    public Game play(Move move) {
        checkCanPlay(move);
        List<Move> history = new ArrayList<>(moves);
        history.add(move);
        return new Game(board.move(move.from(), move.to()), turn.opponent(), history, status, result);
    }

    public Game resign(Color color) {
        if (isOver()) throw new IllegalStateException("The game is already over");
        return new Game(board, turn, moves, GameStatus.Resigned, Result.winner(color.opponent()));
    }

    private void checkCanPlay(Move move) {
        if (isOver()) throw new IllegalStateException("The game is already over");
        Piece piece = board.pieces().get(move.from());
        if (piece == null)
            throw new IllegalArgumentException("No piece at " + move.from());
        if (piece.color() != turn)
            throw new IllegalArgumentException("It is " + turn + "'s turn");
        Piece target = board.pieces().get(move.to());
        if (target != null && target.color() == turn)
            throw new IllegalArgumentException("Cannot capture your own piece at " + move.to());
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
