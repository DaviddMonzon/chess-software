package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTest {
    @Test
    void colorMatchesPieceSide() {
        assertTrue(allPieces(piece -> piece.color().name().equals(piece.name().startsWith("White") ? "White" : "Black")));
    }

    @Test
    void kindChecksMatchOnlyTheirPieces() {
        assertTrue(allPieces(piece -> piece.isPawn() == piece.name().endsWith("Pawn")));
        assertTrue(allPieces(piece -> piece.isRook() == piece.name().endsWith("Rook")));
        assertTrue(allPieces(piece -> piece.isKnight() == piece.name().endsWith("Knight")));
        assertTrue(allPieces(piece -> piece.isBishop() == piece.name().endsWith("Bishop")));
        assertTrue(allPieces(piece -> piece.isQueen() == piece.name().endsWith("Queen")));
        assertTrue(allPieces(piece -> piece.isKing() == piece.name().endsWith("King")));
    }

    private static boolean allPieces(Predicate<Piece> condition) {
        return Arrays.stream(Piece.values()).allMatch(condition);
    }
}
