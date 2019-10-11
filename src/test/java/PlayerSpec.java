import nl.hanze.hive.Hive;
import nl.hanze.hive.Player;
import nl.hanze.hive.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSpec {
    @Test
    void givenStartingPlayerTilesWhenStartingSetThenTrue() {
        Player p = new Player(Hive.Player.BLACK);
        ArrayList<Tile> startingTiles = new ArrayList<Tile>();

        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        startingTiles.add(new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertEquals(startingTiles, p.getStartingTiles());
    }
}
