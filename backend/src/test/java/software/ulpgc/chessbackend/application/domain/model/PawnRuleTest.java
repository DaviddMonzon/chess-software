package software.ulpgc.chessbackend.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chessbackend.application.domain.model.Piece.BlackKnight;
import static software.ulpgc.chessbackend.application.domain.model.Piece.BlackPawn;
import static software.ulpgc.chessbackend.application.domain.model.Piece.WhiteKnight;
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

    @Test
    void pawn_advances_two_squares_from_its_start_rank() {
        Board board = new Board(Map.of(at("e2"), WhitePawn, at("e7"), BlackPawn));
        assertTrue(rule.allows(board, Move.of("e2", "e4")));
        assertTrue(rule.allows(board, Move.of("e7", "e5")));
    }

    @Test
    void pawn_cannot_advance_two_squares_outside_its_start_rank() {
        Board board = new Board(Map.of(at("e3"), WhitePawn, at("e6"), BlackPawn));
        assertFalse(rule.allows(board, Move.of("e3", "e5")));
        assertFalse(rule.allows(board, Move.of("e6", "e4")));
    }

    @Test
    void pawn_cannot_move_backwards_or_sideways() {
        Board board = new Board(Map.of(at("e3"), WhitePawn, at("e6"), BlackPawn));
        assertFalse(rule.allows(board, Move.of("e3", "e2")));
        assertFalse(rule.allows(board, Move.of("e3", "d3")));
        assertFalse(rule.allows(board, Move.of("e6", "e7")));
    }

    @Test
    void pawn_cannot_advance_into_or_through_an_occupied_square() {
        Board board = new Board(Map.of(at("e2"), WhitePawn, at("e3"), BlackKnight));
        assertFalse(rule.allows(board, Move.of("e2", "e3")));
        assertFalse(rule.allows(board, Move.of("e2", "e4")));
    }

    @Test
    void pawn_captures_diagonally_forward() {
        Board board = new Board(Map.of(at("e4"), WhitePawn, at("d5"), BlackPawn, at("f5"), BlackKnight));
        assertTrue(rule.allows(board, Move.of("e4", "d5")));
        assertTrue(rule.allows(board, Move.of("e4", "f5")));
        Board black = new Board(Map.of(at("d5"), BlackPawn, at("e4"), WhiteKnight));
        assertTrue(rule.allows(black, Move.of("d5", "e4")));
    }

    @Test
    void pawn_does_not_move_diagonally_to_an_empty_square() {
        Board board = new Board(Map.of(at("e4"), WhitePawn));
        assertFalse(rule.allows(board, Move.of("e4", "d5")));
        assertFalse(rule.allows(board, Move.of("e4", "f5")));
    }
}
