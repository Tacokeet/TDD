import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileClassSpec {
    @Test
    void givenTileColorWhenBlackThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Player.BLACK, a.getPlayer());

    }

    @Test
    void givenTileColorWhenWhiteThenTrue() {
        TileClass a = new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE);
        assertEquals(Hive.Player.WHITE, a.getPlayer());

    }

    @Test
    void givenTileRoleWhenSpiderThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER);
        assertEquals(Hive.Tile.SPIDER, a.getTile());
    }

    @Test
    void givenTileRoleWhenGrasshopperThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);
        assertEquals(Hive.Tile.GRASSHOPPER, a.getTile());
    }

    @Test
    void givenTileRoleWhenSoldier_antThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        assertEquals(Hive.Tile.SOLDIER_ANT, a.getTile());
    }

    @Test
    void givenTileRoleWhenQueen_beeThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Tile.QUEEN_BEE, a.getTile());
    }

    @Test
    void givenTileRoleWhenBeetleThenTrue() {
        TileClass a = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Tile.BEETLE, a.getTile());
    }
}
