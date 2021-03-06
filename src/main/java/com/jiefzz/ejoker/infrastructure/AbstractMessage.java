package com.jiefzz.ejoker.infrastructure;

import com.jiefzz.ejoker.utils.EObjectId;

public abstract class AbstractMessage implements  IMessage {

	private String id;
	private int sequence;
	private long timestamp;
	
	public AbstractMessage() {
		id=EObjectId.generateHexStringId();
		timestamp=System.currentTimeMillis();
		sequence=1;
	}
	
	@Override
	public String getRoutingKey() {
		return null;
	}

	@Override
	public String getTypeName() {
		return this.getClass().getName();
	}

	@Override
	public void setId(String id) {
		this.id = id;
	};

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
