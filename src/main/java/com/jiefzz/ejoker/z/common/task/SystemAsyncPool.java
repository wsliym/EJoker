package com.jiefzz.ejoker.z.common.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jiefzz.ejoker.z.common.task.context.lambdaSupport.IFunction;

public class SystemAsyncPool implements IAsyncEntrance {
	
	private final static Logger logger = LoggerFactory.getLogger(SystemAsyncPool.class);

	private ExecutorService defaultThreadPool;
	
	public SystemAsyncPool(int threadPoolSize) {
		this(threadPoolSize, false);
	}
	
	private BlockingQueue<Runnable> taskQueue = null;
	
	private AtomicLong aliveCount = new AtomicLong(0l);
	
	public SystemAsyncPool(int threadPoolSize, boolean prestartAllThread) {
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//				60L, TimeUnit.SECONDS,
//				new SynchronousQueue<Runnable>()) {
//			@Override
//			protected void beforeExecute(Thread t, Runnable r) {
//				aliveCount.getAndIncrement();
//			}
//
//			@Override
//			protected void afterExecute(Runnable r, Throwable t) {
//				aliveCount.decrementAndGet();
//			}
//		};
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 0l, TimeUnit.MILLISECONDS,
				taskQueue = new LinkedBlockingQueue<Runnable>()) {

					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						aliveCount.getAndIncrement();
					}

					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						aliveCount.decrementAndGet();
					}
			
		};
		if(prestartAllThread)
			threadPoolExecutor.prestartAllCoreThreads();
		defaultThreadPool = threadPoolExecutor;
	}
	
	public void debugInfo(String poolName) {
		logger.error("pool: {}, aliveThread: {}, waiting: {}", poolName, aliveCount.get(), null == taskQueue? 0 : taskQueue.size());
	}

	@Override
	public <TAsyncTaskResult> Future<TAsyncTaskResult> execute(IFunction<TAsyncTaskResult> asyncTaskThread) {
		
		// @important 建立新线程存在线程上限和大量的上下文切换成本，极易发生OutOfMemory。
		// @important 或者使用cachedThreadPool？？
		
		// @important CompletableFuture.runAsync 有大量的 ForkJoinPool开销，且我对新版本的线程理念还不熟。
//		{
//			RipenFuture<TAsyncTaskResult> ripenFuture = new RipenFuture<>();
//			CompletableFuture.runAsync(() -> {
//				try {
//					TAsyncTaskResult result = asyncTaskThread.call();
//					ripenFuture.trySetResult(result);
//				} catch (Exception ex) {
//					ripenFuture.trySetException(e);
//				}
//			});
//			return ripenFuture;
//		}
		
		// @important 使用线程池模式的话，正常的情况还好，但是有一个棘手的问题，
		// @important 在整个系统中某一个有超过系统空闲线程（或这个数量级附近时），
		// @important 假设这些存量线程都在开新任务并异步等待结果的话，
		// @important 系统会处于死锁和饿死状态中（对旧线程时死锁，对新任务则是饿死），
		// @important 旧的任务将永远等不到他们的结果，新的任务却没有任何新线程来处理。
		// @important 不治本的解决方法:
		// @important  1. 基于框架的幂等性，我们可以重启程序，重新接受指令。
		// @important  2. 采用线程池占满补偿方案：
		// @important		采用游离线程处理等待中的任务，且不接受超过n毫秒的任务，超时即杀死，并向提交此任务的线程返回异常。
		// @important		* 可能长时间存在游离线程一直被杀死，且提交线程不断重试的情况。
		return defaultThreadPool.submit(asyncTaskThread::trigger);
		
		// TODO important
		// @important 根治的解决方法使用coroutine方案，目前考虑使用 Quasar 方案
	}

	public void shutdown() {
		defaultThreadPool.shutdown();
	}

}