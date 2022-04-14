package chess.piece;

import chess.piece.detail.Direction;
import chess.piece.detail.Team;
import chess.square.Square;
import java.util.List;

public abstract class Piece {

    private final Team team;
    protected Square square;

    protected Piece(final Team team, final Square square) {
        this.team = team;
        this.square = square;
    }

    abstract boolean canMove(final Square to);

    abstract List<Direction> getAvailableDirections();
}
