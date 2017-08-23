package com.tibodelor.maven.plugin.profiler;

import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import com.tibodelor.maven.plugin.profiler.formatting.RawEventsFormatter;
import com.tibodelor.maven.plugin.profiler.formatting.ResultFormatter;
import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;

@Named
@Singleton
public class LifeCycleProfiler implements EventSpy {


	private List<LifecycleEvent> events = new LinkedList<LifecycleEvent>();

	private ResultFormatter formatter;

	@Override
	public void init(Context context) throws Exception {
		formatter = new RawEventsFormatter();
	}

	@Override
	public void onEvent(Object event) throws Exception {
		if (event instanceof ExecutionEvent)
			events.add(new LifecycleEvent((ExecutionEvent) event));
	}

	@Override
	public void close() throws Exception {
		LifeCycleResult lifeCycleResult = new LifeCycleResult(events);
		formatter.writeResultTo(lifeCycleResult, new OutputStreamWriter(System.out));
	}
}
