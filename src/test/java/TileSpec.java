import nl.hanze.hive.Hive;
import nl.hanze.hive.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileSpec {
    @Test
    void givenTileColorWhenBlackThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Player.BLACK, a.getPlayer());

    }

    @Test
    void givenTileColorWhenWhiteThenTrue() {
        Tile a = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        assertEquals(Hive.Player.WHITE, a.getPlayer());

    }

    @Test
    void givenTileRoleWhenSpiderThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        assertEquals(Hive.Tile.SPIDER, a.getTile());
    }

    @Test
    void givenTileRoleWhenGrasshopperThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);
        assertEquals(Hive.Tile.GRASSHOPPER, a.getTile());
    }

    @Test
    void givenTileRoleWhenSoldier_antThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        assertEquals(Hive.Tile.SOLDIER_ANT, a.getTile());
    }

    @Test
    void givenTileRoleWhenQueen_beeThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Tile.QUEEN_BEE, a.getTile());
    }

    @Test
    void givenTileRoleWhenBeetleThenTrue() {
        Tile a = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Tile.BEETLE, a.getTile());
    }
}
