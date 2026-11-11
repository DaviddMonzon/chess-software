package software.ulpgc.chess.game.architecture.model;

import static software.ulpgc.chess.game.architecture.model.Color.Black;
import static software.ulpgc.chess.game.architecture.model.Color.White;

public enum Piece {
    WhitePawn(White), WhiteRook(White), WhiteKnight(White),
    WhiteBishop(White), WhiteQueen(White), WhiteKing(White),
    BlackPawn(Black), BlackRook(Black), BlackKnight(Black),
    BlackBishop(Black), BlackQueen(Black), BlackKing(Black);

    private final Color color;

    Piece(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }

    public boolean isPawn() {
        return this == WhitePawn || this == BlackPawn;
    }

    public boolean isRook() {
        return this == WhiteRook || this == BlackRook;
    }

    public boolean isKnight() {
        return this == WhiteKnight || this == BlackKnight;
    }

    public boolean isBishop() {
        return this == WhiteBishop || this == BlackBishop;
    }

    public boolean isQueen() {
        return this == WhiteQueen || this == BlackQueen;
    }

    public boolean isKing() {
        return this == WhiteKing || this == BlackKing;
    }
}
