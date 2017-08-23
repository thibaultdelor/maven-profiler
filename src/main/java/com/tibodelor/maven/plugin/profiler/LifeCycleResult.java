package com.tibodelor.maven.plugin.profiler;

import java.util.List;

public class LifeCycleResult {

	private final List<LifecycleEvent> events;

	public LifeCycleResult(List<LifecycleEvent> events) {
		this.events = events;
	}

	public List<LifecycleEvent> getRawEvents() {
		return events;
	}
}
