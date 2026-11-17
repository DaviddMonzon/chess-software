package software.ulpgc.chess.gameservice.architecture.model;

import java.util.Arrays;

public enum File {
    A('a'), B('b'), C('c'), D('d'),
    E('e'), F('f'), G('g'), H('h');

    private final char symbol;

    File(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }

    public static File of(char symbol) {
        return Arrays.stream(values()).filter(file -> file.symbol == symbol).findFirst().orElseThrow();
    }
}
