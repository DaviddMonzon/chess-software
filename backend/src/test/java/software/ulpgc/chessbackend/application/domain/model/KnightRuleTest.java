package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightRuleTest {
    private final MoveRule rule = new KnightRule();
    private final Board board = new Board(Map.of(Square.at("d4"), Piece.WhiteKnight));

    @Test
    void knight_moves_in_an_L_shape() {
        for (String to : List.of("b3", "b5", "c2", "c6", "e2", "e6", "f3", "f5"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }
}
