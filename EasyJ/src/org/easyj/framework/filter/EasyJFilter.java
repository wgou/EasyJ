package org.easyj.framework.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easyj.framework.core.SupportClass;
import org.easyj.framework.core.annotation.Model;
import org.easyj.framework.core.loader.EasyJClassLoader;
import org.easyj.framework.core.map.Action;
import org.easyj.framework.core.map.ClassMap;
import org.easyj.framework.util.ResponseView;
import org.easyj.framework.util.URLUtil;
/**
 * ��������filter
 * ����ɨ��@Controller/@Resouce Class
 * @author ��ΰ
 *
 */
public class EasyJFilter implements Filter{

	private String encoding = "utf-8";
	private static FilterConfig config;
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		System.out.println("����class�ļ�");
		EasyJClassLoader.initLoaderClass();
		System.out.println("��ʼ�����ӳ�");
		try {
			Class.forName(config.getInitParameter("DBUtil"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(config.getInitParameter("encoding") != null)
			encoding = config.getInitParameter("encoding");
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse) response;
		req.setCharacterEncoding(encoding);		//��������ָ���ķ�������
		rep.setCharacterEncoding(encoding);	//�ѷ�����Ӧָ�� ���뷽ʽ
		new ResponseView(req,rep);
		Action action = URLUtil.getActionMethod(req);
		if(action != null){
			try {
				Object obj = SupportClass.setFiledClass(action.getClazz());
				Method actionMethod = action.getMethod();
				Object paramObject = URLUtil.getParamsObject(req,actionMethod);
				if(paramObject == null){
					actionMethod.invoke(obj,new Object[]{req,rep});
				}else{
					actionMethod.invoke(obj,new Object[]{paramObject,req,rep});
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}else{
			chain.doFilter(request, response);
		}
	}
	public void destroy() {
		this.config = null;
	}
	
}
