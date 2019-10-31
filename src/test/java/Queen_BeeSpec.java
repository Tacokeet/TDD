import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Queen_BeeSpec {

    //A
    @Test
    void givenQueen_BeeToMoveWhenMoveMoreThanOneTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertFalse(g.moveTile(0,0,2,1));
    }
    //B
    @Test
    void givenQueen_BeeToMoveWhenMovingOnTopOfAnotherTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertFalse(g.moveTile(0,0,1,-1));
    }


}
