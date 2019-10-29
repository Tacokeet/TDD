package nl.hanze.hive;

public class TileClass {
    private Hive.Player player;
    private Hive.Tile tile;

    public TileClass(Hive.Player player, Hive.Tile tile) {
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
