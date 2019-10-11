package nl.hanze.hive;

public class Tile {
    private Hive.Player player;
    private Hive.Tile tile;

    public Tile(Hive.Player player, Hive.Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public Hive.Player getPlayer() {
        return player;
    }

    public Hive.Tile getTile() {
        return tile;
    }
}
