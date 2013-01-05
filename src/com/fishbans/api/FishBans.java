package com.fishbans.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FishBans {
	public static String MCBANS = "mcbans";
	public static String MCBOUNCER = "mcbouncer";
	public static String MCBLOCKIT = "mcblockit";
	/**
	 * Gets Bans for a player from all Services.
	 * @param player
	 * @return Bans
	 */
	public static Bans getAllBans(Player player){
		return getAllBans(player.getName());
	}
	/**
	 * Gets Bans for a player from all Services.
	 * @param playerName
	 * @return Bans
	 */
	public static Bans getAllBans(String playerName){
		Set<BanService> set = new HashSet<BanService>();
		URL url;
		try {
			url = new URL("http://www.fishbans.com/api/bans/"+playerName+"/");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(builder.toString());
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject bans = (JSONObject) jsonObject.get("bans");
			if(bans == null){
				return null;
			}
			JSONObject service = (JSONObject) bans.get("service");
			if(service == null){
				return null;
			}
			//McBans
			JSONObject mcbans = (JSONObject) service.get(MCBANS);
			if(mcbans == null){
				return null;
			}
			long mcbansAmt = 0;
			if(mcbans.get("bans") != null){
				mcbansAmt = (Long) mcbans.get("bans");
			}
			if(mcbansAmt > 0){
				JSONObject mcbansInfo = castToJSON(mcbans.get("ban_info"));
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(mcbansInfo!=null)
					toJavaMap(mcbansInfo, map);
				set.add(new BanService(MCBANS,map));
			}
			//McBouncer
			JSONObject mcbouncer = (JSONObject) service.get(MCBOUNCER);
			if(mcbouncer == null){
				return null;
			}
			long mcbouncerAmt = 0;
			if(mcbouncer.get("bans") != null){
				mcbouncerAmt = (Long) mcbouncer.get("bans");
			}
			if(mcbouncerAmt > 0){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject mcbouncerInfo = castToJSON(mcbouncer.get("ban_info"));
				if(mcbouncerInfo!=null)
					toJavaMap(mcbouncerInfo, map);
				set.add(new BanService(MCBOUNCER,map));
			}
			
			//McBlockIt
			JSONObject mcblockit = (JSONObject) service.get(MCBLOCKIT);
			if(mcblockit == null){
				return null;
			}
			long mcblockitAmt = 0;
			if(mcblockit.get("bans") != null){
				mcblockitAmt = (Long) mcblockit.get("bans");
			}
			if(mcblockitAmt > 0){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject mcblockitInfo = castToJSON(mcblockit.get("ban_info"));
				if(mcblockitInfo!=null)
					toJavaMap(mcblockitInfo, map);
				set.add(new BanService(MCBLOCKIT,map));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Bans(set);
	}
	/**
	 * Gets Bans for a player from all Services.
	 * @param player
	 * @return BanService
	 */
	public static BanService getBans(Player player,String servicename){
		return getBans(player.getName(),servicename);
	}
	/**
	 * Gets Bans for a player from all Services.
	 * @param playerName
	 * @return BanService
	 */
	public static BanService getBans(String playerName,String servicename){
		URL url;
		try {
			url = new URL("http://www.fishbans.com/api/bans/"+playerName+"/"+servicename+"/");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(builder.toString());
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject bans = (JSONObject) jsonObject.get("bans");
			if(bans == null){
				return null;
			}
			JSONObject service = (JSONObject) bans.get("service");
			if(service == null){
				return null;
			}
			JSONObject ser = (JSONObject) service.get(servicename);
			if(ser == null){
				return null;
			}
			long amt = 0;
			if(ser.get("bans") != null){
				amt = (Long) ser.get("bans");
			}
			if(amt > 0){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject mcblockitInfo = castToJSON(ser.get("ban_info"));
				if(mcblockitInfo!=null)
					toJavaMap(mcblockitInfo, map);
				return new BanService(servicename,map);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static JSONObject castToJSON(Object object){
		if(object instanceof JSONObject){
			return (JSONObject) object;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void toJavaMap(JSONObject o, Map<String, Object> b) {
		  Iterator ji = o.keySet().iterator();
		  while (ji.hasNext()) {
		    String key = (String) ji.next();
		    Object val = o.get(key);
		    if (val.getClass() == JSONObject.class) {
		      Map<String, Object> sub = new HashMap<String, Object>();
		      toJavaMap((JSONObject) val, sub);
		      b.put(key, sub);
		    } else if (val.getClass() == JSONArray.class) {
		      List<Object> l = new ArrayList<Object>();
		      JSONArray arr = (JSONArray) val;
		      for (int a = 0; a < arr.size(); a++) {
		        Map<String, Object> sub = new HashMap<String, Object>();
		        Object element = arr.get(a);
		        if (element instanceof JSONObject) {
		          toJavaMap((JSONObject) element, sub);
		          l.add(sub);
		        } else {
		          l.add(element);
		        }
		      }
		      b.put(key, l);
		    } else {
		      b.put(key, val);
		    }
		  }
		}
}
