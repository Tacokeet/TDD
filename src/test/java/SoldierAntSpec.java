import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierAntSpec {

    //A
    @Test
    void givenSoldierAntToMove() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        if (g.moveTile(1,-1,-1,-2)){
            System.out.println("Succesvol!");
        }

    }
    //B
    @Test
    void givenSoldierAntToMoveWhenThen() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertFalse(g.moveTile(0,0,1,-1));
    }


}
