package software.ulpgc.chessbackend.application.domain.model;

public enum Result {
    WhiteWins, BlackWins, Draw;

    public static Result winner(Color color) {
        return color == Color.White ? WhiteWins : BlackWins;
    }
}
