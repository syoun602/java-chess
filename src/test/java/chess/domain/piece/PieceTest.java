package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.detail.Team;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PieceTest {

    @DisplayName("기물의 위치를 변경한다")
    @Test
    void updateSquare() {
        final Piece piece = new Knight(Team.WHITE, Square.from("a1"));
        final Square to = Square.from("c2");
        piece.updateSquare(to);

        assertThat(piece.getSquare()).isEqualTo(to);
    }

    @DisplayName("기물이 갈 수 없는 위치일 시 예외를 발생한다.")
    @Test
    void invalidUpdateSquareException() {
        final Piece piece = new Knight(Team.WHITE, Square.from("a1"));

        assertThatThrownBy(() -> piece.updateSquare(Square.from("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
    }

    @DisplayName("기물의 색상을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"BLACK", "WHITE"})
    void getTeam(final String rawTeam) {
        final Team team = Team.valueOf(rawTeam);
        final Piece piece = new Knight(team, Square.from("a1"));
        assertThat(piece.getTeam()).isEqualTo(team);
    }
}
