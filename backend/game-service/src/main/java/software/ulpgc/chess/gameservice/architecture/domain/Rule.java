package software.ulpgc.chess.gameservice.architecture.domain;

import software.ulpgc.chess.gameservice.architecture.model.Move;

public interface Rule {
    boolean allows(Move move);
}
