package eu.forumat.core.model.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Data
@AllArgsConstructor
public class JsonLocation {

    private final String worldName;

    private final double x, y, z;

    private final float yaw, pitch;

    public static JsonLocation ofBukkitLocation(Location location) {
        return new JsonLocation(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
    }

    public Location toBukkitLocation() {
        return new Location(
                Bukkit.getWorld(this.getWorldName()),
                this.getX(),
                this.getY(),
                this.getZ(),
                this.getYaw(),
                this.getPitch());
    }
}
