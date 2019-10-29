package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Board {

    private HashMap<ArrayList<Integer>, Stack<TileClass>> board = new HashMap<>();
    private HashMap<Hive.Player, ArrayList<Integer>> queenList = new HashMap<>();

    public HashMap<ArrayList<Integer>, Stack<TileClass>> getBoard() {
        return board;
    }

    public Stack<TileClass> getTilesOnSpot(int q, int r) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        return board.get(coords);
    }

    public HashMap<Hive.Player, ArrayList<Integer>> getQueenList() {
        return queenList;
    }

    public ArrayList<ArrayList<Integer>> getNeighbours(int q, int r) {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            neighbours.add(new ArrayList());
        }
        // Rechtsonder
        neighbours.get(0).add(q);
        neighbours.get(0).add(r + 1);
        // Rechtsboven
        neighbours.get(1).add(q + 1);
        neighbours.get(1).add(r - 1);
        // Rechts
        neighbours.get(2).add(q + 1);
        neighbours.get(2).add(r);
        // Linksboven
        neighbours.get(3).add(q);
        neighbours.get(3).add(r - 1);
        // Linksonder
        neighbours.get(4).add(q - 1);
        neighbours.get(4).add(r + 1);
        // Links
        neighbours.get(5).add(q - 1);
        neighbours.get(5).add(r);
        return neighbours;
    }

    public void setTile(int q, int r, TileClass tileClass) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<TileClass> tileClasses = new Stack<>();
        if (board.get(coords) != null) {
            tileClasses = board.get(coords);
        }
        tileClasses.push(tileClass);
        board.put(coords, tileClasses);
        if (tileClass.getTile() == Hive.Tile.QUEEN_BEE){
            queenList.put(tileClass.getPlayer(), coords);
        }
    }

    public void moveTile(int fromQ, int fromR, int toQ, int toR) {
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);

        Stack<TileClass> tilesOnSpot = getTilesOnSpot(fromQ, fromR);
        setTile(toQ, toR, tilesOnSpot.pop());

        if (tilesOnSpot.empty()) {
            board.remove(fromCoords);
        }
    }

}

