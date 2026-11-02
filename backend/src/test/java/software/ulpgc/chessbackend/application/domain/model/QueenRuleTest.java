package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.BlackPawn;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhiteQueen;
import static software.ulpgc.chessbackend.application.domain.model.Square.at;

class QueenRuleTest {
    private final MoveRule rule = new QueenRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteQueen));

    @Test
    void queen_moves_straight_and_diagonally() {
        for (String to : List.of("d8", "a4", "h4", "d1", "a1", "h8", "a7", "g1"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void queen_does_not_move_in_an_L_shape() {
        for (String to : List.of("e6", "f5", "c2", "b3"))
            assertFalse(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void queen_cannot_jump_over_pieces() {
        Board blocked = new Board(Map.of(at("d4"), WhiteQueen, at("d6"), BlackPawn, at("f6"), BlackPawn));
        assertFalse(rule.allows(blocked, Move.of("d4", "d8")));
        assertFalse(rule.allows(blocked, Move.of("d4", "h8")));
    }
}
