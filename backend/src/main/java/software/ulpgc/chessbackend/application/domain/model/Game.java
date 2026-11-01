package software.ulpgc.chessbackend.application.domain.model;

import java.util.List;
import java.util.Optional;

public final class Game {

    private Game() {
    }

    public static Game start() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Game replay(List<Move> moves) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Game play(Move move) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Game resign(Color color) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isOver() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Board board() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Color turn() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Move> moves() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public GameStatus status() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Optional<Result> result() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
