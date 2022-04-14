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


}
