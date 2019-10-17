import javafx.beans.binding.When;
import nl.hanze.hive.Game;

import nl.hanze.hive.Hive;
import nl.hanze.hive.Tile;
import org.junit.Before;
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

        g.setTile(0, 0, new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        assertEquals(Hive.Player.BLACK, g.getCurrentPlayer());

        g.moveTile(0, 0, 1, 0);
        assertEquals(Hive.Player.WHITE, g.getCurrentPlayer());

        g.pass();
        assertEquals(Hive.Player.BLACK, g.getCurrentPlayer());
    }

    //C
    @Test
    void givenQueen_BeeWhenOpponentQueen_BeeSurroundedByTilesThenPlayerWinsThenTrue() {
        Game g = new Game();
        g.setTile(0, 0, new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, 1, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, -1, new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 0, new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 1, new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        g.setTile(1, 0, new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        assertEquals("WHITE", g.getWinner());
    }

    //D
    @Test
    void givenVictoryWhenTwoPlayersWinThenDrawThenTrue() {
        Game g = new Game();
        g.setTile(0, 0, new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
        g.setTile(1, -1, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, 1, new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        g.setTile(0, -1, new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 0, new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER));
        g.setTile(-1, 1, new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));
        g.setTile(1, 0, new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));


        g.setTile(-4,0,new Tile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
        g.setTile(-3,0,new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(-3,-1,new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        g.setTile(-4,-1,new Tile(Hive.Player.WHITE, Hive.Tile.SPIDER));
        g.setTile(-4,1,new Tile(Hive.Player.WHITE, Hive.Tile.SPIDER));
        g.setTile(-5,1,new Tile(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        g.setTile(-5,0,new Tile(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
        assertEquals("DRAW", g.getWinner());
    }
}
