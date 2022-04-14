package chess.domain.piece;

import chess.domain.piece.detail.Direction;
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

    public void updateSquare(final Square to) {
        if (!canMove(to)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
        }
        square = to;
    }

    abstract boolean canMove(final Square to);

    abstract List<Direction> getAvailableDirections();

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
