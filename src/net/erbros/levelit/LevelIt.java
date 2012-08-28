package net.erbros.levelit;

import org.bukkit.plugin.java.JavaPlugin;

public class LevelIt extends JavaPlugin {

    public LevelItCommandExecutor myExecutor;

    @Override
    public void onDisable() {
        
    }
    
    @Override
    public void onEnable() {
     // Get ready for the commands
        myExecutor = new LevelItCommandExecutor(this);
        getCommand("levelit").setExecutor(myExecutor);
        
    }
}
