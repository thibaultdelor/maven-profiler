package com.tibodelor.maven.plugin.profiler.formatting;

import java.io.Writer;

import com.tibodelor.maven.plugin.profiler.LifeCycleResult;

public interface ResultFormatter {
	void writeResultTo(LifeCycleResult result, Writer destination) throws Exception;
}
