package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Division {

    public static final int BISHOP_SCORE = 3;

    public Bishop(Color color, Position position) {
        super(color, "b", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        if (position.isDiagonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {
        this.moveToEmpty(to, pieces);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }


    public List<Position> movablePosition(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(position.positionsOfDirection(1,1));
        positions.addAll(position.positionsOfDirection(1,-1));
        positions.addAll(position.positionsOfDirection(-1,1));
        positions.addAll(position.positionsOfDirection(-1,-1));

        return positions;
    }
}
