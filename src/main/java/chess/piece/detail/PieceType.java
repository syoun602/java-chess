package chess.piece.detail;

public enum PieceType {
    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K"),
    BLANK("."),
    ;

    private final String symbol;

    PieceType(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
