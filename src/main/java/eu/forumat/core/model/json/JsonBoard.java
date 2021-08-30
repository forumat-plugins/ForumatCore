package eu.forumat.core.model.json;

import eu.forumat.core.scoreboard.FastBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class JsonBoard {

    private final String[] names;
    private final int animationTick;
    private final List<String> lines;

    private BukkitTask updater;
    private AtomicInteger currentAnimationIndex ;

    public FastBoard toBoard(Player player, JavaPlugin plugin, String... placeholders) {
        Map<String, String> placeholderMap = new HashMap<>();
        for (int i = 0; i < placeholders.length; i++) {
            placeholderMap.put(placeholders[i], placeholders[i += 1]);
        }

        final FastBoard fastBoard = new FastBoard(player);
        currentAnimationIndex = new AtomicInteger(0);
        fastBoard.updateTitle(names[currentAnimationIndex.get()]);

        updater = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {

            int index = currentAnimationIndex.incrementAndGet();
            if (index >= names.length) {
                index = 0;
                currentAnimationIndex.set(0);
            }
            fastBoard.updateTitle(names[index]);

        }, 0, animationTick);

        final List<String> lines = getLines();
        int i = lines.size();
        for (String line : lines) {
            System.out.println("Registering Line: " + line + " with index " + i);
            String finalLineText = line;
            for (String placeholder : placeholderMap.keySet()) {
                finalLineText = finalLineText.replace(placeholder, placeholderMap.get(placeholder));
            }
            fastBoard.updateLine(i, ChatColor.translateAlternateColorCodes('&', finalLineText));
            i--;
        }

        return fastBoard;
    }


}
