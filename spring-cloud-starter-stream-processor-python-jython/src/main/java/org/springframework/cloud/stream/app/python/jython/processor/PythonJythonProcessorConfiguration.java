/*
 * Copyright 2017 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.springframework.cloud.stream.app.python.jython.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.app.python.jython.JythonScriptConfiguration;
import org.springframework.cloud.stream.app.python.jython.JythonScriptExecutor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * A Processor that runs a Jython script.
 *
 * @author David Turanski
 **/
@EnableBinding(Processor.class)
@Import({ JythonScriptConfiguration.class })
public class PythonJythonProcessorConfiguration {

	@Autowired
	private JythonScriptExecutor jythonWrapper;

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Object process(Message<?> message) {
		return jythonWrapper.execute(message);
	}
}