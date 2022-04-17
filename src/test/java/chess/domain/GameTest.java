package chess.domain;

import static chess.domain.piece.detail.Team.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Piece;
import chess.domain.piece.multiplemove.Bishop;
import chess.domain.piece.multiplemove.Queen;
import chess.domain.piece.multiplemove.Rook;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.singlemove.Knight;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.HashMap;
import java.util.Map;

class GameTest {

    @DisplayName("게임에 이동 명령을 내리면 기물이 정상적으로 움직이는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"a2,a3", "a2,a4", "b1, a3", "b1,c3"})
    void move(final String rawFrom, final String rawTo) {
        final Game game = Game.initGame();
        final Square from = Square.from(rawFrom);
        final Square to = Square.from(rawTo);
        final Board board = game.getBoard();
        final Piece expected = board.getPieceAt(from);

        game.move(from, to);
        final Piece actual = board.getPieceAt(to);

        assertAll(
                () -> assertThat(actual.getPieceType()).isSameAs(expected.getPieceType()),
                () -> assertThat(actual.getTeam()).isSameAs(expected.getTeam())
        );
    }

    @DisplayName("자기 차례가 아닌 팀의 기물이 움직일 경우 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"a7,a6", "a7,a5", "a8,a5", "b8,a6", "c8,b7", "d8,d5", "e8,e7"})
    void invalidTurnMove(final String rawFrom, final String rawTo) {
        final Game game = Game.initGame();
        final Square from = Square.from(rawFrom);
        final Square to = Square.from(rawTo);

        assertThatThrownBy(() -> game.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("현재는 %s의 차례입니다. 차례에 맞는 기물을 움직여주세요.", game.getTurn().name()));
    }

    @DisplayName("정상적으로 이동이 완료되면 턴이 넘어간다.")
    @ParameterizedTest
    @CsvSource({"a2,a3", "a2,a4", "b1, a3", "b1,c3"})
    void reverseTurn(final String rawFrom, final String rawTo) {
        final Game game = Game.initGame();
        final Square from = Square.from(rawFrom);
        final Square to = Square.from(rawTo);

        game.move(from, to);

        assertThat(game.getTurn()).isEqualTo(BLACK);
    }

    @DisplayName("게임에 종료 명령을 주면 게임이 종료된다.")
    @Test
    void terminate() {
        final Game game = Game.initGame();
        game.terminate();
        assertThat(game.getTurn()).isSameAs(NONE);
    }

    @DisplayName("종료된 게임에 기물을 움직이려하면 예외가 발생한다.")
    @Test
    void invalidMoveAfterTerminate() {
        final Game game = Game.initGame();
        game.terminate();
        assertThatThrownBy(() -> game.move(Square.from("a1"), Square.from("a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 종료된 게임입니다.");
    }

    @Test
    void createResult() {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.from("a1"), Pawn.of(WHITE, Square.from("a1")));
        board.put(Square.from("a2"), Pawn.of(WHITE, Square.from("a2")));
        board.put(Square.from("a3"), Pawn.of(WHITE, Square.from("a3"))); // Score : 1.5

        board.put(Square.from("b1"), Pawn.of(WHITE, Square.from("b1"))); // Score : 2.5

        board.put(Square.from("c1"), new Rook(WHITE, Square.from("c1"))); // Score : 7.5
        board.put(Square.from("d1"), new Knight(WHITE, Square.from("d1"))); // Score : 10
        board.put(Square.from("e1"), new Bishop(WHITE, Square.from("e1"))); // Score : 13
        board.put(Square.from("f1"), new Queen(WHITE, Square.from("f1"))); // Score : 22

        final Game game = new Game(1L, new Board(board), WHITE);
        final Result result = game.createResult();

        assertThat(result.getWhiteScore()).isEqualTo(22);
    }
}
