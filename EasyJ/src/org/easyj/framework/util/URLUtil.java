package org.easyj.framework.util;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easyj.framework.core.annotation.Model;
import org.easyj.framework.core.map.Action;
import org.easyj.framework.core.map.ClassMap;

/**
 * 用户请求URL处理
 * @author 苟伟
 *
 */
public class URLUtil {
	/**
	 * http://192.168.1.1:8080/EP/User/index
	 * http://192.168.1.1:8080/EP/User/index?xxxx
	 * @param url
	 * @param request
	 * @return
	 */
	public static Action getActionMethod(HttpServletRequest request){
		Action actions = null;
		String url =  request.getRequestURI();
		String contextpath = request.getContextPath();
		String controller = url.substring(url.indexOf(contextpath)+contextpath.length(),url.lastIndexOf("/"));
		String methodUrl = url.indexOf("?") == -1 ?url.substring(url.lastIndexOf("/")):url.substring(url.lastIndexOf("/"),url.indexOf("?") );
		List<Action> list= ClassMap.actionMap.get(controller);
		if(list != null){
			for(Action action : list){
				if(methodUrl.equals(action.getMethodName())){
					actions = action;
				}
			}
		}
		return actions;
	}
	//判断action参数中是否具有对象@Model,并返回Model修饰的参数
	private static Class<?> isModelParams(Method method){
		Class<?> clazz = null;
		Class<?>[] paramsTypeClass = method.getParameterTypes();
		Annotation[][] annotations = method.getParameterAnnotations();
		for(int i=0,len=annotations.length;i<len;i++){
			Annotation[] annt = annotations[i];
			if(annt.length > 0 && (annt[0].annotationType() == Model.class)){
				clazz = paramsTypeClass[i];
			}
		}
		return clazz;
	}
	
	
	//组合Model对象
	@SuppressWarnings("unchecked")
	public static <T>T getParamsObject(HttpServletRequest request,Method method){
		Class<?> clazz = isModelParams(method);
		if(clazz == null){
			return null;
		}
		T t = null;
		try {
			t = (T)clazz.newInstance();
			Map<String,String[]> paramsMap = request.getParameterMap();
			if(paramsMap != null && paramsMap.size() > 0){
				 Map<String, Method> setMap = ClassMethodUtil.getSetMethodMap(clazz);
				for(Map.Entry<String, String[]> map : paramsMap.entrySet()){
					if(setMap.get(map.getKey()) != null){
						Method setMethod = setMap.get(map.getKey());
						StringBuffer value = new StringBuffer();
						for(String str : map.getValue()){
							if(value.length() > 0){value.append(",");}
							value.append(str);
						}
						String des = new String(value.toString().getBytes("iso8859-1"),"UTF-8");
						ClassMethodUtil.getInvokeSetParamsBean(t, setMethod, des);
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return t;
	}
}
