package com.jiefzz.ejoker.queue;

import java.util.Set;

public interface ITopicProvider<T> {
	
	String getTopic(T source);
	
    Set<String> GetAllTopics();
    
}
