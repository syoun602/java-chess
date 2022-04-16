package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.List;

public class BlackPawn extends Pawn {

    public BlackPawn(final Team team, final Square square) {
        super(team, square);
    }

    @Override
    List<Direction> getAvailableDirections() {
        return Direction.getBlackPawnDirections();
    }

    @Override
    protected boolean isPawnInitial() {
        return square.isBlackPawnInitial();
    }

    @Override
    protected List<Direction> getPawnAttackDirections() {
        return Direction.getBlackPawnAttackDirections();
    }
}
