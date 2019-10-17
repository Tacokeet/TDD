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
        ArrayList<Tile> startingTiles = new ArrayList<>();
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
        for (int i = 0; i <= startingTiles.size() - 1; i++) {
            assertEquals(startingTiles.get(i).getTile(), p.getStartingTiles().get(i).getTile());
            assertEquals(startingTiles.get(i).getPlayer(), p.getStartingTiles().get(i).getPlayer());
        }
    }
}
