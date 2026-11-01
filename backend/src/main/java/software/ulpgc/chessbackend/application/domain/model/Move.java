package software.ulpgc.chessbackend.application.domain.model;

public record Move(Square from, Square to) {
    public static Move of(String from, String to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
