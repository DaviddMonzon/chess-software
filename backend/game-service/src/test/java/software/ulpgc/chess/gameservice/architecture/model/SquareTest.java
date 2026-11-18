package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {
    @Test
    void atReadsFileAndRank() {
        assertEquals(new Square(File.E, Rank.R4), Square.at("e4"));
    }

    @Test
    void atReadsBoardCorners() {
        assertEquals(new Square(File.A, Rank.R1), Square.at("a1"));
        assertEquals(new Square(File.H, Rank.R8), Square.at("h8"));
    }

    @Test
    void atRejectsSquareOutsideBoard() {
        assertThrows(NoSuchElementException.class, () -> Square.at("i9"));
    }
}
