package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static software.ulpgc.chess.gameservice.architecture.model.Piece.*;

public class BoardTest {
    private final Board board = Board.initial();

    @Test
    void initialHasThirtyTwoPieces() {
        assertEquals(32, board.pieces().size());
    }

    @Test
    void initialPlacesKingsOnTheEFile() {
        assertEquals(Optional.of(WhiteKing), board.pieceAt(Square.at("e1")));
        assertEquals(Optional.of(BlackKing), board.pieceAt(Square.at("e8")));
    }

    @Test
    void initialPlacesQueensOnTheDFile() {
        assertEquals(Optional.of(WhiteQueen), board.pieceAt(Square.at("d1")));
        assertEquals(Optional.of(BlackQueen), board.pieceAt(Square.at("d8")));
    }

    @Test
    void initialFillsSecondAndSeventhRanksWithPawns() {
        assertTrue(Arrays.stream(File.values()).allMatch(file -> board.pieceAt(new Square(file, Rank.R2)).equals(Optional.of(WhitePawn))));
        assertTrue(Arrays.stream(File.values()).allMatch(file -> board.pieceAt(new Square(file, Rank.R7)).equals(Optional.of(BlackPawn))));
    }

    @Test
    void pieceAtEmptySquareIsEmpty() {
        assertTrue(board.pieceAt(Square.at("e4")).isEmpty());
    }

    @Test
    void pieceAtAcceptsSquareName() {
        assertEquals(board.pieceAt(Square.at("e1")), board.pieceAt("e1"));
    }

    @Test
    void doesNotChangeWhenOriginalPiecesChange() {
        Map<Square, Piece> pieces = new HashMap<>();
        Board emptyBoard = new Board(pieces);
        pieces.put(Square.at("e4"), WhitePawn);
        assertTrue(emptyBoard.pieceAt(Square.at("e4")).isEmpty());
    }
}
