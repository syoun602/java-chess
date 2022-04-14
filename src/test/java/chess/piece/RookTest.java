package chess.piece;

import chess.piece.detail.Team;
import chess.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest
    @CsvSource({"d4,d8", "d4,h4", "d4,d1", "d4,a4"})
    void canMove(final String rawFrom, final String rawTo) {
        final Square from = Square.from(rawFrom);
        final Square to = Square.from(rawTo);
        final Rook rook = new Rook(Team.WHITE, from);

        Assertions.assertThat(rook.canMove(to)).isTrue();
    }
}
