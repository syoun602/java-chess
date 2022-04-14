package chess.piece;

import chess.piece.detail.Team;
import chess.square.Square;

public abstract class NonBlankPiece extends Piece {

    protected NonBlankPiece(final Team team, final Square square) {
        super(team, square);
        validateTeam(team);
    }

    private void validateTeam(final Team team) {
        if (team.isNone()) {
            throw new IllegalArgumentException("빈칸 외의 기물은 팀이 있어야 합니다.");
        }
    }
}
