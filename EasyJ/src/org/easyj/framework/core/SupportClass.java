package org.easyj.framework.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.easyj.framework.core.annotation.Controller;
import org.easyj.framework.core.annotation.RequestMapping;
import org.easyj.framework.core.annotation.Resource;
import org.easyj.framework.core.annotation.Service;
import org.easyj.framework.core.map.Action;
import org.easyj.framework.core.map.ClassMap;
import org.easyj.framework.core.proxy.ProxyFactory;
import org.easyj.framework.util.AnnactionUtil;

public class SupportClass extends ClassMap{
	
	public static <T>void loadClass(Class<T> clazz){
		String class_key = null;
		if(AnnactionUtil.hasAnnotation(clazz, Controller.class)){
			Controller controller = AnnactionUtil.getAnnotation(clazz,Controller.class);
			if(controller.value() == null){
				class_key = clazz.getName().substring(0, 1).toLowerCase()+clazz.getName().substring(1);
			}else{
				class_key = controller.value();
			}
			actionMethodDescation(class_key,clazz);
		}else if(AnnactionUtil.hasAnnotation(clazz, Service.class)){
			Service service = AnnactionUtil.getAnnotation(clazz,Service.class);
			if(service.value() == null){
				class_key = clazz.getName().substring(0, 1).toLowerCase()+clazz.getName().substring(1);
			}else{
				class_key = service.value();
			}
		}
		instanceClassMap.put(class_key, clazz);
			
	}
	//对属性注入依赖对象
	public static synchronized <T>T setFiledClass(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T t = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields){
			if(AnnactionUtil.hasAnnotation(f, Resource.class)){
				String resource_name;
				Resource resource = AnnactionUtil.getAnnotation(f,Resource.class);
				if(resource.name() == null){
					resource_name = f.getName();
				}else{
					resource_name = resource.name();
				}
				if(newClassMap.get(resource_name) == null){
					Object o = setFiledClass(instanceClassMap.get(resource_name));
					Object proxyClass = ProxyFactory.proxyClass(o);
					newClassMap.put(resource_name, proxyClass);
				}
			f.setAccessible(true);
			f.set(t,newClassMap.get(resource_name));
			}
		}
		return t;
	}
	//加载controller的method
	private static void actionMethodDescation(String key,Class<?> clazz){
		Method[] methods = clazz.getDeclaredMethods();
		List<Action> list = new ArrayList<Action>();
		for(Method method : methods){
			Action action = new Action();
			String methodUrl;
			if(AnnactionUtil.hasAnnotation(method, RequestMapping.class)){
				RequestMapping mapping = AnnactionUtil.getAnnotation(method,  RequestMapping.class);
				if(mapping.value() == null){
					methodUrl = "/"+method.getName();
				}else{
					methodUrl = mapping.value();
				}
			}else{
				methodUrl = "/"+method.getName();
			}
			action.setClazz(clazz);
			action.setMethod(method);
			action.setMethodName(methodUrl);
			list.add(action);
		}
		
		actionMap.put(key, list);
	}
}
