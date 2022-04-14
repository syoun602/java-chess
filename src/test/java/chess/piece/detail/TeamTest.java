package chess.piece.detail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

    @DisplayName("팀에 따른 적을 반환한다.")
    @ParameterizedTest
    @CsvSource({"WHITE,BLACK", "BLACK,WHITE"})
    void findOpponent(final String rawTeam, final String rawOpponent) {
        Team team = Team.valueOf(rawTeam);
        Team opponent = Team.valueOf(rawOpponent);
        assertThat(team.findOpponent()).isSameAs(opponent);
    }

    @DisplayName("NONE으로 적팀을 찾을 시 예외가 발생한다.")
    @Test
    void noneOpponentException() {
        Team team = Team.NONE;
        assertThatThrownBy(team::findOpponent)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("적이 존재할 수 없는 팀입니다.");
    }
}
