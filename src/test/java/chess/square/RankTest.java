package chess.square;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("랭크 이름로 자신의 숫자를 반환한다.")
    @ParameterizedTest
    @CsvSource({"ONE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8"})
    void getRank(final String name, final int value) {
        Rank enumName = Rank.valueOf(name);
        assertThat(enumName.getValue()).isEqualTo(value);
    }

    @DisplayName("숫자로 자신의 랭크를 반환한다.")
    @ParameterizedTest
    @CsvSource({"ONE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8"})
    void from(final String name, final int value) {
        Rank rank = Rank.from(value);
        Rank enumName = Rank.valueOf(name);
        assertThat(rank).isSameAs(enumName);
    }

    @DisplayName("허용되지 않은 숫자로 랭크를 생성할 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void invalidRankException(final int value) {
        Assertions.assertThatThrownBy(() -> Rank.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 랭크입니다.");
    }
}
