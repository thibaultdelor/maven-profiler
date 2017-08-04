/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package io.tesla.lifecycle.profiler;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Pattern;

import org.apache.maven.cli.MavenCli;
import org.junit.Test;

public class LifecycleProfilerIT {

	@Test
	public void testIt() throws InterruptedException, IOException {
		File targetFolder = new File("target");
		if (!targetFolder.exists()) {
			fail("no target folder");
		}
		File sampleProject = new File(targetFolder, "test-classes/SampleMultiModuleProject");
		if (!targetFolder.exists()) {
			fail("no sampleProject folder");
		}
		File jar = null;
		Pattern jarPattern = Pattern.compile("tesla-profiler-.+\\.jar");
		for (String file : targetFolder.list()) {
			if (jarPattern.matcher(file).matches()) {
				jar = new File(targetFolder, file);
				break;
			}
		}

		if (jar == null || !jar.exists()) {
			fail("Can't find jar");
		}


		MavenCli mavenCli = new MavenCli();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		int result = mavenCli.doMain(
				new String[]{"clean","install", "-Dmaven.profile", "-Dmaven.ext.class.path="+jar.getAbsolutePath()},
				sampleProject.getAbsoluteFile().getAbsolutePath(), ps, ps);
		String content = new String(baos.toByteArray(), "UTF-8");

		System.out.printf("Process exited with result %d and output %s%n", result, content);

		assertTrue(content.contains("BUILD SUCCESS"));


	}
}
