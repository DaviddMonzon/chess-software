package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhiteKing;
import static software.ulpgc.chessbackend.application.domain.model.Square.at;

class KingRuleTest {
    private final MoveRule rule = new KingRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteKing));

    @Test
    void king_moves_one_square_in_any_direction() {
        for (String to : List.of("c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }
}
