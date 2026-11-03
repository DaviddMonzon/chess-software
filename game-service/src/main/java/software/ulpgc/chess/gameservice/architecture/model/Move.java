package software.ulpgc.chess.gameservice.application.domain.model;

import java.util.List;
import java.util.stream.IntStream;

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

    public int fileDelta() {
        return to.file().ordinal() - from.file().ordinal();
    }

    public int rankDelta() {
        return to.rank().value() - from.rank().value();
    }

    public List<Square> squaresBetween() {
        int fileStep = Integer.signum(fileDelta());
        int rankStep = Integer.signum(rankDelta());
        int steps = Math.max(Math.abs(fileDelta()), Math.abs(rankDelta()));
        return IntStream.range(1, steps)
                .mapToObj(i -> new Square(
                        File.values()[from.file().ordinal() + i * fileStep],
                        Rank.of(from.rank().value() + i * rankStep)))
                .toList();
    }
}
