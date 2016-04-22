package com.jiefzz.ejoker.eventing;

import java.util.LinkedHashSet;
import java.util.Map;

import com.jiefzz.ejoker.annotation.persistent.PersistentIgnore;
import com.jiefzz.ejoker.infrastructure.AbstractSequenceMessage;

public class DomainEventStreamMessage extends AbstractSequenceMessage<String> {

	@PersistentIgnore
	private static final long serialVersionUID = -721576949011677756L;
	
	private String commandId;
	private Map<String, String> items;
	private LinkedHashSet<IDomainEvent> events;
	
	public DomainEventStreamMessage() {}
	
	public DomainEventStreamMessage(String commandId, String aggregateRootId, int version, String aggregateRootTypeName, LinkedHashSet<IDomainEvent> events, Map<String, String> items)
    {
        this.setCommandId(commandId);
        this.setAggregateRootId(aggregateRootId);
        this.setVersion(version);
        this.setAggregateRootTypeName(aggregateRootTypeName);
        this.setEvents(events);
        this.setItems(items);
    }

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public LinkedHashSet<IDomainEvent> getEvents() {
		return events;
	}

	public void setEvents(LinkedHashSet<IDomainEvent> events) {
		this.events = events;
	}
}