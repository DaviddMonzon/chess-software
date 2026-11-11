package software.ulpgc.chess.game.architecture.model;

public record Square(File file, Rank rank) {
    public static Square at(String square) {
        return new Square(File.of(square.charAt(0)), Rank.of(square.charAt(1) - '0'));
    }
}
