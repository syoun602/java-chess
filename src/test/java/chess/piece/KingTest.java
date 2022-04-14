package chess.piece;

import chess.piece.detail.Direction;
import chess.piece.detail.Team;
import chess.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("킹이 움직일 수 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"a1,N", "a1,NE", "a1,E", "a2,SE", "a2,S", "b2,SW", "b1,NW"})
    void canMove(final String rawSquare, final String rawDirection) {
        final Square from = Square.from(rawSquare);
        final King king = new King(Team.WHITE, from);
        final Direction direction = Direction.valueOf(rawDirection);
        final Square to = from.next(direction);

        Assertions.assertThat(king.canMove(to)).isTrue();
    }
}
