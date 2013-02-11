import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.fishbans.api.BanService;
import com.fishbans.api.FishBans;


public class Example extends JavaPlugin implements Listener{
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);		
	}
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event){
		//Methods are not threaded Make sure to schedule or the server will hang.
		if(!event.getPlayer().hasPlayedBefore()){
			final String name = event.getPlayer().getName();
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				@Override
				public void run() {
					BanService service = FishBans.getBans(name, FishBans.GLIZER);
					Player player = Bukkit.getPlayer(name);
					if(player!=null)
						for(String bans:service.getCombinedList())
							player.sendMessage(bans);				
				}
			});
		}
	}

}
