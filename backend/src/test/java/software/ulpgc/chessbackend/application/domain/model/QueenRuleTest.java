package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
