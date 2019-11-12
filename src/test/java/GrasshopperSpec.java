import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrasshopperSpec {

    @Test
    void givenGrasshopperToMoveWhenLegitMoveThenTrue() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertTrue(g.moveTile(1,-1,-1,1));

    }

    @Test
    void givenGrasshopperToMoveWhenMovingToStartingSpaceThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertFalse(g.moveTile(1,-1,1,-1));
    }

    @Test
    void name() {
        Game g = new Game();
        g.isWinner(Hive.Player.BLACK);
    }
}
