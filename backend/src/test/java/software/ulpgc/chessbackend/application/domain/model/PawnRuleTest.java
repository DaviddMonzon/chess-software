package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.BlackPawn;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhitePawn;
import static software.ulpgc.chessbackend.application.domain.model.Square.at;

class PawnRuleTest {
    private final MoveRule rule = new PawnRule();

    @Test
    void pawn_advances_one_square_forward() {
        Board board = new Board(Map.of(at("e2"), WhitePawn, at("e7"), BlackPawn));
        assertTrue(rule.allows(board, Move.of("e2", "e3")));
        assertTrue(rule.allows(board, Move.of("e7", "e6")));
    }
}
