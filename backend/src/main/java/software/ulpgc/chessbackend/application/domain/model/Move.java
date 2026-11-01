package software.ulpgc.chessbackend.application.domain.model;

public record Move(Square from, Square to) {
    public Move {
        if (from == null || to == null)
            throw new IllegalArgumentException("A move needs an origin and a destination");
        if (from.equals(to))
            throw new IllegalArgumentException("Origin and destination are the same square: " + from);
    }

    public static Move of(String from, String to) {
        return new Move(Square.at(from), Square.at(to));
    }
}
