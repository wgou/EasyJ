package org.easyj.framework.core.map;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public  class ClassMap {
	public static Map<String,Object> newClassMap = new ConcurrentHashMap<String, Object>();
	public static Map<String,List<Action>> actionMap = new ConcurrentHashMap<String, List<Action>>();
	public static Map<String,Class<?>> instanceClassMap = new ConcurrentHashMap<String, Class<?>>();
	public static Set<Class<?>> loadClass = new HashSet<Class<?>>();
}
