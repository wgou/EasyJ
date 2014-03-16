package org.easyj.framework.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.easyj.framework.core.annotation.Transcation;
import org.easyj.framework.core.jdbc.DBUtil;
import org.easyj.framework.util.AnnactionUtil;
/**
 * 动态代理，处理@Transcation事物机制
 * @author 苟伟
 *
 */
public class ProxyFactory {
	public static Object proxyClass(final Object object){
		if(object.getClass().getInterfaces() != null && object.getClass().getInterfaces().length > 0){
			//jdk proxy
			return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					return ProxyFactory.invoke(object,method,args);
				}
			});
		}else{
			//cglib
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(object.getClass());
			enhancer.setCallback(new MethodInterceptor() {
				public Object intercept(Object obj, Method method, Object[] args,
						MethodProxy methodProxy) throws Throwable {
					return ProxyFactory.invoke(object,method,args);
				}
			});
			return enhancer.create();
		}
	}
	private static Object invoke(Object o,Method method,Object[] params){
		Object obj = null;
		boolean isTranscation = false;
		if(AnnactionUtil.hasAnnotation(o, Transcation.class) || AnnactionUtil.hasAnnotation(method, Transcation.class)){
			isTranscation = true;
		}
		if(isTranscation){
			try {
				DBUtil.beginTranscation();
				obj = method.invoke(o, params);
				DBUtil.endTranscation();
			} catch (Exception e) { 
				try {
					DBUtil.rollbackTranscation();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try {
					DBUtil.close(null, null, DBUtil.getConnection());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				obj = method.invoke(o, params);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}
