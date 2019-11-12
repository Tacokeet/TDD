import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpiderSpec {

//    @Test
//    void givenSpiderToMoveWhenLegitMoveThenTrue() {
//        Game g = new Game();
//        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
//        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
//        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.SPIDER));
//        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
//        assertTrue(g.moveTile(1,-1,-1,1));
//
//    }

    @Test
    void givenSpiderToMoveWhenMovingToStartingSpaceThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.SPIDER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        assertFalse(g.moveTile(1,-1,1,-1));
    }


}
