package chess.piece.detail;

import static chess.piece.detail.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

class DirectionTest {

    @Test
    void getWhitePawnDirections() {
        assertThat(Direction.getWhitePawnDirections())
                .isEqualTo(List.of(N, NW, NE));

    }

    @Test
    void getBlackPawnDirections() {
        assertThat(Direction.getBlackPawnDirections())
                .isEqualTo(List.of(S, SW, SE));
    }

    @Test
    void getEveryDirections() {
        assertThat(Direction.getEveryDirections())
                .isEqualTo(List.of(N, NE, E, SE, S, SW, W, NW));
    }

    @Test
    void getVerticalAndHorizontalDirections() {
        assertThat(Direction.getVerticalAndHorizontalDirections())
                .isEqualTo(List.of(N, E, S, W));
    }

    @Test
    void getDiagonalDirections() {
        assertThat(Direction.getDiagonalDirections())
                .isEqualTo(List.of(NW, NE, SE, SW));

    }

    @Test
    void getKnightDirections() {
        assertThat(Direction.getKnightDirections())
                .isEqualTo(List.of(NNE, ENE, ESE, SSE, SSW, WSW, WNW, NNW));

    }

    @ParameterizedTest
    @CsvSource({
            "N,0", "S,0", "E,1", "W,-1",
            "NE,1", "SE,1", "SW,-1", "NW,-1",
            "NNE,1", "ENE,2", "ESE,2", "SSE,1",
            "SSW,-1", "WSW,-2", "WNW,-2", "NNW,-1",
            "NONE,0"
    })
    void getXDegree(final String rawDirection, final int xDegree) {
        Direction direction = Direction.valueOf(rawDirection);
        assertThat(direction.getXDegree()).isEqualTo(xDegree);
    }

    @ParameterizedTest
    @CsvSource({
            "N,1", "S,-1", "E,0", "W,0",
            "NE,1", "SE,-1", "SW,-1", "NW,1",
            "NNE,2", "ENE,1", "ESE,-1", "SSE,-2",
            "SSW,-2", "WSW,-1", "WNW,1", "NNW,2",
            "NONE,0"
    })
    void getYDegree(final String rawDirection, final int yDegree) {
        Direction direction = Direction.valueOf(rawDirection);
        assertThat(direction.getYDegree()).isEqualTo(yDegree);
    }
}
