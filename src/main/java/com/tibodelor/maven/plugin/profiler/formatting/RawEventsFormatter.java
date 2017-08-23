package com.tibodelor.maven.plugin.profiler.formatting;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tibodelor.maven.plugin.profiler.LifeCycleResult;
import com.tibodelor.maven.plugin.profiler.LifecycleEvent;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;

public class RawEventsFormatter implements ResultFormatter {

	@Override
	public void writeResultTo(LifeCycleResult result, Writer destination) throws Exception {
		for (LifecycleEvent event : result.getRawEvents()) {
			MavenProject project = event.getProject();
			List<String> projectsHierarchy = new ArrayList<String>();
			MavenProject tmpProject = project;
			while (tmpProject != null) {
				projectsHierarchy.add(tmpProject.getId());
				tmpProject = tmpProject.getParent();
			}
			Collections.reverse(projectsHierarchy);

			destination.append("------EVENT---- type: ").append(event.getType().toString());
			destination.append(", time: ").append(String.valueOf(event.getTimestampInMs()));
			MojoExecution mojoExecution = event.getExecution();
			if (mojoExecution != null) {
				destination.append(", executionId: ").append(mojoExecution.getExecutionId());
				destination.append(", goal: ").append(mojoExecution.getGoal());
				destination.append(", groupId: ").append(mojoExecution.getGroupId());
				destination.append(", artifactId: ").append(mojoExecution.getArtifactId());
				destination.append(", version: ").append(mojoExecution.getVersion());
			}
			if (project == null) {
				destination.append(", project: NONE????");
			} else {
				destination.append(", project: ").append(project.getId());
			}
			for (int i = 0; i < projectsHierarchy.size(); i++) {
				destination.append(", level").append(String.valueOf(
						i + 1)).append(": ").append(projectsHierarchy.get(i));
			}
			destination.append("\n");
			destination.flush();
		}
	}
}
