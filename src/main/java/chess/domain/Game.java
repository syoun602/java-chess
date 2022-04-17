package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.detail.PieceType;
import chess.domain.piece.detail.Team;
import chess.domain.square.File;
import chess.domain.square.Square;
import java.util.List;
import java.util.Map;

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

    public Result createResult() {
        return Result.byScore(calculateByTeam(Team.BLACK), calculateByTeam(Team.WHITE));
    }

    private Double calculateByTeam(final Team team) {
        Map<File, List<Piece>> files = board.findFilesByTeam(team);
        double total = 0;
        for (File file : files.keySet()) {
            total += getTotal(files, file);
        }
        return total;
    }

    private double getTotal(final Map<File, List<Piece>> files, final File file) {
        final List<Piece> pieces = files.get(file);
        final int pawnCount = pawnCountByFile(pieces);
        final double total = pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();

        return total - pawnPenaltyScore(pawnCount);
    }

    private double pawnPenaltyScore(final int pawnCount) {
        if (pawnCount >= 2) {
            return pawnCount * 0.5;
        }
        return 0;
    }

    private int pawnCountByFile(List<Piece> pieces) {
        return (int) pieces.stream()
                .map(Piece::getPieceType)
                .filter(pieceType -> pieceType == PieceType.PAWN)
                .count();
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
