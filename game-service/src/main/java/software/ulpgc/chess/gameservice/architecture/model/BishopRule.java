package software.ulpgc.chess.gameservice.architecture.model;

public final class BishopRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        boolean diagonal = Math.abs(move.fileDelta()) == Math.abs(move.rankDelta());
        return diagonal && board.isPathClear(move);
    }
}
