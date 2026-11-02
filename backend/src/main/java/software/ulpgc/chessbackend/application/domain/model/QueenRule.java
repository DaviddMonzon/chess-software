package software.ulpgc.chessbackend.application.domain.model;

public final class QueenRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        return false;
    }
}
