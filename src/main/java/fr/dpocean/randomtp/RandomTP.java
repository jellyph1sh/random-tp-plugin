package fr.dpocean.randomtp;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dpocean.randomtp.commands.RandomTPCommand;

public class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("rtp").setExecutor(new RandomTPCommand());

        new TeleportUtils(this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

}
