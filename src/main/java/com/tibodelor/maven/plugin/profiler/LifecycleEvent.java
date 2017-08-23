package com.tibodelor.maven.plugin.profiler;

import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;

public class LifecycleEvent {
	private final ExecutionEvent.Type type;
	private final MojoExecution execution;
	private final long timestampInMs;
	private final MavenProject project;

	public LifecycleEvent(ExecutionEvent event) {
		this.type = event.getType();
		this.execution = event.getMojoExecution();
		this.timestampInMs = System.currentTimeMillis();
		this.project = event.getProject();
	}


	public ExecutionEvent.Type getType() {
		return type;
	}

	public MojoExecution getExecution() {
		return execution;
	}

	public long getTimestampInMs() {
		return timestampInMs;
	}

	public MavenProject getProject() {
		return project;
	}
}
