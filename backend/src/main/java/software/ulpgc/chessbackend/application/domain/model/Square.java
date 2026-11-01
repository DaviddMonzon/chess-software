package software.ulpgc.chessbackend.application.domain.model;

public record Square(File file, Rank rank) {
    public static Square at(String square) {
        if (square == null || square.length() != 2)
            throw new IllegalArgumentException("Invalid square: " + square);
        return new Square(
                File.of(Character.toLowerCase(square.charAt(0))),
                Rank.of(square.charAt(1) - '0')
        );
    }
}
