package software.ulpgc.chessbackend.application.domain.model;

public interface MoveRule {
    boolean allows(Board board, Move move);
}
