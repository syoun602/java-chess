package chess.game;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;
import static chess.position.Position.of;

import chess.piece.*;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {

    public static Map<Position, Piece> init() {
        final Map<Position, Piece> board = new HashMap<>();
        createKings(board);
        createQueen(board);
        createBishop(board);
        createKnight(board);
        createRook(board);
        createPawn(board);

        return new HashMap<>(board);
    }

    private static void createKings(final Map<Position, Piece> board) {
        board.put(of("e", 1), new King(WHITE));
        board.put(of("e", 8), new King(BLACK));
    }

    private static void createQueen(final Map<Position, Piece> board) {
        board.put(of("d", 1), new Queen(WHITE));
        board.put(of("d", 8), new Queen(BLACK));
    }

    private static void createBishop(final Map<Position, Piece> board) {
        board.put(of("c", 1), new Bishop(WHITE));
        board.put(of("f", 1), new Bishop(WHITE));
        board.put(of("c", 8), new Bishop(BLACK));
        board.put(of("f", 8), new Bishop(BLACK));
    }

    private static void createKnight(final Map<Position, Piece> board) {
        board.put(of("b", 1), new Knight(WHITE));
        board.put(of("g", 1), new Knight(WHITE));
        board.put(of("b", 8), new Knight(BLACK));
        board.put(of("g", 8), new Knight(BLACK));
    }

    private static void createRook(final Map<Position, Piece> board) {
        board.put(of("a", 1), new Rook(WHITE));
        board.put(of("h", 1), new Rook(WHITE));
        board.put(of("a", 8), new Rook(BLACK));
        board.put(of("h", 8), new Rook(BLACK));
    }

    private static void createPawn(final Map<Position, Piece> board) {
        for (final Column column : Column.values()) {
            board.put(of(column, Row.of(2)), new Pawn(WHITE));
            board.put(of(column, Row.of(7)), new Pawn(BLACK));
        }
    }
}
