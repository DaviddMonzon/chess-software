package software.ulpgc.chess.gameservice.architecture.model;

import java.util.Optional;

public final class MoveValidator {
    private MoveValidator() {
    }

    public static Optional<String> violationOf(Board board, Color turn, Move move) {
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

    public static boolean hasLegalMoves(Board board, Color turn) {
        return board.pieces().keySet().stream()
                .flatMap(from -> Square.all().filter(to -> !to.equals(from)).map(to -> new Move(from, to)))
                .anyMatch(move -> violationOf(board, turn, move).isEmpty());
    }
}
