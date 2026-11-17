package software.ulpgc.chess.gameservice.architecture.model;

public record Position(Board board, State state) {
    public static Position initial() {
        return new Position(Board.initial(), State.initial());
    }
}
