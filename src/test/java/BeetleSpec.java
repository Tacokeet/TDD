import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeetleSpec {

    //B
    @Test
    void givenBeetleToMoveWhenMovedOneTileThenTrueElseFalse() {
        Game g = new Game();
        g.setTile(0, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.SPIDER));
        assertFalse(g.moveTile(-1, 1, 1, -1));
        assertTrue(g.moveTile(-1, 1, 0, 0));
    }


}
