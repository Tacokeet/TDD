import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.TileClass;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class BoardSpec {

    //B
    @Test
    void givenTileWhenTileHasSixNeighboursThenTrue() {
        Board b = new Board();
        assertEquals(6, b.getNeighbours(0, 0).size());
    }

    //C
    @Test
    void givenNewBoardWhenEmptyThenTrue() {
        Board b = new Board();
        assertTrue(b.getBoard().isEmpty());
    }

    //D
    @Test
    void givenTileWhenPreciselyInOneSpotThenTrue() {
        Board b = new Board();
        TileClass t = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);
        b.setTile(0, 0, new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE));
        assertEquals(t.getTile(), b.getTilesOnSpot(0, 0).peek().getTile());
    }

    //E
    @Test
    void givenTileToMoveWhenMovedThenTrue() {
        TileClass tileClass = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);

        Board expectBoard = new Board();
        expectBoard.setTile(1, 0, tileClass);

        Board moveBoard = new Board();
        moveBoard.setTile(0, 0, tileClass);
        moveBoard.moveTile(0, 0, 1, 0);

        assertEquals(expectBoard.getBoard(), moveBoard.getBoard());
    }

    //F-1
    @Test
    void givenSpotAndTwoTilesWhenTileOnAnotherTileThenTrue() {
        Stack<TileClass> expectStack = new Stack<>();
        TileClass t1 = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);
        TileClass t2 = new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER);
        expectStack.push(t1);
        expectStack.push(t2);

        Board b = new Board();
        b.setTile(0, 0, t1);
        b.setTile(0, 0, t2);

        assertEquals(expectStack, b.getTilesOnSpot(0, 0));
    }

    //F-2
    @Test
    void givenSpotWithMultipleTilesWhenTileMovesOnlyTopTileCanMoveThenTrue() {
        TileClass t1 = new TileClass(Hive.Player.BLACK, Hive.Tile.BEETLE);
        TileClass t2 = new TileClass(Hive.Player.BLACK, Hive.Tile.SPIDER);

        Board expectBoard = new Board();
        expectBoard.setTile(0, 0, t1);
        expectBoard.setTile(1, 0, t2);

        Board testBoard = new Board();
        testBoard.setTile(0, 0, t1);
        testBoard.setTile(0, 0, t2);
        testBoard.moveTile(0, 0, 1, 0);

        assertEquals(expectBoard.getBoard(), testBoard.getBoard());
    }
}

