package chess.square;

import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h"),
    ;

    private final String value;

    File(final String value) {
        this.value = value;
    }

    public static File from(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));
    }

    public String getValue() {
        return value;
    }
}
