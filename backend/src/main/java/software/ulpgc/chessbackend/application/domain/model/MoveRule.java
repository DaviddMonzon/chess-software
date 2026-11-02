package software.ulpgc.chessbackend.application.domain.model;

public interface MoveRule {
    boolean allows(Board board, Move move);

    static MoveRule of(Piece piece) {
        if (piece.isPawn()) return new PawnRule();
        if (piece.isKnight()) return new KnightRule();
        if (piece.isBishop()) return new BishopRule();
        if (piece.isRook()) return new RookRule();
        if (piece.isQueen()) return new QueenRule();
        return new KingRule();
    }
}
