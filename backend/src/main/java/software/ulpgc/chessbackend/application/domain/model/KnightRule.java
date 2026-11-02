package software.ulpgc.chessbackend.application.domain.model;

public final class KnightRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        int files = Math.abs(move.fileDelta());
        int ranks = Math.abs(move.rankDelta());
        return (files == 1 && ranks == 2) || (files == 2 && ranks == 1);
    }
}
