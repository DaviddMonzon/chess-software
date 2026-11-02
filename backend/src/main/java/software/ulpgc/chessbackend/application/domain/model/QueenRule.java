package software.ulpgc.chessbackend.application.domain.model;

public final class QueenRule implements MoveRule {
    private final MoveRule rook = new RookRule();
    private final MoveRule bishop = new BishopRule();

    @Override
    public boolean allows(Board board, Move move) {
        return rook.allows(board, move) || bishop.allows(board, move);
    }
}
