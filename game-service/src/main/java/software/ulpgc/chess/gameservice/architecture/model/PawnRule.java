package software.ulpgc.chess.gameservice.architecture.model;

public final class PawnRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        Color color = board.pieces().get(move.from()).color();
        int forward = color == Color.White ? 1 : -1;
        return advances(board, move, color, forward) || captures(board, move, forward);
    }

    private static boolean advances(Board board, Move move, Color color, int forward) {
        if (move.fileDelta() != 0 || !board.isEmpty(move.to())) return false;
        if (move.rankDelta() == forward) return true;
        return move.rankDelta() == 2 * forward
                && move.from().rank() == startRank(color)
                && board.isPathClear(move);
    }

    private static boolean captures(Board board, Move move, int forward) {
        return Math.abs(move.fileDelta()) == 1
                && move.rankDelta() == forward
                && !board.isEmpty(move.to());
    }

    private static Rank startRank(Color color) {
        return color == Color.White ? Rank.R2 : Rank.R7;
    }
}
