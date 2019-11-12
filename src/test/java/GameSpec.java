import nl.hanze.hive.Game;
import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSpec {

    //A
    @Test
    void givenGameStartWhenWhiteBeginsThenTrue() {
        Game g = new Game();
        assertEquals(Hive.Player.WHITE, g.getCurrentPlayer());
    }

    //B
    @Test
    void givenPlayerTurnWhenPlayerHasDoneItsTurnOpponentHasItsTurnThenTrue() {
        Game g = new Game();

        g.setTile(0, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE));
        assertEquals(Hive.Player.BLACK, g.getCurrentPlayer());

        g.setTile(1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertEquals(Hive.Player.WHITE, g.getCurrentPlayer());

        g.playerPass();
        assertEquals(Hive.Player.BLACK, g.getCurrentPlayer());
    }

    //C
    @Test
    void givenQueen_BeeWhenOpponentQueen_BeeSurroundedByTilesThenPlayerWinsThenTrue() {
        Game g = new Game();
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        g.setTile(1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertEquals(Hive.Player.WHITE, g.getWinner());
        g.isWinner(Hive.Player.WHITE);
    }

    //D
    @Test
    void givenVictoryWhenTwoPlayersWinThenDrawThenTrue() {
        Game g = new Game();
        g.setTile(1, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)); //Whites turn
        g.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE)); // Blacks turn
        g.setTile(2, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE)); // Whites Turn
        g.setTile(-1, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE)); // Blacks turn
        g.setTile(3, -1, new TileClass(Hive.Player.WHITE, Hive.Tile.BEETLE)); // Whites Turn
        g.setTile(0, -1, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE)); // Blacks turn
        g.setTile(2, 1, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER)); // Whites turn
        g.setTile(-1, 1, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER)); // Blacks turn
        g.setTile(3, 0, new TileClass(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));// Whites turn
        g.setTile(-1, 2, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));   // Blacks turn
        g.setTile(1, -2, new TileClass(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));   // Whites turn

        g.moveTile(-1, 2, 0, 1);  // Blacks turn
        g.moveTile(3, -1, 2, -1); // Whites turn
        g.moveTile(1, -2, 1, -1);  // Blacks turn
        g.moveTile(2, 1, 1, 1); // Whites turn


        assertEquals(true, g.getWinner());
    }
}
