import nl.hanze.hive.Hive;
import nl.hanze.hive.PlayerClass;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerClassSpec {
    @Test
    void givenStartingPlayerTilesWhenStartingSetThenTrue() {
        PlayerClass p = new PlayerClass(Hive.Player.BLACK);
        ArrayList<TileClass> startingTileClasses = new ArrayList<>();
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        startingTileClasses.add(new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        for (int i = 0; i <= startingTileClasses.size() - 1; i++) {
            assertEquals(startingTileClasses.get(i).getTile(), p.getStartingTileClasses().get(i).getTile());
            assertEquals(startingTileClasses.get(i).getPlayer(), p.getStartingTileClasses().get(i).getPlayer());
        }
    }
}
