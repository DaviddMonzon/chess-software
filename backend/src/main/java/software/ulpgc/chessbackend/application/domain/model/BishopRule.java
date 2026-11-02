package software.ulpgc.chessbackend.application.domain.model;

public final class BishopRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        boolean diagonal = Math.abs(move.fileDelta()) == Math.abs(move.rankDelta());
        return diagonal && move.squaresBetween().stream().allMatch(board::isEmpty);
    }
}
