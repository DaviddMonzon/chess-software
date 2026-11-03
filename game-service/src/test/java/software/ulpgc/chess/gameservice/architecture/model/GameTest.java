package software.ulpgc.chess.gameservice.architecture.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void new_game_starts_with_white_to_move_and_no_moves() {
        Game game = Game.start();
        assertEquals(Color.White, game.turn());
        assertTrue(game.moves().isEmpty());
        assertEquals(GameStatus.InProgress, game.status());
        assertTrue(game.result().isEmpty());
        assertEquals(Board.initial(), game.board());
    }

    @Test
    void playing_a_move_changes_turn_and_moves_the_piece() {
        Game game = Game.start().play(Move.of("e2", "e4"));
        assertEquals(Color.Black, game.turn());
        assertEquals(List.of(Move.of("e2", "e4")), game.moves());
        assertEquals(Piece.WhitePawn, game.board().pieces().get(Square.at("e4")));
        assertNull(game.board().pieces().get(Square.at("e2")));
    }

    @Test
    void playing_does_not_modify_the_original_game() {
        Game original = Game.start();
        original.play(Move.of("e2", "e4"));
        assertEquals(Color.White, original.turn());
        assertTrue(original.moves().isEmpty());
    }

    @Test
    void replay_rebuilds_the_same_game() {
        Game game = Game.start().play(Move.of("e2", "e4")).play(Move.of("e7", "e5"));
        Game replayed = Game.replay(game.moves());
        assertEquals(game.board(), replayed.board());
        assertEquals(game.turn(), replayed.turn());
    }

    @Test
    void cannot_move_from_an_empty_square() {
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("e4", "e5")));
    }

    @Test
    void cannot_move_an_opponent_piece() {
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("e7", "e5")));
    }

    @Test
    void cannot_capture_own_piece() {
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("a1", "a2")));
    }

    @Test
    void a_move_cannot_end_on_its_origin() {
        assertThrows(IllegalArgumentException.class, () -> Move.of("e2", "e2"));
    }

    @Test
    void a_move_that_breaks_the_piece_rules_is_rejected() {
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("b1", "b3")));
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("a1", "a5")));
        assertThrows(IllegalArgumentException.class, () -> Game.start().play(Move.of("e2", "e5")));
    }

    @Test
    void a_move_that_follows_the_piece_rules_is_played() {
        Game game = Game.start().play(Move.of("g1", "f3"));
        assertEquals(Piece.WhiteKnight, game.board().pieces().get(Square.at("f3")));
    }

    @Test
    void resigning_ends_the_game_and_the_opponent_wins() {
        Game game = Game.start().resign(Color.White);
        assertEquals(GameStatus.Resigned, game.status());
        assertEquals(Result.BlackWins, game.result().orElseThrow());
        assertTrue(game.isOver());
        assertThrows(IllegalStateException.class, () -> game.play(Move.of("e2", "e4")));
    }

    @Test
    void a_pinned_piece_cannot_move() {
        Game game = Game.from(new Board(Map.of(
                Square.at("e1"), Piece.WhiteKing,
                Square.at("e2"), Piece.WhiteBishop,
                Square.at("e8"), Piece.BlackRook,
                Square.at("a8"), Piece.BlackKing)), Color.White);
        assertThrows(IllegalArgumentException.class, () -> game.play(Move.of("e2", "d3")));
    }
}
