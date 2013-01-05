package com.fishbans.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BanService {
	Map<String, Object> map;
	String name;
	public BanService(String name,Map<String,Object> map){
		this.map=map;
		this.name=name;
	}
	/**
	 * Returns the name of the service checked.
	 * @return name.
	 */
	public String getService(){
		return name;
	}
	/**
	 * Returns a list of ban reasons.
	 * @return list - a listing of all bans.
	 */
	public List<String> getBanList(){
		List<String> list = new ArrayList<String>();
		if(!map.isEmpty())
			for(Object ban:map.values())
				list.add(ban.toString());
		return list;
	}
	/**
	 * Returns a list of ban reasons.
	 * @return list - a listing of all servers.
	 */	
	public List<String> getServerList(){
		List<String> list = new ArrayList<String>();
		if(!map.isEmpty())
			for(String ban:map.keySet())
				list.add(ban);
		return list;	
	}
	/**
	 * Returns a combined list of servers:ban reasons (seperated by ':').
	 * @return list - a listing of all servers and bans.
	 */	
	public List<String> getCombinedList(){
		List<String> list = new ArrayList<String>();
		if(map.isEmpty())
			return list;
		Iterator<String> cKeys = map.keySet().iterator();
	    Iterator<Object> cValue = map.values().iterator();
		while (cValue.hasNext() && cKeys.hasNext()){
			list.add(cKeys.next()+":"+cValue.next().toString());
		}
		return list;
	}
}
