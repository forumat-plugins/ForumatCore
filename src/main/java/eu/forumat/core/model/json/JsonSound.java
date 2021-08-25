package eu.forumat.core.model.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class JsonSound {

    private final String soundName;
    private final float pitch;
    private final float volume;

    public void play(Player player) {
        player.playSound(player.getLocation(), Sound.valueOf(this.soundName), this.getVolume(), this.getPitch());
    }

}
