package software.ulpgc.chess.game.architecture.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static software.ulpgc.chess.game.architecture.model.Piece.*;
import static software.ulpgc.chess.game.architecture.model.Rank.*;

public record Board(Map<Square, Piece> pieces) {
    private static final List<Piece> WhiteBackRank = List.of(WhiteRook, WhiteKnight, WhiteBishop, WhiteQueen, WhiteKing, WhiteBishop, WhiteKnight, WhiteRook);
    private static final List<Piece> BlackBackRank = List.of(BlackRook, BlackKnight, BlackBishop, BlackQueen, BlackKing, BlackBishop, BlackKnight, BlackRook);

    public Board {
        pieces = Map.copyOf(pieces);
    }

    public static Board initial() {
        return new Board(Arrays.stream(File.values()).flatMap(Board::initialColumn).collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private static Stream<Map.Entry<Square, Piece>> initialColumn(File file) {
        return Stream.of(
                Map.entry(new Square(file, R1), WhiteBackRank.get(file.ordinal())),
                Map.entry(new Square(file, R2), WhitePawn),
                Map.entry(new Square(file, R7), BlackPawn),
                Map.entry(new Square(file, R8), BlackBackRank.get(file.ordinal()))
        );
    }

    public Piece pieceAt(Square square) {
        return pieces.get(square);
    }

    public Piece pieceAt(String square) {
        return pieceAt(Square.at(square));
    }
}