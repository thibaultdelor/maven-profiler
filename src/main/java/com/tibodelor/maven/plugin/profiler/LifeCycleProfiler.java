/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.tibodelor.maven.plugin.profiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;

@Named
@Singleton
public class LifeCycleProfiler implements EventSpy {


	private List<LifecycleEvent> events = new LinkedList<LifecycleEvent>();

	private void logEvent(LifecycleEvent event) {
		MavenProject project = event.getProject();
		List<String> projectsHierarchy = new ArrayList<String>();
		MavenProject tmpProject = project;
		while (tmpProject != null) {
			projectsHierarchy.add(tmpProject.getId());
			tmpProject = tmpProject.getParent();
		}
		Collections.reverse(projectsHierarchy);

		StringBuilder sb = new StringBuilder();
		sb.append("------EVENT---- type: ").append(event.getType());
		sb.append(", time: ").append(event.getTimestampInMs());
		MojoExecution mojoExecution = event.getExecution();
		if (mojoExecution != null) {
			sb.append(", executionId: ").append(mojoExecution.getExecutionId());
			sb.append(", goal: ").append(mojoExecution.getGoal());
			sb.append(", groupId: ").append(mojoExecution.getGroupId());
			sb.append(", artifactId: ").append(mojoExecution.getArtifactId());
			sb.append(", version: ").append(mojoExecution.getVersion());
		}
		if (project == null) {
			sb.append(", project: NONE????");
		} else {
			sb.append(", project: ").append(project.getId());
		}
		for (int i = 0; i < projectsHierarchy.size(); i++) {
			sb.append(", level").append(i + 1).append(": ").append(projectsHierarchy.get(i));
		}
		System.out.println(sb.toString());
	}


	@Override
	public void init(Context context) throws Exception {
		System.out.println("context = " + context);
	}

	@Override
	public void onEvent(Object event) throws Exception {
		if (event instanceof ExecutionEvent)
			events.add(new LifecycleEvent((ExecutionEvent) event));
	}

	@Override
	public void close() throws Exception {
		for (LifecycleEvent event : events) {
			logEvent(event);
		}
	}
}
