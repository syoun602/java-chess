package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.PieceType;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.List;

public abstract class Piece {

    private final Team team;
    protected Square square;

    protected Piece(final Team team, final Square square) {
        this.team = team;
        this.square = square;
    }

    public abstract boolean canMove(final Piece piece);

    public abstract boolean isBlank();

    public abstract PieceType getPieceType();

    protected abstract List<Direction> getAvailableDirections();

    public void updateSquare(final Square to) {
        square = to;
    }

    public boolean isSameTeam(final Piece target) {
        return team == target.team;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public Team getTeam() {
        return team;
    }

    public Square getSquare() {
        return square;
    }
}
