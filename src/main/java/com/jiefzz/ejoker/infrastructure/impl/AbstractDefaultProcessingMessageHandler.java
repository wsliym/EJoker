package com.jiefzz.ejoker.infrastructure.impl;

import static com.jiefzz.ejoker.z.common.system.extension.LangUtil.await;

import com.jiefzz.ejoker.infrastructure.IMessage;
import com.jiefzz.ejoker.infrastructure.IMessageDispatcher;
import com.jiefzz.ejoker.infrastructure.IProcessingMessage;
import com.jiefzz.ejoker.infrastructure.IProcessingMessageHandler;
import com.jiefzz.ejoker.z.common.context.annotation.context.Dependence;
import com.jiefzz.ejoker.z.common.system.extension.acrossSupport.SystemFutureWrapper;
import com.jiefzz.ejoker.z.common.task.AsyncTaskResult;
import com.jiefzz.ejoker.z.common.task.context.EJokerTaskAsyncHelper;

public abstract class AbstractDefaultProcessingMessageHandler<X extends IProcessingMessage<X, Y>, Y extends IMessage> implements IProcessingMessageHandler<X, Y> {

	@Dependence
	IMessageDispatcher messageDispatcher;
	
	@Dependence
	EJokerTaskAsyncHelper eJokerAsyncHelper;
	
	@Override
	public SystemFutureWrapper<AsyncTaskResult<Void>> handleAsync(X processingMessage) {
		return eJokerAsyncHelper.submit(() -> handle(processingMessage));
	}

	private void handle(X processingMessage) {
		Y message = processingMessage.getMessage();
		// TODO @await
		await(messageDispatcher.dispatchMessageAsync(message));
		processingMessage.complete();
	}

}
