package software.ulpgc.chessbackend.application.domain.model;

public final class BishopRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        return false;
    }
}
