package org.easyj.framework.core.loader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Set;

import org.easyj.framework.core.SupportClass;
import org.easyj.framework.core.annotation.Controller;
import org.easyj.framework.core.annotation.Service;
import org.easyj.framework.core.map.ClassMap;
import org.easyj.framework.util.AnnactionUtil;
import org.easyj.framework.util.PropertiesUtil;

/**
 * 根据配置，加载class
 * @author 苟伟
 *
 */
public final class EasyJClassLoader extends ClassMap{
	private static final String ANNACTION_SCAN_KEY = "annaction_scan_key";
	private static final String packageName = PropertiesUtil.getPropValue(ANNACTION_SCAN_KEY);
	public static void initLoaderClass(){
		extClassLoader.getClass(packageName);
		for(Class<?> clazz : loadClass){
			if(AnnactionUtil.hasAnnotation(clazz,Controller.class) || AnnactionUtil.hasAnnotation(clazz,Service.class)){
				SupportClass.loadClass(clazz);
			}
		}
	}
	
	final static class extClassLoader{
		static void getClass(String packageName){
			//转化为路径
			String packagePath = packageName.replace(".", "/");
			try {
				Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
				while(dirs.hasMoreElements()){
					URL url = dirs.nextElement();
					if("file".equals(url.getProtocol())){//如果是文件
						String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
						findClass(filePath,packageName,loadClass);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private static void findClass(String filePath,String packageName,Set<Class<?>> loadClass){
			File file = new File(filePath);
			File[] files = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.isDirectory() || file.getName().endsWith(".class");
				}
			});
			for(File f : files){
				if(f.isDirectory()){
					findClass(f.getPath(),packageName,loadClass);
				}else{
					String classname = f.getName().substring(0, f.getName().length()-6);
					String path = f.getAbsolutePath();
					path = path.replace("\\", ".");
					String classPath = path.substring(path.indexOf(packageName),path.indexOf(classname));
					try {
						System.out.println("已加载 ["+classPath+classname+"]");
						loadClass.add(Thread.currentThread().getContextClassLoader().loadClass(classPath+classname));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
}
