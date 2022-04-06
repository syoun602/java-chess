package chess.dao;

import chess.piece.detail.Color;

public interface BoardDao {

    int save(final Color color);

    Color findTurnById(final int id);

    Color load();

    int findLastlyUsedBoard();

    void updateById(int boardId, Color turn);
}
