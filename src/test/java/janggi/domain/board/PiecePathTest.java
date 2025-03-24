package janggi.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecePathTest {

    @Test
    void 다른_포지션과의_row_차이를_구한다() {
        // given
        Position src = new Position(Row.FOUR, Column.FOUR);
        Position dst = new Position(Row.FIVE, Column.FOUR);

        PiecePath path = new PiecePath(src, dst);

        // when
        int rowDifference = path.rowDifference();

        // then
        assertThat(rowDifference).isEqualTo(1);
    }

    @Test
    void 다른_포지션과의_column_차이를_구한다() {
        // given
        Position src = new Position(Row.FOUR, Column.FOUR);
        Position dst = new Position(Row.FIVE, Column.FIVE);
        PiecePath path = new PiecePath(src, dst);

        // when
        int colDifference = path.columnDifference();

        // then
        assertThat(colDifference).isEqualTo(1);
    }

    @Test
    void 두_직선_좌표_사이의_모든_좌표를_반환() {
        Position src = new Position(Row.ONE, Column.ONE);
        Position dst = new Position(Row.ONE, Column.FIVE);
        PiecePath path = new PiecePath(src, dst);

        // when
        List<Position> betweenPositions = path.getBetweenPositions();

        // then
        assertAll(
                () -> assertThat(betweenPositions).hasSize(3),
                () -> assertThat(betweenPositions.get(0)).isEqualTo(new Position(Row.ONE, Column.TWO)),
                () -> assertThat(betweenPositions.get(1)).isEqualTo(new Position(Row.ONE, Column.THREE)),
                () -> assertThat(betweenPositions.get(2)).isEqualTo(new Position(Row.ONE, Column.FOUR))
        );
    }

    @DisplayName("경로가 대각선일떄, 두 좌표 사이의 모든 좌표를 반환")
    @Test
    void DiagonalPath_getBetweenPositions() {
        // given
        Position src = new Position(Row.ONE, Column.ONE);
        Position dst = new Position(Row.FOUR, Column.FOUR);
        PiecePath path = new PiecePath(src, dst);

        // when
        List<Position> positions = path.getBetweenPositions();

        // then
        assertThat(positions).hasSize(2);
        assertThat(positions.get(0)).isEqualTo(Position.of(2,2));
        assertThat(positions.get(1)).isEqualTo(Position.of(3,3));
    }

}
