package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Hive.Player currentPlayer = Hive.Player.WHITE;
    private Board board = new Board();
    private String winner = null;
    private HashMap<Hive.Player, Boolean> victory = new HashMap<>();

    public Game() {
        victory.put(Hive.Player.BLACK, false);
        victory.put(Hive.Player.WHITE, false);
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
                    return;
                }
                if (board.getBoard().get(neighbour).size() >= 1) {
                    victory.put(getKey(board.getQueenList(), coords), true);
                } else {
                    victory.put(getKey(board.getQueenList(), coords), false);
                }
            }
            if (victory.get(Hive.Player.BLACK) && victory.get(Hive.Player.WHITE)) {
                winner = "DRAW";
            } else if (victory.get(Hive.Player.BLACK)) {
                winner = "WHITE";
            } else if (victory.get(Hive.Player.WHITE)) {
                winner = "BLACK";
            }
        }
    }

    public void setTile(int q, int r, Tile tile) {
        board.setTile(q, r, tile);
        checkVictory();
        pass();
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
