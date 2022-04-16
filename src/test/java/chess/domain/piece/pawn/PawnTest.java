package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.detail.Team;
import chess.domain.piece.singlemove.King;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @DisplayName("폰이 같은 팀 기물위치로 이동할수 있는지 확인할 시 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"WHITE,c1", "BLACK,a1"})
    void canAttackMove(final String rawTeam, final String rawSquare) {
        final Square from = Square.from("b2");
        final Square to = Square.from(rawSquare);
        final Team team = Team.valueOf(rawTeam);
        final Piece pawn = Pawn.of(team, from);

        assertThat(pawn.canMove(new King(team, to))).isFalse();
    }

    @DisplayName("폰이 초기 위치일 시 두 칸을 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource({"WHITE,a2,a4", "BLACK,a7,a5"})
    void canAttackMove(final String rawTeam, final String rawFromSquare, final String rawToSquare) {
        final Square from = Square.from(rawFromSquare);
        final Square to = Square.from(rawToSquare);
        final Team team = Team.valueOf(rawTeam);
        final Piece pawn = Pawn.of(team, from);

        assertThat(pawn.canMove(new Blank(Team.NONE, to))).isTrue();
    }

    @DisplayName("NONE 팀으로 폰을 생성할 시 예외를 발생한다.")
    @Test
    void cannotCreatePawnWithNoneTeam() {
        assertThatThrownBy(() -> Pawn.of(Team.NONE, Square.from("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰 기물의 팀은 NONE일 수 없습니다.");
    }
}
