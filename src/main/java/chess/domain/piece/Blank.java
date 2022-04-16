package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.Collections;
import java.util.List;

public class Blank extends Piece {

    public Blank(final Team team, final Square square) {
        super(team, square);
        validateTeam(team);
    }

    private void validateTeam(final Team team) {
        if (!team.isNone()) {
            throw new IllegalArgumentException("빈칸은 팀이 있으면 안됩니다.");
        }
    }

    @Override
    boolean isBlank() {
        return true;
    }

    @Override
    boolean canMove(final Piece piece) {
        return false;
    }

    @Override
    List<Direction> getAvailableDirections() {
        return Collections.emptyList();
    }
}
