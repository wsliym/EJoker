package com.jiefzz.ejoker.commanding;

public class AggregateRootAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -3883348844724461341L;
	
	private final static String exceptionMessage = "Aggregate root [type=%s,id=%s] already exist in command context, cannot be added again.";

	public AggregateRootAlreadyExistException(Object id, Class type) {
		super(String.format(exceptionMessage, type.getName(), id));
	}

}
