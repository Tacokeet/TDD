import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierAntSpec {

    @Test
    void givenSoldierAntToMoveWhenLegitMoveThenTrue() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        assertTrue(g.moveTile(1,-1,-1,-2));

    }


    @Test
    void givenSoldierAntToMoveWhenMovingToStartingSpaceThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        assertFalse(g.moveTile(1,-1,1,-1));
    }


}
