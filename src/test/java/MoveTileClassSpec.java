import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTileClassSpec {
    @Test
    void canOnlyMoveOwnTilesInPlay() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(0, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.moveTile(0, 1, 0, 2);
    }

    @Test
    void givenTileToMoveWhenQueen_BeeNotInPlayThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(g.moveTile(0, 0, 0, 1));
    }

    @Test
    void givenTileToMoveWhenTileNotConnectedToAnotherTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(0, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        assertFalse(g.moveTile(0, 0, 1, 0));

    }
}