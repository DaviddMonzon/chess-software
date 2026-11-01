package software.ulpgc.chessbackend.application.domain.model;

public enum File {
    A('a'),B('b'),C('c'),
    D('d'),E('e'),F('f'),
    G('g'),H('h');

    private final char symbol;

    File(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }

    public static File of(char symbol) {
        for (File file : values())
            if (file.symbol == symbol) return file;
        throw new IllegalArgumentException("Invalid file: " + symbol);
    }
}
