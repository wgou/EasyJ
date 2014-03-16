package org.easyj.framework.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
public class AnnactionUtil {
	
	public static <T extends Annotation>T getAnnotation(Object obj,Class<T>clz){
		if(obj instanceof AnnotatedElement){
			AnnotatedElement ae = (AnnotatedElement)obj;
			T t = ae.getAnnotation(clz);
			return t;
		}
		return null;
	}
	public static <T extends Annotation>boolean hasAnnotation(Object obj,Class<T>clz){
		return getAnnotation(obj,clz) != null;
	}
}
