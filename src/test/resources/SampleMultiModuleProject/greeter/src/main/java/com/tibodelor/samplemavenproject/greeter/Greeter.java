/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.tibodelor.samplemavenproject.greeter;

import com.tibodelor.samplemavenproject.dateutil.DateUtil;

public class Greeter {

	private final DateUtil dateUtil;

	public Greeter(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}

	public String welcome(String name){
		return "Welcome "+name+"!! It's "+dateUtil.getMeADate().getYear();
	}
}
