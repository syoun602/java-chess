package chess.piece;

import chess.piece.detail.Direction;
import chess.piece.detail.Team;
import chess.square.Square;
import java.util.List;

public class Pawn extends SingleMovePiece {

    public Pawn(final Team team, final Square square) {
        super(team, square);
    }

    @Override
    List<Direction> getAvailableDirections() {
        if (isBlack()) {
            return Direction.getBlackPawnDirections();
        }
        return Direction.getWhitePawnDirections();
    }
}
