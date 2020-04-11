package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    void canMove_세로로_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("c8"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_세로로_후진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("h5"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("h5"), Position.of("h1"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가로로_전진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("h2"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가로로_후진() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("a2"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_오른쪽_앞_대각선_이동() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("c2"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("c2"), Position.of("e4"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_오른쪽_뒤_대각선_이동() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("e4"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("e4"), Position.of("c2"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_왼쪽_앞_대각선_이동() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of("b7"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_왼쪽_뒤_대각선_이동() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b7"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("b7"), Position.of("d5"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isTrue();
    }

    @Test
    void canMove_가는_길에_장애물이_있을_때() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new Queen(Team.WHITE));
        board.put(Position.of("c6"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of("b7"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void canMove_엉뚱한_방향으로_이동() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of("d5"), new Queen(Team.WHITE));

        MoveInformation moveInformation = new MoveInformation(
            board, Position.of("d5"), Position.of("a4"));

        assertThat(new Queen(Team.WHITE).canMove(moveInformation)).isFalse();
    }

    @Test
    void 인자있는_score_예외처리() {
        assertThatThrownBy(() -> {
            new Queen(Team.WHITE).getScore(true);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 인자없는_score() {
        assertThat(new Queen(Team.WHITE).getScore()).isEqualTo(9.0);
    }
}
