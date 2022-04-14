package chess.piece.detail;

public enum Team {

    WHITE,
    BLACK,
    NONE,
    ;

    public Team findOpponent() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        throw new IllegalStateException("적이 존재할 수 없는 팀입니다.");
    }
}
