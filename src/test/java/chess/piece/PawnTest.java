package chess.piece;

import chess.piece.detail.Direction;
import chess.piece.detail.Team;
import chess.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @DisplayName("검은색 폰이 움직일 수 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"b2,S", "b2,SW", "b2,SE"})
    void canBlackMove(final String rawSquare, final String rawDirection) {
        final Square from = Square.from(rawSquare);
        final Piece pawn = new Pawn(Team.BLACK, from);
        final Direction direction = Direction.valueOf(rawDirection);
        final Square to = from.next(direction);

        Assertions.assertThat(pawn.canMove(to)).isTrue();
    }

    @DisplayName("흰색 폰이 움직일 수 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"b1,N", "b1,NW", "b1,NE"})
    void canWhiteMove(final String rawSquare, final String rawDirection) {
        final Square from = Square.from(rawSquare);
        final Piece pawn = new Pawn(Team.WHITE, from);
        final Direction direction = Direction.valueOf(rawDirection);
        final Square to = from.next(direction);

        Assertions.assertThat(pawn.canMove(to)).isTrue();
    }
}
