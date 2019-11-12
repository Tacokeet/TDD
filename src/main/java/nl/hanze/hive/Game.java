package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.lang.Math.toIntExact;


public class Game implements Hive {
    private Hive.Player currentPlayer = Hive.Player.WHITE;
    private Board board = new Board();
    private Player winner = null;
    private boolean draw = false;
    private HashMap<Hive.Player, Integer> victory = new HashMap<>();
    private PlayerClass blackPlayer = new PlayerClass(Hive.Player.BLACK);
    private PlayerClass whitePlayer = new PlayerClass(Hive.Player.WHITE);
    private Boolean blackTileInPlay = false;
    private Boolean whiteTileInPlay = false;
    private Boolean blackQueenBeeInPlay = false;
    private Boolean whiteQueenBeeInPlay = false;
    private ArrayList<TileClass> blackTilesInPlay = new ArrayList<TileClass>();
    private ArrayList<TileClass> whiteTilesInPlay = new ArrayList<TileClass>();

    public Game() {
        victory.put(Hive.Player.BLACK, 0);
        victory.put(Hive.Player.WHITE, 0);
    }

    public Hive.Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    private static <K, V> Hive.Player getKey(HashMap<Hive.Player, ArrayList<Integer>> map, ArrayList<Integer> value) {
        return map.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(HashMap.Entry::getKey)
                .findFirst().get();
    }

    public void putTile(int fromQ, int fromR, TileClass tile) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(fromQ);
        a.add(fromR);
        Stack<TileClass> s = new Stack<>();
        s.push(tile);
        board.boardPut(a, s);
    }

    public void removeTile(int fromQ, int fromR){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(fromQ);
        a.add(fromR);
        board.boardRemove(a);
    }

    private void checkVictory() {
        for (ArrayList<Integer> coords : board.getQueenList().values()) {
            for (ArrayList<Integer> neighbour : board.getNeighbours(coords.get(0), coords.get(1))) {
                if (board.getBoard().get(neighbour) == null) {
                    victory.put(getKey(board.getQueenList(), coords), victory.get(getKey(board.getQueenList(), coords)));
                } else if (board.getBoard().get(neighbour) != null) {
                    victory.put(getKey(board.getQueenList(), coords), victory.get(getKey(board.getQueenList(), coords)) + 1);
                }
            }
        }
        if (victory.get(Hive.Player.BLACK) == 6 && victory.get(Hive.Player.WHITE) == 6) {
            draw = true;
        } else if (victory.get(Hive.Player.BLACK) == 6) {
            winner = Player.WHITE;
        } else if (victory.get(Hive.Player.WHITE) == 6) {
            winner = Player.BLACK;
        }
        victory.put(Hive.Player.BLACK, 0);
        victory.put(Hive.Player.WHITE, 0);
    }


    public boolean setTile(int q, int r, TileClass tile) {
        Integer counter = 0;
        boolean inNotPlayedTiles = false;
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        if (tile.getPlayer() == Hive.Player.BLACK) {
            if (tile.getTile() == Hive.Tile.QUEEN_BEE) {
                blackQueenBeeInPlay = true;
            }
            if (blackPlayer.getStartingTileClasses().size() == 8 && !blackQueenBeeInPlay) {
                if (tile.getTile() != Hive.Tile.QUEEN_BEE) {
                    System.out.println(1);
                    return false;
                }
            }
            for (TileClass t : blackPlayer.getStartingTileClasses()) {
                if (t.getTile() == tile.getTile()) {
                    inNotPlayedTiles = true;
                }
            }
        } else if (tile.getPlayer() == Hive.Player.WHITE) {
            if (tile.getTile() == Hive.Tile.QUEEN_BEE) {
                whiteQueenBeeInPlay = true;
            }
            if (whitePlayer.getStartingTileClasses().size() == 8 && !whiteQueenBeeInPlay) {
                if (tile.getTile() != Hive.Tile.QUEEN_BEE) {
                    System.out.println(2);
                    return false;
                }
            }
            for (TileClass t : whitePlayer.getStartingTileClasses()) {
                if (t.getTile() == tile.getTile()) {
                    inNotPlayedTiles = true;
                }
            }
        }
        if (!inNotPlayedTiles) {
            System.out.println(3);
            return false;
        }
        if (board.getTilesOnSpot(q, r) != null) {
            System.out.println(4);
            return false;
        }
        for (ArrayList<Integer> neighbour : board.getNeighbours(q, r)) {
            if (board.getBoard().isEmpty()) {
                break;
            }
            if (board.getBoard().get(neighbour) != null) {
                counter++;
                if (blackTileInPlay && whiteTileInPlay) {
                    for (TileClass t : board.getBoard().get(neighbour)) {
                        if (t.getPlayer() != tile.getPlayer()) {
                            System.out.println(5);
                            return false;
                        }
                    }
                }
            }
        }
        if (counter == 0 && !board.getBoard().isEmpty()) {
            System.out.println(6);
            return false;
        }
        if (tile.getPlayer() == Hive.Player.BLACK) {
            blackTileInPlay = true;
        } else {
            whiteTileInPlay = true;
        }

        if (tile.getPlayer() == Hive.Player.BLACK) {
            blackTilesInPlay.add(tile);
            blackPlayer.removeTile(tile);
        } else if (tile.getPlayer() == Hive.Player.WHITE) {
            whiteTilesInPlay.add(tile);
            whitePlayer.removeTile(tile);
        }
        board.setTile(q, r, tile);
        checkVictory();
        playerPass();
        return true;
    }

    private boolean testSoldierAnt(ArrayList<ArrayList<Integer>> legitMoves, int fromQ, int fromR, int toQ, int toR, TileClass tile) {
        //    board.testMoveTile(move.get(0), move.get(1), neighbour.get(0), neighbour.get(1))
        ArrayList<ArrayList<Integer>> newLegitMoves = new ArrayList<>();
        for (ArrayList<Integer> move : legitMoves) {
//            board.moveTile(fromQ, fromR, move.get(0), move.get(1));
            if (move.get(0) == toQ && move.get(1) == toR) {
                System.out.println("Its a legit move");
                return true;
            } else {
                for (ArrayList<Integer> neighbour : board.getNeighbours(move.get(0), move.get(1))) {
                    if (neighbour.get(0) == fromQ && neighbour.get(1) == fromR) {
                        continue;
                    } else if (neighbour.get(0) == 0 && neighbour.get(1) == -1) {

                        ArrayList<Integer> fromCoords = new ArrayList<>();
                        fromCoords.add(move.get(0));
                        fromCoords.add(move.get(1));
                        Stack<TileClass> testStack = new Stack<>();
                        testStack.push(tile);

                        board.boardPut(fromCoords, testStack);
                        System.out.println(move.get(0) + " : " + move.get(1) + " ----->  " + neighbour.get(0) + " : " + neighbour.get(1));
                        if (!maySlide(move.get(0), move.get(1), neighbour.get(0), neighbour.get(1))) {
                            //TODO does return false but whole move function still says true?
                            System.out.println("Can not fit through gap");
                            board.boardRemove(fromCoords);
                            return false;
                        }
                    } else if (board.evaluateMoveTile(move.get(0), move.get(1), neighbour.get(0), neighbour.get(1), tile)) {
//                        System.out.println(move.get(0) + " : " +  move.get(1) + "  --------> " +  neighbour.get(0) + " : " +  neighbour.get(1));
                        newLegitMoves.add(neighbour);
                    }
                }
                return testSoldierAnt(newLegitMoves, move.get(0), move.get(1), toQ, toR, tile);

            }
        }
        return false;
    }

    private boolean testSpider(ArrayList<ArrayList<Integer>> legitMoves, int fromQ, int fromR, int toQ, int toR, TileClass tile) {
        ArrayList<ArrayList<Integer>> newLegitMoves = new ArrayList<>();
        int cnt = 0;
        for (ArrayList<Integer> move : legitMoves) {
            cnt++;

            if (move.get(0) == toQ && move.get(1) == toR) {
                System.out.println("Its a legit move");
                return true;
            } else {
                for (ArrayList<Integer> neighbour : board.getNeighbours(move.get(0), move.get(1))) {

                    if (neighbour.get(0) == fromQ && neighbour.get(1) == fromR) {
                        continue;
                    } else if (board.evaluateMoveTile(move.get(0), move.get(1), neighbour.get(0), neighbour.get(1), tile)) {
                        newLegitMoves.add(neighbour);
                    }
                }
                System.out.println("=-=-=-=-=-=-=-=-=-=-=cnt : " + cnt + "=-=-=-=-=-=-=-=-=");
                return testSpider(newLegitMoves, move.get(0), move.get(1), toQ, toR, tile);

            }
        }
        return false;
    }

    public boolean moveTile(int fromQ, int fromR, int toQ, int toR) {
        if (getCurrentPlayer() == Player.BLACK) {
            if (!blackTilesInPlay.contains(board.getTilesOnSpot(fromQ, fromR).peek())) {
                System.out.println("Move 1");
                return false;
            }
            if (!blackQueenBeeInPlay) {
                System.out.println("Move 2");
                return false;
            }
        } else {
            if (!whiteTilesInPlay.contains(board.getTilesOnSpot(fromQ, fromR).peek())) {
                System.out.println("Move 3");
                return false;
            }
            if (!whiteQueenBeeInPlay) {
                System.out.println("Move 4");
                return false;
            }
        }

        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);

        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);

        switch (board.getTilesOnSpot(fromQ, fromR).peek().getTile()) {
            case QUEEN_BEE:

//                System.out.println(toR + " : " + fromR + " : " + toQ + " : " + fromQ);
//                Integer distanceX = Math.abs(fromQ - toQ);
//                Integer distanceY = Math.abs(fromR - toR);
//                System.out.println(Math.max(distanceX, distanceY));
                if (board.getTilesOnSpot(toQ, toR) != null) {
                    System.out.println("Q1) Queen wants to move but there is a tile on the location");
                    return false;
                }

                // only move 1 tile
                if (!board.getNeighbours(fromQ, fromR).contains(toCoords)) {
                    System.out.println("Queen moved more than 1 tile");
                    return false;
                }

                if (!maySlide(fromQ, fromR, toQ, toR)) {
                    System.out.println("Queen Cannot slide like this!");
                    return false;
                }
                if (!keepContact(fromQ, fromR, toQ, toR)) {
                    return false;
                }

                break;
            case BEETLE:
                // only move 1 tile
                if (!board.getNeighbours(fromQ, fromR).contains(toCoords)) {
                    System.out.println("Beetle moved more than 1 tile");
                    return false;
                }

                if (!maySlide(fromQ, fromR, toQ, toR)) {
                    System.out.println("Beetle Cannot slide like this!");
                    return false;
                }
                if (!keepContact(fromQ, fromR, toQ, toR)) {
                    return false;
                }

                break;
            case GRASSHOPPER:
                //Een sprinkhaan mag zich niet verplaatsen naar het veld waar hij al staat.
                if (fromQ == toQ && fromR == toR) {
                    System.out.println("Cant move to same spot");
                    return false;
                }
                //Een sprinkhaan mag niet naar een bezet veld springen.
                if (board.getTilesOnSpot(toQ, toR) != null) {
                    return false;
                }
                //Een sprinkhaan moet over minimaal één steen springen.
                if (pathMaker(fromQ, fromR, toQ, toR).isEmpty()) {
                    return false;
                }

                //Een sprinkhaan mag niet over lege velden springen.
                for (ArrayList<Integer> coord : pathMaker(fromQ, fromR, toQ, toR)) {
                    if (board.getTilesOnSpot(coord.get(0), coord.get(1)) == null) {
                        return false;
                    }
                }

                break;
            case SPIDER:
//                if (!maySlide(fromQ, fromR, toQ, toR)) {
//                    System.out.println("Spider Cannot slide like this!");
//                    return false;
//                }
//                if (!keepContact(fromQ, fromR, toQ, toR)) {
//                    return false;
//                }
                if (fromQ == toQ && fromR == toR) {
                    System.out.println("Cant move to same spot");
                    return false;
                }
                ArrayList<ArrayList<Integer>> legitSpiderMoves = new ArrayList<>();
                for (ArrayList<Integer> neighbour : board.getNeighbours(fromQ, fromR)) {
                    if (board.testMoveTile(fromQ, fromR, neighbour.get(0), neighbour.get(1))) {
                        legitSpiderMoves.add(neighbour);
                    }
                }
                if (testSpider(legitSpiderMoves, fromQ, fromR, toQ, toR, board.getTilesOnSpot(fromQ, fromR).peek())) {
                    return true;
                }
                break;
            case SOLDIER_ANT:
                ArrayList<ArrayList<Integer>> legitSoldier_antMoves = new ArrayList<>();
                int cnt = 0;

                if (fromQ == toQ && fromR == toR) {
                    System.out.println("Cant move to same spot");
                    return false;
                }
                for (ArrayList<Integer> neighbour : board.getNeighbours(fromQ, fromR)) {
                    if (board.testMoveTile(fromQ, fromR, neighbour.get(0), neighbour.get(1))) {
                        legitSoldier_antMoves.add(neighbour);
                    }
                }
                for (ArrayList<Integer> neighbour : board.getNeighbours(toQ, toR)) {
                    if (board.getTilesOnSpot(neighbour.get(0), neighbour.get(1)) == null) {
                        cnt++;
                    }
                }
                if (cnt == 6) {
                    return false;
                }
//                if (!keepContact(fromQ, fromR, toQ, toR)) {
//                    return false;
//                }
                if (testSoldierAnt(legitSoldier_antMoves, fromQ, fromR, toQ, toR, board.getTilesOnSpot(fromQ, fromR).peek())) {
                    return true;
                }

//                ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
//
//                legitMoves.add(fromCoords);
////                System.out.println(fromQ + " : " + fromR);
////                System.out.println(toQ + " : " + toR);
//                for (int i = 0; i < legitMoves.size() ; i++){
//                    System.out.println("Number of iterations : " + i );
//                    for (ArrayList<Integer> neighbour : board.getNeighbours(legitMoves.get(i).get(0), legitMoves.get(i).get(1))) {
////                        System.out.println(legitMoves.get(0).get(0) + " : "+ legitMoves.get(0).get(1));
//                        if (legitMoves.contains(neighbour)){
//                            continue;
//                        }
//                        if (board.testMoveTile(legitMoves.get(i).get(0), legitMoves.get(i).get(1), neighbour.get(0), neighbour.get(1))){
////                        System.out.println("Move to " + neighbour + " is legit");
//                            if (neighbour.equals(toCoords)){
//                                System.out.println("Found solution");
//                                board.moveTile(legitMoves.get(i).get(0), legitMoves.get(i).get(1), neighbour.get(0), neighbour.get(1));
//                                return true;
//                            }
//                            legitMoves.add(neighbour);
////                            System.out.println("added " + neighbour);
//
//                        }
//                    }
//                    System.out.println("Currenn i " + i);
//                    System.out.println(legitMoves);
//                    System.out.println(board.testMoveTile(legitMoves.get(i).get(0), legitMoves.get(i).get(1), legitMoves.get(i+1).get(0), legitMoves.get(i+1).get(1)));
//                    board.moveTile(legitMoves.get(i).get(0), legitMoves.get(i).get(1), legitMoves.get(i+1).get(0), legitMoves.get(i+1).get(1));
//                    System.out.println((legitMoves.get(i).get(0) +" : " + legitMoves.get(i).get(1) + "  --Going to--->  " + legitMoves.get(i+1).get(0) +" :  " +  legitMoves.get(i+1).get(1)));
////                    System.out.println("**Legit***" + legitMoves + "****Moves**");
////                    System.out.println("***Visited**" + visited + "******");
//                }

//                if (!maySlide(fromQ, fromR, toQ, toR)) {
//                    System.out.println("Soldier ant Cannot slide like this!");
//                    return false;
//                }


                break;
        }

        if (!board.testMoveTile(fromQ, fromR, toQ, toR)) {
            System.out.println("Move 5");
            return false;
        }

        board.moveTile(fromQ, fromR, toQ, toR);
        checkVictory();
        playerPass();
        return true;
    }

    public boolean playerPass() {
        if (currentPlayer == Hive.Player.WHITE) {
            currentPlayer = Hive.Player.BLACK;
        } else {
            currentPlayer = Hive.Player.WHITE;
        }
        return true;
    }

    private ArrayList<ArrayList<Integer>> pathMaker(int fromQ, int fromR, int toQ, int toR) {
        int x = 0;
        int y = 0;
        ArrayList<ArrayList<Integer>> path = new ArrayList<>();
        if (fromQ != toQ && fromR != toR) {
            // now we know rechtsboven of linksonder
            if (toQ < fromQ && toR > fromR) {
                // Linksonder gegaan
                x = -1;
                y = 1;
            } else if (toQ > fromQ && toR < fromR) {
                //rechtsboven
                x = 1;
                y = -1;
            }
        } else if (fromQ == toQ && fromR != toR) {
            // Rechtsonder of Linksboven
            if (toR > fromR) {
                //Rechtsonder
                y = 1;
            } else {
                // Linksboven
                y = -1;
            }
        } else if (fromQ != toQ && fromR == toR) {
            // Rechts of Links
            if (toQ > fromQ) {
                // Rechts
                x = 1;
            } else {
                // Links
                x = -1;
            }
        }
        if (x == 0 && y == 0) {
            return path;
        }
        while (fromQ != toQ || fromR != toR) {
            ArrayList<Integer> coords = new ArrayList<>();
            fromQ += x;
            fromR += y;
            coords.add(fromQ);
            coords.add(fromR);
            path.add(coords);
        }
        path.remove(path.size() - 1);
        return path;
    }

    private boolean keepContact(int fromQ, int fromR, int toQ, int toR) {

        // keep contact while sliding
        if (fromQ + 1 == toQ && fromR == toR) {
            System.out.println("Moved Rechts");
            if (board.getTilesOnSpot(fromQ + 1, fromR - 1) == null && board.getTilesOnSpot(fromQ, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ - 1 == toQ && fromR == toR) {
            System.out.println("Moved Links");
            if (board.getTilesOnSpot(fromQ, fromR - 1) == null && board.getTilesOnSpot(fromQ - 1, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ - 1 == toQ && fromR + 1 == toR) {
            System.out.println("Moved Linksonder");
            if (board.getTilesOnSpot(fromQ, fromR + 1) == null && board.getTilesOnSpot(fromQ - 1, fromR) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ == toQ && fromR + 1 == toR) {
            System.out.println("Moved Rechtsonder");
            if (board.getTilesOnSpot(fromQ + 1, fromR) == null && board.getTilesOnSpot(fromQ - 1, fromR + 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ == toQ && fromR - 1 == toR) {
            System.out.println("Moved Linksboven");
            if (board.getTilesOnSpot(fromQ - 1, fromR) == null && board.getTilesOnSpot(fromQ + 1, fromR - 1) == null) {
                System.out.println("Je houdt niet contact!");
                return false;
            }
        }
        if (fromQ + 1 == toQ && fromR - 1 == toR) {
            System.out.println("Moved Rechtsboven");
            if (board.getTilesOnSpot(fromQ, fromR - 1) == null && board.getTilesOnSpot(fromQ + 1, fromR) == null) {
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

        if (board.getNeighbours(fromQ, fromR).contains(toCoords)) {
            // They are connected
            for (ArrayList<Integer> neighbour : board.getNeighbours(fromQ, fromR)) {
                if (board.getBoard().get(neighbour) != null) {
                    fromNeighbours.add(neighbour);
                }
            }
            for (ArrayList<Integer> neighbour : board.getNeighbours(toQ, toR)) {
                if (board.getBoard().get(neighbour) != null) {
                    toNeighbours.add(neighbour);
                }
            }
            toNeighbours.retainAll(fromNeighbours);

            if (toNeighbours.isEmpty() || toNeighbours.size() == 1) {
                return true;
            }
            if (board.getTilesOnSpot(toQ, toR) == null) {
                toTiles = 0;
            } else {
                toTiles = board.getTilesOnSpot(toQ, toR).size();
            }
            if (Math.min(board.getTilesOnSpot(toNeighbours.get(0).get(0), toNeighbours.get(0).get(1)).size(), board.getTilesOnSpot(toNeighbours.get(1).get(0), toNeighbours.get(1).get(0)).size()) <=
                    Math.max(board.getTilesOnSpot(fromQ, fromR).size() - 1, toTiles)) {
                // It can slide to the toQ,toR coordinate
                return true;
            }
        }
        System.out.println();
        return false;
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        if (!setTile(q, r, new TileClass(getCurrentPlayer(), tile))) {
            throw new IllegalMove();
        }

    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        if (!moveTile(fromQ, fromR, toQ, toR)) {
            throw new IllegalMove("False");
        }
    }

    @Override
    public void pass() throws IllegalMove {
        if (!playerPass()) {
            throw new IllegalMove("False");
        }
    }

    @Override
    public boolean isWinner(Player player) {
        if (winner == null) {
            return false;
        }
        if (winner == player) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDraw() {
        return draw;
    }

}
