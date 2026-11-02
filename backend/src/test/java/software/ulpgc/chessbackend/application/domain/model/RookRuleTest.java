package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhiteRook;
import static software.ulpgc.chessbackend.application.domain.model.Square.at;

class RookRuleTest {
    private final MoveRule rule = new RookRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteRook));

    @Test
    void rook_moves_in_a_straight_line() {
        for (String to : List.of("d1", "d8", "a4", "h4", "d5", "e4"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }
}
