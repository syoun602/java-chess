package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.List;

public class Queen extends MultipleMovePiece {

    public Queen(final Team team, final Square square) {
        super(team, square);
    }

    @Override
    List<Direction> getAvailableDirections() {
        return Direction.getEveryDirections();
    }
}
