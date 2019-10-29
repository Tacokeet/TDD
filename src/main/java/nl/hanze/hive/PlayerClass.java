package nl.hanze.hive;

import java.util.ArrayList;

public class PlayerClass {
    private ArrayList<TileClass> startingTileClasses = new ArrayList<>();

    public PlayerClass(Hive.Player player) {
        startingTileClasses.add(new TileClass(player, Hive.Tile.QUEEN_BEE));
        startingTileClasses.add(new TileClass(player, Hive.Tile.SPIDER));
        startingTileClasses.add(new TileClass(player, Hive.Tile.SPIDER));
        startingTileClasses.add(new TileClass(player, Hive.Tile.BEETLE));
        startingTileClasses.add(new TileClass(player, Hive.Tile.BEETLE));
        startingTileClasses.add(new TileClass(player, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(player, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(player, Hive.Tile.SOLDIER_ANT));
        startingTileClasses.add(new TileClass(player, Hive.Tile.GRASSHOPPER));
        startingTileClasses.add(new TileClass(player, Hive.Tile.GRASSHOPPER));
        startingTileClasses.add(new TileClass(player, Hive.Tile.GRASSHOPPER));
    }

    public ArrayList<TileClass> getStartingTileClasses() {
        return startingTileClasses;
    }

    public void removeTile(TileClass tileClass){
        for (TileClass t : startingTileClasses){
            if (t.getTile() == tileClass.getTile()){
                startingTileClasses.remove(t);
                break;
            }
        }
    }
}
