
	# 异步线程池大小(Quasar模式下无效)
	ASYNC_INTERNAL_EXECUTE_THREADPOOL_SIZE = 256
	
	# io失败重试线程池大小
	ASYNC_IO_RETRY_THREADPOLL_SIZE = 64
		
	# 消息异步发送(Quasar模式下请置为true)
	ASYNC_EJOKER_MESSAGE_SEND = false
	
	# 消息异步发送线程池大小
	ASYNC_MESSAGE_SENDER_THREADPOLL_SIZE = 128
	
	# 控制在处理中消息数量
	FLOW_CONTROL_ON_PROCESSING = false
	
	# 最大处理中消息数
	MAX_AMOUNT_OF_ON_PROCESSING_MESSAGE = ASYNC_INTERNAL_EXECUTE_THREADPOOL_SIZE/2 + NUMBER_OF_PROCESSOR
			
	# 对象消息内存mailbox副本最大超时时间(毫秒)
	MAILBOX_IDLE_TIMEOUT = 180000
			
	# 内存中聚合对象副本的最大超时时间(毫秒)
	AGGREGATE_IN_MEMORY_EXPIRE_TIMEOUT = 180000
			
	# 消息回复的监听端口
	REPLY_PORT = 65056
	
	# 是否支持事件批处理		
	SUPPORT_BATCH_APPEND_EVENT = false
			
	# 最大单次批处理命令数
	MAX_BATCH_COMMANDS = 16
			
	# 最大单次批处理事件数
	MAX_BATCH_EVENTS = 32

