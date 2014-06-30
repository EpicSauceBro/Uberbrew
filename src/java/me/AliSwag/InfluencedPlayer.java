package me.AliSwag;


import org.bukkit.entity.Player;

/**
 * @since 0.1
 */
public class InfluencedPlayer {

    private byte alcoholContent;
    private Player player;
    private Main instance;

    @SuppressWarnings("unused")
    @Deprecated
    private InfluencedPlayer() {
    }

    public InfluencedPlayer(Player player, byte alcoholContent) {
        this.player = player;
        if (alcoholContent < 0) {
            alcoholContent = 0;
        }
        if (alcoholContent > 100) {
            alcoholContent = 100;
        }
        this.alcoholContent = alcoholContent;
        this.instance = Main.main;
        Main.influencedPlayers.put(player, this);
    }

    public void resetAlcoholContent() {
        this.alcoholContent = 0;
    }

    public byte getAlcoholContent() {
        return this.alcoholContent;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setAlcoholContent(byte alcoholContent) {
        if (alcoholContent < 0) {
            alcoholContent = 0;
        }
        if (alcoholContent > 100) {
            alcoholContent = 100;
        }

        this.alcoholContent = alcoholContent;
        Main.influencedPlayers.put(player, this);
    }
}
