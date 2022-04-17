package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.*;
import chess.domain.piece.detail.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class MockGameDaoTest {

    @Test
    @DisplayName("체스 게임을 저장소에 저장한다.")
    void save() {
        MockGameDao dao = new MockGameDao();
        final Member memberOne = new Member(1L, "썬");
        final Member memberTwo = new Member(2L, "알렉스");
        final Board board = new Board(BoardInitializer.create());
        final Participant participant = new Participant(memberOne, memberTwo);
        final ChessGame game = new ChessGame(board, Team.WHITE, participant);

        dao.save(game);

        assertThat(dao.findById(1L).get().getTurn()).isEqualTo(game.getTurn());
    }

    @Test
    @DisplayName("저장소에 저장된 모든 게임을 불러온다.")
    void findAll() {
        MockGameDao dao = new MockGameDao();
        List<ChessGame> games = new ArrayList<>();

        final Member memberOne = new Member(1L, "썬");
        final Member memberTwo = new Member(2L, "알렉스");
        final Board board = new Board(BoardInitializer.create());
        final Participant participant = new Participant(memberOne, memberTwo);

        games.add(new ChessGame(board, Team.WHITE, participant));
        games.add(new ChessGame(board, Team.WHITE, participant));
        games.add(new ChessGame(board, Team.WHITE, participant));

        for (ChessGame game : games) {
            dao.save(game);
        }

        assertThat(dao.findAll().size()).isEqualTo(games.size());
    }
}
