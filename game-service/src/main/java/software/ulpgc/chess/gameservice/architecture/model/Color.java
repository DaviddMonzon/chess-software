package software.ulpgc.chess.gameservice.architecture.model;

public enum Color {
    White, Black;

    public Color opponent() {
        return this == White ? Black : White;
    }
}
