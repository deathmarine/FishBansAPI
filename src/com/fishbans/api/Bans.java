package com.fishbans.api;

import java.util.Set;

public class Bans {
	Set<BanService> set;
	public Bans(Set<BanService> set) {
		this.set=set;
	}
	/**
	 * Returns a set of all Services
	 * @return set - Set of all Services
	 */
	public Set<BanService> getAllServices(){
		return set;
	}
	/**
	 * Gets a service by name
	 * @param name
	 * @return Service will return null if not found.
	 */
	public BanService getService(String name){
		for(BanService s:set)
			if(s.getService().equalsIgnoreCase(name))
				return s;
		return null;
	}
	
}
