package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Hive.Player currentPlayer = Hive.Player.WHITE;
    private Board board = new Board();
    private String winner = null;
    private HashMap<Hive.Player, Integer> victory = new HashMap<>();
    private Player blackPlayer = new Player(Hive.Player.BLACK);
    private Player whitePlayer = new Player(Hive.Player.WHITE);
    private Boolean blackTileInPlay = false;
    private Boolean whiteTileInPlay = false;
    private Boolean blackQueenBeeInPlay = false;
    private Boolean whiteQueenBeeInPlay = false;

    public Game() {
        victory.put(Hive.Player.BLACK, 0);
        victory.put(Hive.Player.WHITE, 0);
    }

    public Hive.Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    private static <K, V> Hive.Player getKey(HashMap<Hive.Player, ArrayList<Integer>> map, ArrayList<Integer> value) {
        return map.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(HashMap.Entry::getKey)
                .findFirst().get();
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
            winner = "DRAW";
        } else if (victory.get(Hive.Player.BLACK) == 6) {
            winner = "WHITE";
        } else if (victory.get(Hive.Player.WHITE) == 6) {
            winner = "BLACK";
        }
        victory.put(Hive.Player.BLACK, 0);
        victory.put(Hive.Player.WHITE, 0);
    }

    public boolean setTile(int q, int r, Tile tile) {
        Integer counter = 0;
        boolean inNotPlayedTiles = false;
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        if (tile.getPlayer() == Hive.Player.BLACK) {
            if (tile.getTile() == Hive.Tile.QUEEN_BEE){
                blackQueenBeeInPlay = true;
            }
            if (blackPlayer.getStartingTiles().size() == 8 && !blackQueenBeeInPlay) {
                if (tile.getTile() != Hive.Tile.QUEEN_BEE) {
                    System.out.println(coords + " " + tile.getTile() + " " + tile.getPlayer());
                    System.out.println(1);
                    return false;
                }
            }
            for (Tile t : blackPlayer.getStartingTiles()) {
                if (t.getTile() == tile.getTile()) {
                    inNotPlayedTiles = true;
                }
            }
        } else if (tile.getPlayer() == Hive.Player.WHITE) {
            if (tile.getTile() == Hive.Tile.QUEEN_BEE){
                whiteQueenBeeInPlay = true;
            }
            if (whitePlayer.getStartingTiles().size() == 8 && whiteQueenBeeInPlay) {
                if (tile.getTile() != Hive.Tile.QUEEN_BEE) {
                    System.out.println(2);
                    return false;
                }
            }
            for (Tile t : whitePlayer.getStartingTiles()) {
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
                    for (Tile t : board.getBoard().get(neighbour)) {
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

        if (tile.getPlayer() == Hive.Player.BLACK){
            blackPlayer.removeTile(tile);
        }else  if (tile.getPlayer() == Hive.Player.WHITE){
            whitePlayer.removeTile(tile);
        }
        board.setTile(q, r, tile);
        checkVictory();
        pass();
        return true;
    }

    public void moveTile(int fromQ, int fromR, int toQ, int toR) {
        board.moveTile(fromQ, fromR, toQ, toR);
        checkVictory();
        pass();
    }

    public void pass() {
        if (currentPlayer == Hive.Player.WHITE) {
            currentPlayer = Hive.Player.BLACK;
        } else {
            currentPlayer = Hive.Player.WHITE;
        }
    }

}
