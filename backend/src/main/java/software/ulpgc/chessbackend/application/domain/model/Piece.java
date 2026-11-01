package software.ulpgc.chessbackend.application.domain.model;

import static software.ulpgc.chessbackend.application.domain.model.Color.*;

public enum Piece {
    WhitePawn,
    WhiteRook,
    WhiteKnight,
    WhiteBishop,
    WhiteKing,
    WhiteQueen,
    BlackPawn,
    BlackRook,
    BlackKnight,
    BlackBishop,
    BlackKing,
    BlackQueen;

    Color color() {
        return isWhite() ? White : Black;
    }

    boolean isPawn() {
        return this == WhitePawn || this == BlackPawn;
    }

    boolean isRook() {
        return this == WhiteRook || this == BlackRook;
    }

    boolean isKnight() {
        return this == WhiteKnight || this == BlackKnight;
    }

    boolean isBishop() {
        return this == WhiteBishop || this == BlackBishop;
    }

    boolean isKing() {
        return this == WhiteKing || this == BlackKing;
    }

    boolean isQueen() {
        return this == WhiteQueen || this == BlackQueen;
    }

    private boolean isWhite() {
        return this == WhitePawn ||
                this == WhiteRook ||
                this == WhiteKnight ||
                this == WhiteBishop ||
                this == WhiteKing ||
                this == WhiteQueen;
    }
}
