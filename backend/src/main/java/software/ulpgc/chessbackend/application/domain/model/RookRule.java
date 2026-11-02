package software.ulpgc.chessbackend.application.domain.model;

public final class RookRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        boolean straight = move.fileDelta() == 0 || move.rankDelta() == 0;
        return straight && board.isPathClear(move);
    }
}
