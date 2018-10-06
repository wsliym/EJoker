package com.jiefzz.ejoker;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在ejoker.properties中封装一些跟执行环境相关的变量
 * @author JiefzzLon
*/
public final class EJokerEnvironment {
	
	private final static Logger logger = LoggerFactory.getLogger(EJokerEnvironment.class);

	public final static int ASYNC_INTERNAL_EXECUTE_THREADPOOL_SIZE;	
	
	public final static int ASYNC_IO_RETRY_THREADPOLL_SIZE;
	
	public final static long MAILBOX_IDLE_TIMEOUT;

	public final static int REPLY_PORT;

	/**
	 * 处理器数量
	 */
	public final static int NUMBER_OF_PROCESSOR = Runtime.getRuntime().availableProcessors();
	
	public final static int MAX_BATCH_COMMANDS;
	
	public final static int MAX_BATCH_EVENTS;
	
	public final static String ENVIROMMENT_FILE="ejoker.properties";

	static {

		// ## region start 加载相关公共变量配置
		Properties props = new Properties();
		try{
	 		props.load(EJokerEnvironment.class.getClassLoader().getResourceAsStream(ENVIROMMENT_FILE));
		}catch(Exception e){
			logger.warn("Could not load configure information from {}!", ENVIROMMENT_FILE);
			throw new RuntimeException(e);
		}
		// ## region end

		ASYNC_INTERNAL_EXECUTE_THREADPOOL_SIZE =
				Integer.valueOf(props.getProperty("ASYNC_INTERNAL_EXECUTE_THREADPOOL_SIZE", "256"));

		ASYNC_IO_RETRY_THREADPOLL_SIZE =
				Integer.valueOf(props.getProperty("ASYNC_IO_RETRY_THREADPOLL_SIZE", "64"));

		MAILBOX_IDLE_TIMEOUT =
				Long.valueOf(props.getProperty("MAILBOX_IDLE_TIMEOUT", "180000"));

		REPLY_PORT =
				Integer.valueOf(props.getProperty("REPLY_PORT", "65056"));

		MAX_BATCH_COMMANDS =
				Integer.valueOf(props.getProperty("MAX_BATCH_COMMANDS", "32"));

		MAX_BATCH_EVENTS =
				Integer.valueOf(props.getProperty("MAX_BATCH_EVENTS", "16"));

	}

}
