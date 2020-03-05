package cn.cicoding.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jUtil {

    private static Logger logger = Logger.getLogger(Log4jUtil.class);

    public void createLogger(String loggerType,String info){
    	String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    	path = path.substring(1, (path.length()-8)) + "log4j/log4j.properties";
    	PropertyConfigurator.configure(ClassLoader.getSystemResource(path));
        if(loggerType.equalsIgnoreCase("info")){
            logger.info(info);
        }else if(loggerType.equalsIgnoreCase("debug")){
            logger.debug(info);
        }else if(loggerType.equalsIgnoreCase("error")){
            logger.error(info);
        }
    }
}