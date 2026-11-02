package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhiteBishop;
import static software.ulpgc.chessbackend.application.domain.model.Square.at;

class BishopRuleTest {
    private final MoveRule rule = new BishopRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteBishop));

    @Test
    void bishop_moves_diagonally() {
        for (String to : List.of("a1", "h8", "a7", "g1", "e5", "c3"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }
}
