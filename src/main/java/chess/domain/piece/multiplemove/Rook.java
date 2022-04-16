package chess.domain.piece.multiplemove;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.List;

public class Rook extends MultipleMovePiece {

    public Rook(final Team team, final Square square) {
        super(team, square);
    }

    @Override
    public List<Direction> getAvailableDirections() {
        return Direction.getVerticalAndHorizontalDirections();
    }
}
