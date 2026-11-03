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
}
