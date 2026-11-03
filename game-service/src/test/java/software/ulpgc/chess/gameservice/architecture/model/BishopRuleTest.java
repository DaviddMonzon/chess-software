package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.BlackPawn;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.WhiteBishop;
import static software.ulpgc.chess.gameservice.architecture.model.Square.at;

class BishopRuleTest {
    private final MoveRule rule = new BishopRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteBishop));

    @Test
    void bishop_moves_diagonally() {
        for (String to : List.of("a1", "h8", "a7", "g1", "e5", "c3"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void bishop_does_not_move_straight_or_in_an_L_shape() {
        for (String to : List.of("d5", "d8", "a4", "e6", "f5"))
            assertFalse(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void bishop_cannot_jump_over_pieces() {
        Board blocked = new Board(Map.of(at("d4"), WhiteBishop, at("f6"), BlackPawn));
        assertTrue(rule.allows(blocked, Move.of("d4", "e5")));
        assertTrue(rule.allows(blocked, Move.of("d4", "f6")));
        assertFalse(rule.allows(blocked, Move.of("d4", "g7")));
        assertFalse(rule.allows(blocked, Move.of("d4", "h8")));
    }
}
