package chess.piece;

import chess.piece.detail.Team;
import chess.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물이 갈 수 없는 위치일 시 예외를 발생한다.")
    @Test
    void updateSquare() {
        final Piece piece = new Knight(Team.WHITE, Square.from("a1"));

        Assertions.assertThatThrownBy(() -> piece.updateSquare(Square.from("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
    }
}
