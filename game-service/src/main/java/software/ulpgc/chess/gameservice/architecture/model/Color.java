package software.ulpgc.chess.gameservice.application.domain.model;

public enum Color {
    White, Black;

    public Color opponent() {
        return this == White ? Black : White;
    }
}
