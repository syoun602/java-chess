package chess.domain.piece.multiplemove;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @DisplayName("비숍이 갈 수 있는 위치인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"d4,a1", "d4,g1", "d4,a7", "d4,h8"})
    void canMove(final String rawFrom, final String rawTo) {
        final Square from = Square.from(rawFrom);
        final Square to = Square.from(rawTo);
        final Piece bishop = new Bishop(Team.WHITE, from);

        Assertions.assertThat(bishop.canMove(new Blank(Team.NONE, to))).isTrue();
    }
}
