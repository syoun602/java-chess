package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;

public class Board {
    private Pieces pieces;
    private Map<Position, Piece> pieceByPosition;

    public Board(Pieces pieces) {
        for (Position position : Position.all()) {
            pieceByPosition = new HashMap<>();
            pieceByPosition.put(position, new Empty());
        }
        this.pieces = pieces;
    }

    public Board(Map<Position, Piece> maps) {
        this.pieceByPosition = maps;
        this.pieces = new Pieces();
    }

    public void print() {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                System.out.print(pieceByPosition.get(Position.of(column, row))
                                                .display());
            }
            System.out.println();
        }
    }

    public void move(Color color, Position from, Position to) {
        Piece start = pickStartPiece(color, from);
        Piece piece = pieces.getPieceOf(to);
        if (piece.isEmpty()) {
            start.moveToEmpty(to, pieces);
            return;
        }
        if (piece.isSameColor(color)) {
            throw new IllegalArgumentException();
        }
        start.moveForKill(to, pieces);
        pieces.delete(piece);
    }

    public void move2(Color color, Position from, Position to) {
        Piece fromPiece = pieceByPosition.get(from);
        Piece toPiece = pieceByPosition.get(to);

        List<Piece> between = Arrays.asList(new Empty());
        if (toPiece.isEmpty()) {
            fromPiece.moveToEmpty(to, pieces);
            return;
        }
        if (toPiece.isSameColor(color)) {
            throw new IllegalArgumentException();
        }
        fromPiece.moveForKill(to, pieces);
        pieces.delete(toPiece);
    }
    public Piece pickStartPiece(Color color, Position position) {
        Piece piece = pieces.getPieceOf(position);
        if (piece.isSameColor(color)) {
            return piece;
        }
        throw new IllegalArgumentException();
    }

    public boolean isNotEnd() {
        return pieces.toList()
                     .stream()
                     .filter(Piece::isKing)
                     .count() == 2;
    }

    public double score(Color color) {
        return pieces.score(color);
    }

    public Map<Position, Piece> allPieces() {
        Map<Position, Piece> pieceMap = new HashMap<>();
        for (Position position : Position.all()) {
            Piece piece = new Empty();
            if (pieces.hasPieceOf(position)) {
                piece = pieces.getPieceOf(position);
            }
            pieceMap.put(position, piece);
        }
        return pieceMap;
    }
}
