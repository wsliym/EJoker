package com.jiefzz.ejoker.z.common.rpc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jiefzz.ejoker.z.common.system.extension.acrossSupport.RipenFuture;
import com.jiefzz.ejoker.z.common.system.functional.IVoidFunction1;

/**
 * 不使用Java对象动态代理技术。
 * <br> 框架上下文内，同意端口，就约定同一类对象调用，方式、参数类型都相同。
 */
public interface IRPCService {
	
	final static Map<Integer, RPCTuple> portMap = new HashMap<>();
	
	final static Map<Integer, AtomicBoolean> serverPortOccupation = new HashMap<>();
	
	default public void export(IVoidFunction1<String> action, int port) {
		export(action, port, false);
	}
	
	public void export(IVoidFunction1<String> action, int port, boolean waitFinished);
	
	public void remoteInvoke(String data, String host, int port);
	
	public void removeExport(int port);

	public static class RPCTuple {
		
		public final Thread ioThread;
		
		public final IVoidFunction1<String> closeAction;
		
		public final RipenFuture<Void> initialFuture;
		
		public RPCTuple(IVoidFunction1<String> closeAction, Thread ioThread) {
			this.ioThread = ioThread;
			this.closeAction = closeAction;
			this.initialFuture = new RipenFuture<>();
		}
		
	}
}
