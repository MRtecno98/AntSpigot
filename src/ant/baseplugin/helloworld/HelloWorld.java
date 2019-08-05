package ant.baseplugin.helloworld;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorld extends JavaPlugin implements CommandExecutor {
	YamlConfiguration config;
	
	@Override
	public void onEnable() {
		getLogger().info("Loving Ant...");
		
		setupConfig();
		
		getCommand("helloworld").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(getConfig().getString("helloworld"));
		return true;
	}
	
	@Override
	public FileConfiguration getConfig() { return config; }
	
	public void setupConfig() {
		File configFile = new File(getDataFolder(), "configs/config.yml");
        if (!configFile.exists()) {
        	configFile.getParentFile().mkdirs();
            saveResource("configs/config.yml", false);
         }

        config = new YamlConfiguration();
        try {
        	config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
	}
}
