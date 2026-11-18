package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    void initialCombinesInitialBoardAndState() {
        assertEquals(new Position(Board.initial(), State.initial()), Position.initial());
    }
}
