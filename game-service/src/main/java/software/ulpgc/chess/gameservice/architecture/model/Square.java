package software.ulpgc.chess.gameservice.architecture.model;

import java.util.Arrays;
import java.util.stream.Stream;

public record Square(File file, Rank rank) {
    public static Square at(String square) {
        if (square == null || square.length() != 2)
            throw new IllegalArgumentException("Invalid square: " + square);
        return new Square(
                File.of(Character.toLowerCase(square.charAt(0))),
                Rank.of(square.charAt(1) - '0')
        );
    }

    public static Stream<Square> all() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Square(file, rank)));
    }
}
