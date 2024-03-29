package nl.hanze.hive;


import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;

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

    public void boardPut(ArrayList<Integer> coords, Stack<TileClass> stack){
        board.put(coords, stack);
    }

    public void boardRemove(ArrayList<Integer> coords){
        board.remove(coords);
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

    public boolean evaluateMoveTile(int fromQ, int fromR, int toQ, int toR, TileClass tile){
        System.out.println(fromQ + " : " + fromR +  " ----->  " + toQ + " : " + toR);
        Stack<TileClass> tileClasses = new Stack<>();
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);

        if (tile.getTile() == Hive.Tile.SOLDIER_ANT){
            tileClasses.push(tile);
            board.put(fromCoords, tileClasses);
            boolean testResult = testMoveTile(fromQ, fromR, toQ ,toR);
            board.remove(fromCoords);
            return testResult;
        }
        if (tile.getTile() == Hive.Tile.SPIDER){
            tileClasses.push(tile);
            board.put(fromCoords, tileClasses);
            boolean testResult = testMoveTile(fromQ, fromR, toQ ,toR);
            board.remove(fromCoords);
            return testResult;
        }
        return true;
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

    private boolean keepContact(int fromQ, int fromR, int toQ, int toR) {

        // keep contact while sliding
        if (fromQ + 1 == toQ && fromR == toR) {
            System.out.println("Moved Rechts");
            if (getTilesOnSpot(fromQ + 1, fromR - 1) == null && getTilesOnSpot(fromQ, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ - 1 == toQ && fromR == toR) {
            System.out.println("Moved Links");
            if (getTilesOnSpot(fromQ, fromR - 1) == null && getTilesOnSpot(fromQ - 1, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ - 1 == toQ && fromR + 1 == toR) {
            System.out.println("Moved Linksonder");
            if (getTilesOnSpot(fromQ, fromR + 1) == null && getTilesOnSpot(fromQ - 1, fromR) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ == toQ && fromR + 1 == toR) {
            System.out.println("Moved Rechtsonder");
            if (getTilesOnSpot(fromQ + 1, fromR) == null && getTilesOnSpot(fromQ - 1, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ == toQ && fromR - 1 == toR) {
            System.out.println("Moved Linksboven");
            if (getTilesOnSpot(fromQ - 1, fromR) == null && getTilesOnSpot(fromQ + 1, fromR - 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ + 1 == toQ && fromR - 1 == toR) {
            System.out.println("Moved Rechtsboven");
            if (getTilesOnSpot(fromQ, fromR - 1) == null && getTilesOnSpot(fromQ + 1, fromR) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        return true;
    }
    private boolean maySlide(int fromQ, int fromR, int toQ, int toR) {
        // Sliding a tile
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        Integer toTiles;

        ArrayList<ArrayList<Integer>> fromNeighbours = new ArrayList<>();
        ArrayList<ArrayList<Integer>> toNeighbours = new ArrayList<>();

        if (getNeighbours(fromQ, fromR).contains(toCoords)) {
            // They are connected
            for (ArrayList<Integer> neighbour : getNeighbours(fromQ, fromR)) {
                if (getBoard().get(neighbour) != null) {
                    fromNeighbours.add(neighbour);
                }
            }
            for (ArrayList<Integer> neighbour : getNeighbours(toQ, toR)) {
                if (getBoard().get(neighbour) != null) {
                    toNeighbours.add(neighbour);
                }
            }
            toNeighbours.retainAll(fromNeighbours);
            if (toNeighbours.isEmpty() || toNeighbours.size() == 1) {
                return true;
            }
            if (getTilesOnSpot(toQ, toR) == null) {
                toTiles = 0;
            } else {
                toTiles = getTilesOnSpot(toQ, toR).size();
            }
            if (Math.min(getTilesOnSpot(toNeighbours.get(0).get(0), toNeighbours.get(0).get(1)).size(), getTilesOnSpot(toNeighbours.get(1).get(0), toNeighbours.get(1).get(0)).size()) <=
                    Math.max(getTilesOnSpot(fromQ, fromR).size() - 1, toTiles)) {
                // It can slide to the toQ,toR coordinate
                return true;
            }
        }
        System.out.println();
        return false;
    }

    public boolean testMoveTile(int fromQ, int fromR, int toQ, int toR) {
        Integer counter = 0;
        ArrayList<Integer> fromCoords = new ArrayList<>();

        fromCoords.add(fromQ);
        fromCoords.add(fromR);


        if (getTilesOnSpot(fromQ, fromR).peek().getTile() != Hive.Tile.BEETLE){
            if (getTilesOnSpot(toQ, toR) != null) {
                System.out.println("Can't move to tile because there is already a tile there");
                return false;
            }
        }

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
        //Restoring the board
        testTilesOnSpot.push(t);
        board.put(fromCoords, testTilesOnSpot);
        board.remove(toCoords);
        return true;
    }

}

