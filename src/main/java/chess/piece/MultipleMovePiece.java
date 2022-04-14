package chess.piece;

import chess.piece.detail.Team;
import chess.square.Square;

public abstract class MultipleMovePiece extends NonBlankPiece {

    protected MultipleMovePiece(final Team team, final Square square) {
        super(team, square);
    }

}
