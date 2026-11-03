package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.BlackPawn;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.WhiteRook;
import static software.ulpgc.chess.gameservice.architecture.model.Square.at;

class RookRuleTest {
    private final MoveRule rule = new RookRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteRook));

    @Test
    void rook_moves_in_a_straight_line() {
        for (String to : List.of("d1", "d8", "a4", "h4", "d5", "e4"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void rook_does_not_move_diagonally_or_in_an_L_shape() {
        for (String to : List.of("e5", "a1", "h8", "e6", "f5"))
            assertFalse(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void rook_cannot_jump_over_pieces() {
        Board blocked = new Board(Map.of(at("d4"), WhiteRook, at("d6"), BlackPawn));
        assertTrue(rule.allows(blocked, Move.of("d4", "d5")));
        assertTrue(rule.allows(blocked, Move.of("d4", "d6")));
        assertFalse(rule.allows(blocked, Move.of("d4", "d7")));
        assertFalse(rule.allows(blocked, Move.of("d4", "d8")));
    }
}
