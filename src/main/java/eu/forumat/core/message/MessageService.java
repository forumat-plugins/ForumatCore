package eu.forumat.core.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class MessageService {

    private final String prefix;
    private final Map<String, String> messages;

    public String getMessage(String key, String... placeholders) {

        String message = messages.get(key).replace("%prefix%", prefix);
        if (placeholders != null) {

            int i = 0;
            for (String placeholder : placeholders) {
                message = message.replace(placeholders[i], placeholders[i + 1]);
                i += 2;
            }

        }
        return ChatColor.translateAlternateColorCodes('&', message);

    }

    public void sendMessage(CommandSender commandSender, String key, String... placeholders) {
        commandSender.sendMessage(getMessage(key, placeholders));
    }

}
