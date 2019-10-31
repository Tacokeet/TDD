import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlideTileClassSpec {

    //B
    @Test
    void givenTileToMoveWhenMovingBetweenOtherTilesThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        g.setTile(2, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        assertFalse(g.moveTile(-1, 0, 0, -1));
    }

    //C
    @Test
    void givenTileToMoveWhenMovingLosingContactThenFalse() {
        Game g = new Game();
        g.setTile(0, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.SPIDER));

//        g.setTile(-2,0, new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
//        g.moveTile(1, -1, 0, 0);

        g.moveTile(-1, 1, 0, 1);

    }
}
