package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StateTest {
    private final State state = State.initial();

    @Test
    void initialTurnIsWhite() {
        assertEquals(Color.White, state.turn());
    }

    @Test
    void initialHasAllCastlingRights() {
        assertEquals(EnumSet.allOf(CastlingRight.class), state.castlingRights());
    }

    @Test
    void castlingRightsCannotBeModified() {
        assertThrows(UnsupportedOperationException.class, () -> state.castlingRights().clear());
    }
}
