package software.ulpgc.chess.gameservice.application.domain.model;

import org.junit.jupiter.api.Test;
import software.ulpgc.chess.gameservice.architecture.model.Board;
import software.ulpgc.chess.gameservice.architecture.model.KnightRule;
import software.ulpgc.chess.gameservice.architecture.model.Move;
import software.ulpgc.chess.gameservice.architecture.model.MoveRule;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.WhiteKnight;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.WhitePawn;
import static software.ulpgc.chess.gameservice.architecture.model.Square.at;

class KnightRuleTest {
    private final MoveRule rule = new KnightRule();
    private final Board board = new Board(Map.of(at("d4"), WhiteKnight));

    @Test
    void knight_moves_in_an_L_shape() {
        for (String to : List.of("b3", "b5", "c2", "c6", "e2", "e6", "f3", "f5"))
            assertTrue(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void knight_does_not_move_straight_or_diagonally() {
        for (String to : List.of("d5", "d6", "f4", "e5", "f6", "a1"))
            assertFalse(rule.allows(board, Move.of("d4", to)), "d4 -> " + to);
    }

    @Test
    void knight_jumps_over_pieces() {
        Board surrounded = new Board(Map.of(
                at("d4"), WhiteKnight,
                at("c3"), WhitePawn, at("c4"), WhitePawn, at("c5"), WhitePawn,
                at("d3"), WhitePawn, at("d5"), WhitePawn,
                at("e3"), WhitePawn, at("e4"), WhitePawn, at("e5"), WhitePawn
        ));
        assertTrue(rule.allows(surrounded, Move.of("d4", "e6")));
    }
}
