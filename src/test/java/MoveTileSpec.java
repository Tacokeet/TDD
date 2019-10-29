import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Tile;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTileSpec {
    @Test
    void canOnlyMoveOwnTilesInPlay() {
        Game g = new Game();
        g.setTile(0, 0, new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(0, 1, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.moveTile(0, 1, 0, 2);
    }

    @Test
    void givenTileToMoveWhenQueen_BeeNotInPlayThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(1, 0, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(g.moveTile(0, 0, 0, 1));
    }

    @Test
    void givenTileToMoveWhenTileNotConnectedToAnotherTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new Tile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(0, 1, new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        assertFalse(g.moveTile(0, 0, 1, 0));

    }
}
