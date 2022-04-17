package chess.domain;

import static chess.domain.piece.detail.Team.*;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import java.util.Map;

public class Board {

    private final Map<Square, Piece> value;

    public Board(final Map<Square, Piece> value) {
        this.value = value;
    }

    public void move(final Square from, final Square to) {
        Piece source = value.get(from);
        Piece target = value.get(to);

        validateMove(source, target);
        validateRoute(from, to);

        value.put(to, source.moveTo(to));
        value.put(from, new Blank(NONE, from));
    }

    public boolean isSameTeam(final Square from, final Team team) {
        final Piece piece = value.get(from);
        return piece.isSameTeam(team);
    }

    private void validateMove(final Piece source, final Piece target) {
        if (!source.canMove(target)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateRoute(final Square from, final Square to) {
        Direction direction = Direction.findAllDirections(from, to);
        for(Square square = from.next(direction); !square.equals(to); square = square.next(direction)) {
            validateBlock(square);
        }
    }

    private void validateBlock(final Square square) {
        if (!value.get(square).isBlank()) {
            throw new IllegalArgumentException("이동 경로에 장애물이 있습니다.");
        }
    }

    public Piece getPieceAt(final Square square) {
        return value.get(square);
    }
}
