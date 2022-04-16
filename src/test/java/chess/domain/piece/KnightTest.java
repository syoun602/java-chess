package chess.domain.piece;

import chess.domain.piece.detail.Direction;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @DisplayName("나이트가 움직일 수 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"a1,NNE", "a1,ENE", "a2,ESE", "a3,SSE", "c2,WSW", "c1,WNW", "b1,NNW"})
    void canMove(final String rawSquare, final String rawDirection) {
        final Square from = Square.from(rawSquare);
        final Knight knight = new Knight(Team.WHITE, from);
        final Direction direction = Direction.valueOf(rawDirection);
        final Square to = from.next(direction);

        Assertions.assertThat(knight.canMove(new Blank(Team.NONE, to))).isTrue();
    }
}
