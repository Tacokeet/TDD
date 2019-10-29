import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTileClassSpec {

    //A
    @Test
    void givenTileSetWhenNotInStartingTilesThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(g.setTile(1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE)));
    }

    //B
    @Test
    void givenTileSetWhenPlacedOnAnotherTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE)));
    }

    //C
    @Test
    void givenTileSetWhenPlacedNotNextToAnotherTileThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertFalse(g.setTile(2, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER)));
    }

    //D
    @Test
    void givenTileSetWhenBothColorStonesAreInPlayAndSetTileNextToOpponentColorThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        assertFalse(g.setTile(-1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE)));
    }

    //E
    @Test
    void givenThreeStonesInPlayWhenNextTileMustBeQueen_BeeIfNotThenFalse() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(2, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(-2, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(3, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.SPIDER));

        assertFalse(g.setTile(-3,0, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER)));

    }
}
