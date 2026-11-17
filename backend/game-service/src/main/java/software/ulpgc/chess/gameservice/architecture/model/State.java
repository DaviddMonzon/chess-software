package software.ulpgc.chess.gameservice.architecture.model;

import java.util.EnumSet;
import java.util.Set;

public record State(Color turn, Set<CastlingRight> castlingRights) {
    public State {
        castlingRights = Set.copyOf(castlingRights);
    }

    public static State initial() {
        return new State(Color.White, EnumSet.allOf(CastlingRight.class));
    }
}
