package com.jiefzz.ejoker.z.common.task.context;

import java.io.IOException;

import com.jiefzz.ejoker.z.common.context.annotation.context.Dependence;
import com.jiefzz.ejoker.z.common.context.annotation.context.EService;
import com.jiefzz.ejoker.z.common.io.AsyncTaskResult;
import com.jiefzz.ejoker.z.common.io.AsyncTaskStatus;
import com.jiefzz.ejoker.z.common.io.IOExceptionOnRuntime;
import com.jiefzz.ejoker.z.common.system.extension.acrossSupport.SystemFutureWrapper;
import com.jiefzz.ejoker.z.common.task.context.lambdaSupport.IFunction;
import com.jiefzz.ejoker.z.common.task.context.lambdaSupport.IVoidFunction;

import co.paralleluniverse.fibers.SuspendExecution;

/**
 * 包装一下使异步任务返回AsyncTaskResult&lt;T&gt;结构
 * @author kimffy
 *
 */
@EService
public class EJokerAsyncHelper {

	@Dependence
	private SystemAsyncHelper systemAsyncHelper;

	public SystemFutureWrapper<AsyncTaskResult<Void>> submit(IVoidFunction vf) {
		return systemAsyncHelper.submit(() -> {
			try {
				vf.trigger();
				return new AsyncTaskResult<>(AsyncTaskStatus.Success);
			} catch (Exception ex) {
				if(ex instanceof SuspendExecution)
					throw (SuspendExecution )ex;
				return new AsyncTaskResult<>(
						((ex instanceof IOException || ex instanceof IOExceptionOnRuntime) ? AsyncTaskStatus.IOException : AsyncTaskStatus.Failed),
						ex.getMessage(),
						null
				);
			}
		});
	}

	public <T> SystemFutureWrapper<AsyncTaskResult<T>> submit(IFunction<T> vf) {
		return systemAsyncHelper.submit(() -> {
			try {
				T r = vf.trigger();
				return new AsyncTaskResult<>(AsyncTaskStatus.Success, null, r);
			} catch (Exception ex) {
				if(ex instanceof SuspendExecution)
					throw (SuspendExecution )ex;
				return new AsyncTaskResult<>(
						((ex instanceof IOException || ex instanceof IOExceptionOnRuntime) ? AsyncTaskStatus.IOException : AsyncTaskStatus.Failed),
						ex.getMessage(),
						null
				);
			}
		});
	}

}
