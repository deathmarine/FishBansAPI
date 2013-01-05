package com.fishbans.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FishStats {
	public static String MCBANS = "mcbans";
	public static String MCBOUNCER = "mcbouncer";
	public static String MCBLOCKIT = "mcblockit";
	public static String MINEBANS = "minebans";
	public static String BCBANS = "bcbans";
	/**
	 * Gets Stats for a player from all Services.
	 * @param player
	 * @return Stats
	 */
	public static Stats getAllStats(Player player){
		return getAllStats(player.getName());
	}
	/**
	 * Gets Stats for a player from all Services.
	 * @param playername
	 * @return Stats
	 */
	public static Stats getAllStats(String playername){
		URL url;
		try {
			url = new URL("http://www.fishbans.com/api/stats/"+playername+"/force/");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(builder.toString());
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject bans = (JSONObject) jsonObject.get("stats");
			if(bans == null){
				return null;
			}
			JSONObject service = (JSONObject) bans.get("service");
			long mcbansAmt = 0;
			if(service.get(MCBANS) != null) mcbansAmt = (Long) service.get(MCBANS);
			long mcbouncerAmt = 0;
			if(service.get(MCBOUNCER) != null) mcbouncerAmt = (Long) service.get(MCBOUNCER);
			long mcblockitAmt =  0;
			if(service.get(MCBLOCKIT) != null) mcblockitAmt = (Long) service.get(MCBLOCKIT);
			long minebansAmt = 0;
			if(service.get(MINEBANS) != null) minebansAmt = (Long) service.get(MINEBANS);
			long bcbansrAmt = 0;
			if(service.get(BCBANS) != null) bcbansrAmt = (Long) service.get(BCBANS);
			return new Stats(mcbansAmt,mcbouncerAmt,mcblockitAmt,minebansAmt,bcbansrAmt);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Stats(0,0,0,0,0);
	}
	/**
	 * Gets Stats for a player from named Services.
	 * @param player
	 * @return Integer
	 */
	public static Integer getStats(Player player,String servicename){
		return getStats(player.getName(), servicename);
	}
	/**
	 * Gets Stats for a player from all Services.
	 * @param playername
	 * @return Integer
	 */
	public static Integer getStats(String playername,String servicename){
		URL url;
		try {
			url = new URL("http://www.fishbans.com/api/stats/"+playername+"/"+servicename+"/force/");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(builder.toString());
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject bans = (JSONObject) jsonObject.get("stats");
			if(bans == null){
				return null;
			}
			JSONObject service = (JSONObject) bans.get("service");
			long s = 0;
			if(service.get(servicename) != null) s = (Long) service.get(servicename);
			return (int) s;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
