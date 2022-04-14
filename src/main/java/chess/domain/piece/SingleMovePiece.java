package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.List;

public abstract class SingleMovePiece extends NonBlankPiece {

    protected SingleMovePiece(final Team team, final Square square) {
        super(team, square);
    }

    @Override
    public boolean canMove(final Square to) {
        final List<Direction> directions = getAvailableDirections();

        return directions.stream()
                .filter(direction -> square.isExist(direction))
                .map(direction -> square.next(direction))
                .anyMatch(to::equals);
    }
}
