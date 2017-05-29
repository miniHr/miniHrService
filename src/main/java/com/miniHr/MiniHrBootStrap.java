package com.miniHr;

import java.io.File;
import java.io.FileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.miniHr.service","com.miniHr.dao.impl"})
public class MiniHrBootStrap {

	private static Logger log = LoggerFactory.getLogger(MiniHrBootStrap.class);


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try{
			log.info("miniHr正在启动服务。。。");
			String packageName = "com.miniHr.controller";//controller所在包
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
			SpringApplication.run(getAllClassByPackage(packageName), args);
			context.registerShutdownHook();
		}catch(Exception e){
			e.printStackTrace();
			log.error("miniHr启动异常");
		}
	}
	
	/**
	 * 根据包名获取当前包下的所有类
	 * @param packageName 包名
	 * @return
	 * @throws Exception
	 */
	public static Object[] getAllClassByPackage(String packageName) throws Exception{
		String scanPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() 
				+ packageName.replace(".", File.separator);
		/*获得字节码文件*/
		File[] files = new File(scanPath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().endsWith(".class"))
					return true;
				return false;
			}
		});
		Object[] classes = new Object[files.length + 1];
		classes[0] = MiniHrBootStrap.class;
		for(int i = 0; i < files.length; i ++){
			String fileName = files[i].getName();
			classes[i + 1] = Class.forName(packageName + "." + fileName.substring(0,fileName.indexOf(".")));
		}
		return classes;
	}
}
