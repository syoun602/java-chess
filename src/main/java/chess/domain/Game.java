package chess.domain;

import chess.domain.piece.detail.Team;
import chess.domain.square.Square;

public class Game {

    private Long id;
    private final Board board;
    private Team turn;

    public Game(final Long id, final Board board, final Team turn) {
        this.id = id;
        this.board = board;
        this.turn = turn;
    }

    public static Game initGame() {
        return new Game(null, new Board(BoardInitializer.create()), Team.WHITE);
    }

    public void move(Square from, Square to) {
        validateTurn(from);
        board.move(from, to);
        this.turn = turn.reverse();
    }

    public void terminate() {
        turn = Team.NONE;
    }

    private void validateTurn(Square from) {
        if (turn == Team.NONE) {
            throw new IllegalStateException("이미 종료된 게임입니다.");
        }

        if (!board.isSameTeam(from, turn)) {
            throw new IllegalArgumentException(
                    String.format("현재는 %s의 차례입니다. 차례에 맞는 기물을 움직여주세요.", turn.name())
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return turn;
    }
}
