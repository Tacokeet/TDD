package nl.hanze.hive;

import java.util.ArrayList;

public class Player {
    private ArrayList<Tile> startingTiles = new ArrayList<>();

    public Player(Hive.Player player) {
        startingTiles.add(new Tile(player, Hive.Tile.QUEEN_BEE));
        startingTiles.add(new Tile(player, Hive.Tile.SPIDER));
        startingTiles.add(new Tile(player, Hive.Tile.SPIDER));
        startingTiles.add(new Tile(player, Hive.Tile.BEETLE));
        startingTiles.add(new Tile(player, Hive.Tile.BEETLE));
        startingTiles.add(new Tile(player, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(player, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(player, Hive.Tile.SOLDIER_ANT));
        startingTiles.add(new Tile(player, Hive.Tile.GRASSHOPPER));
        startingTiles.add(new Tile(player, Hive.Tile.GRASSHOPPER));
        startingTiles.add(new Tile(player, Hive.Tile.GRASSHOPPER));
    }

    public ArrayList<Tile> getStartingTiles() {
        return startingTiles;
    }

    public void removeTile(Tile tile){
        for (Tile t : startingTiles){
            if (t.getTile() == tile.getTile()){
                startingTiles.remove(t);
                break;
            }
        }
    }
}
