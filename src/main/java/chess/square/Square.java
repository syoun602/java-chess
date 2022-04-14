package chess.square;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Square {

    private static final Map<String, Square> squareCache = new HashMap<>();

    static {
        for (File file : File.values()) {
            initializeSquareCache(file);
        }
    }

    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String rawSquare) {
        validateLength(rawSquare);
        validateExist(rawSquare);
        return squareCache.get(rawSquare);
    }

    private static void initializeSquareCache(final File file) {
        for (Rank rank : Rank.values()) {
            squareCache.put(file.getValue() + rank.getValue(), new Square(file, rank));
        }
    }

    private static void validateExist(final String rawSquare) {
        if (!squareCache.containsKey(rawSquare)) {
            throw new IllegalArgumentException("존재하지 않는 스퀘어입니다.");
        }
    }

    private static void validateLength(final String rawSquare) {
        if (rawSquare.length() != 2) {
            throw new IllegalArgumentException("스퀘어의 길이가 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        final Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
