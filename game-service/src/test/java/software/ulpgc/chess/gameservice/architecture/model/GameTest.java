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

    @Test
    void a_move_that_ignores_a_check_is_rejected() {
        Game game = Game.from(new Board(Map.of(
                Square.at("e1"), Piece.WhiteKing,
                Square.at("a2"), Piece.WhitePawn,
                Square.at("e8"), Piece.BlackRook,
                Square.at("h8"), Piece.BlackKing)), Color.White);
        assertThrows(IllegalArgumentException.class, () -> game.play(Move.of("a2", "a3")));
    }

    @Test
    void a_check_can_be_answered_by_blocking_capturing_or_moving_the_king() {
        Board board = new Board(Map.of(
                Square.at("e1"), Piece.WhiteKing,
                Square.at("h2"), Piece.WhiteRook,
                Square.at("a8"), Piece.WhiteQueen,
                Square.at("e8"), Piece.BlackRook,
                Square.at("h8"), Piece.BlackKing));
        Game game = Game.from(board, Color.White);
        assertDoesNotThrow(() -> game.play(Move.of("h2", "e2")));
        assertDoesNotThrow(() -> game.play(Move.of("a8", "e8")));
        assertDoesNotThrow(() -> game.play(Move.of("e1", "d1")));
    }

    @Test
    void the_king_cannot_move_to_an_attacked_square() {
        Game game = Game.from(new Board(Map.of(
                Square.at("e1"), Piece.WhiteKing,
                Square.at("d8"), Piece.BlackRook,
                Square.at("h8"), Piece.BlackKing)), Color.White);
        assertThrows(IllegalArgumentException.class, () -> game.play(Move.of("e1", "d1")));
    }

    @Test
    void the_game_knows_when_the_player_to_move_is_in_check() {
        Game game = Game.from(new Board(Map.of(
                Square.at("h1"), Piece.WhiteKing,
                Square.at("a2"), Piece.WhiteRook,
                Square.at("e8"), Piece.BlackKing)), Color.White);
        assertFalse(game.isInCheck());
        assertTrue(game.play(Move.of("a2", "e2")).isInCheck());
    }

    @Test
    void fools_mate_ends_the_game_and_black_wins() {
        Game game = Game.replay(List.of(
                Move.of("f2", "f3"), Move.of("e7", "e5"),
                Move.of("g2", "g4"), Move.of("d8", "h4")));
        assertEquals(GameStatus.Checkmate, game.status());
        assertEquals(Result.BlackWins, game.result().orElseThrow());
        assertTrue(game.isOver());
        assertThrows(IllegalStateException.class, () -> game.play(Move.of("e2", "e4")));
    }

    @Test
    void a_check_that_is_not_mate_keeps_the_game_in_progress() {
        Game game = Game.replay(List.of(
                Move.of("e2", "e4"), Move.of("f7", "f6"),
                Move.of("d1", "h5")));
        assertTrue(game.isInCheck());
        assertEquals(GameStatus.InProgress, game.status());
        assertTrue(game.result().isEmpty());
    }

    @Test
    void a_player_without_legal_moves_and_not_in_check_is_stalemated() {
        Game game = Game.from(new Board(Map.of(
                Square.at("a8"), Piece.BlackKing,
                Square.at("c6"), Piece.WhiteKing,
                Square.at("b5"), Piece.WhiteQueen)), Color.White)
                .play(Move.of("b5", "b6"));
        assertEquals(GameStatus.Stalemate, game.status());
        assertEquals(Result.Draw, game.result().orElseThrow());
        assertFalse(game.isInCheck());
    }
}
