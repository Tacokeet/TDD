package nl.hanze.hive;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Collections;
import java.util.stream.Collectors;

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
        if (tileClass.getTile() == Hive.Tile.QUEEN_BEE) {
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

    private void testSetTile(int q, int r, TileClass tileClass) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<TileClass> tileClasses = new Stack<>();
        if (board.get(coords) != null) {
            tileClasses = (Stack<TileClass>) board.get(coords);
        }
        tileClasses.push(tileClass);
        board.put(coords, tileClasses);

    }

    public static HashMap<ArrayList<Integer>, Stack<TileClass>> copy(HashMap<ArrayList<Integer>, Stack<TileClass>> original) {
        ArrayList<TileClass> copyList = new ArrayList<>();
        Stack<TileClass> copyStack = new Stack<>();
        HashMap<ArrayList<Integer>, Stack<TileClass>> testBoard = new HashMap<>();
        for (HashMap.Entry<ArrayList<Integer>, Stack<TileClass>> entry : original.entrySet()) {
            for (TileClass t : entry.getValue()){
                copyList.add(t);
            }
            Collections.reverse(copyList);
            for (TileClass t : copyList){
                copyStack.push(t);
            }

            //PERFECT
            System.out.println(entry.getKey());
            System.out.println(copyStack);

            testBoard.put(entry.getKey(), copyStack);
            System.out.println(testBoard);

            copyList.clear();
            copyStack.clear();
            System.out.println("-=-=-=-=-==-=-=-==-=-=-===--=-=-=-=-=-=-=-=-=-=-=-=-=-=--=");
        }
        System.out.println(testBoard);
        return testBoard;
    }

    public boolean testMoveTile(int fromQ, int fromR, int toQ, int toR) {
        Integer counter = 0;
        ArrayList<Integer> fromCoords = new ArrayList<>();

        fromCoords.add(fromQ);
        fromCoords.add(fromR);

        Stack<TileClass> testTilesOnSpot = board.get(fromCoords);
        TileClass t = testTilesOnSpot.pop();

        testSetTile(toQ, toR, t);

        if (testTilesOnSpot.empty()) {
            board.remove(fromCoords);
        }

        for (ArrayList<Integer> neighbour : getNeighbours(toQ, toR)) {
            if (board.get(neighbour) != null) {
                counter++;
            }
        }


        ArrayList<ArrayList<Integer>> tileList = new ArrayList<>();
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        tileList.add(toCoords);
        for (int i = 0; i < tileList.size() ; i++){
            for (ArrayList<Integer> neighbour : getNeighbours(tileList.get(i).get(0), tileList.get(i).get(1))){
                if (board.get(neighbour) != null){
                    if (!tileList.contains(neighbour)){
                        System.out.println(neighbour + "Is not inside TileList");
                        tileList.add(neighbour);
                    }
                }
            }
        }
        if (tileList.size() < board.size()){
            System.out.println("It's not connected anymore!");
            //Restoring the board
            testTilesOnSpot.push(t);
            board.put(fromCoords, testTilesOnSpot);
            board.remove(toCoords);
            return false;
        }

        if (counter == 0){
            System.out.println("Move 5");
            //Restoring the board
            testTilesOnSpot.push(t);
            board.put(fromCoords, testTilesOnSpot);
            board.remove(toCoords);
            return false;
        }

        return true;
    }

}

