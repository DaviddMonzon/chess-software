package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.*;
import static software.ulpgc.chess.gameservice.architecture.model.Square.at;

class BoardTest {

    @Test
    void a_rook_on_the_same_file_with_nothing_between_gives_check() {
        Board board = new Board(Map.of(at("e1"), WhiteKing, at("e8"), BlackRook));
        assertTrue(board.isInCheck(Color.White));
    }

    @Test
    void a_king_without_attackers_is_not_in_check() {
        Board board = new Board(Map.of(at("e1"), WhiteKing, at("d8"), BlackRook));
        assertFalse(board.isInCheck(Color.White));
    }

    @Test
    void a_piece_between_blocks_the_check() {
        Board board = new Board(Map.of(at("e1"), WhiteKing, at("e2"), WhitePawn, at("e8"), BlackRook));
        assertFalse(board.isInCheck(Color.White));
    }

    @Test
    void a_knight_gives_check_jumping_over_pieces() {
        Board board = new Board(Map.of(
                at("e1"), WhiteKing,
                at("d2"), WhitePawn, at("e2"), WhitePawn, at("f2"), WhitePawn,
                at("f3"), BlackKnight));
        assertTrue(board.isInCheck(Color.White));
    }

    @Test
    void a_pawn_gives_check_diagonally_but_not_straight_ahead() {
        Board diagonal = new Board(Map.of(at("e4"), WhiteKing, at("d5"), BlackPawn));
        Board ahead = new Board(Map.of(at("e4"), WhiteKing, at("e5"), BlackPawn));
        assertTrue(diagonal.isInCheck(Color.White));
        assertFalse(ahead.isInCheck(Color.White));
    }
}
