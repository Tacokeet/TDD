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
        System.out.println("Comes here");
        System.out.println(fromQ);
        System.out.println(fromR);
        System.out.println(tilesOnSpot);
        setTile(toQ, toR, tilesOnSpot.pop());

        if (tilesOnSpot.empty()) {
            board.remove(fromCoords);
        }
    }

    private void testSetTile(int q, int r, TileClass tileClass, HashMap<ArrayList<Integer>, Stack<TileClass>> testBoard) {
        System.out.println("-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Inside testSetTile Board  " + board);
        System.out.println("Inside testSetTile TestBoard  " + testBoard);
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<TileClass> tileClasses = new Stack<>();
        if (testBoard.get(coords) != null) {
            tileClasses = (Stack<TileClass>) testBoard.get(coords);
        }
        tileClasses.push(tileClass);
        testBoard.put(coords, tileClasses);

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

    Integer testMoveTile(int fromQ, int fromR, int toQ, int toR) {

        System.out.println("Before Board  " + board);
        HashMap<ArrayList<Integer>, Stack<TileClass>> testBoard = copy(board);

        System.out.println("Before TestBoard  " + testBoard);
        System.out.println();

        Integer counter = 0;
        ArrayList<Integer> fromCoords = new ArrayList<>();

        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        Stack<TileClass> testTilesOnSpot = testBoard.get(fromCoords);
        TileClass t = testTilesOnSpot.pop();
        System.out.println(t);
        System.out.println("Just for testSetTile Board  " + board);
        System.out.println("Just for testSetTile TestBoard  " + testBoard);

        testSetTile(toQ, toR, t, testBoard);

        if (testTilesOnSpot.empty()) {
            testBoard.remove(fromCoords);
        }

        for (ArrayList<Integer> neighbour : getNeighbours(toQ, toR)) {
            if (testBoard.get(neighbour) != null) {
                counter++;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("After Board  " + board);
        System.out.println("After TestBoard  " + testBoard);
        return counter;
    }

}

