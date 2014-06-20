package com.javafera.javascript.domain;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Row extends AbstractMap<String, String> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6854532310640879964L;
	
	private final Map<String, String> map;
	
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	public String get(Object key) {
		return map.get(key);
	}
	
	public String set(String key, String value) {
		map.clear();
		return map.put(key, value);
	}

	public Row(String key, String value) {
		this.map = new HashMap<>();
		this.map.put(key, value);
	}

	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		return map.entrySet();
	}
}
