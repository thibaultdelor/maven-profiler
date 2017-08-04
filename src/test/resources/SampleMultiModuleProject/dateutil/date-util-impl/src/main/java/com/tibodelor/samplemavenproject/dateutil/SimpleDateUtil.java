/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.tibodelor.samplemavenproject.dateutil;

import java.util.Date;

public class SimpleDateUtil implements DateUtil{
	public Date getMeADate() {
		return new Date();
	}
}
