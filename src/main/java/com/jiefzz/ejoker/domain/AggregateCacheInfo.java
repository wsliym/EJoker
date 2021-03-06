package com.jiefzz.ejoker.domain;

public class AggregateCacheInfo {

	public IAggregateRoot aggregateRoot;
    public long lastUpdateTime;

    public AggregateCacheInfo(IAggregateRoot aggregateRoot) {
        this.aggregateRoot = aggregateRoot;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public boolean isExpired(long timeoutMilliseconds) {
        return 0 < (System.currentTimeMillis() - lastUpdateTime - timeoutMilliseconds);
    }
    
}
