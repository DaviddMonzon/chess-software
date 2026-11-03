package software.ulpgc.chess.gameservice.application.domain.model;

public final class KingRule implements MoveRule {
    @Override
    public boolean allows(Board board, Move move) {
        return Math.abs(move.fileDelta()) <= 1 && Math.abs(move.rankDelta()) <= 1;
    }
}
