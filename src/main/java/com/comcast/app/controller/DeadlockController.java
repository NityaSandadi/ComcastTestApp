package com.comcast.app.controller;

import java.util.Iterator;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Rest controller that creates deadlock and checks the status of the threads.
 */
@RestController
@RequestMapping(value = "/deadlock")
public class DeadlockController {

	private DeadLockCreator deadlockCreator = new DeadLockCreator();

	@PostMapping(value = "/")
	public ResponseEntity<?> startDeadlock() {
		deadlockCreator.deadLock();
		return ResponseEntity.ok("ok");

	}

	@GetMapping(value = "/status")
	public ResponseEntity<?> getStatus() {
		Map allThreads = Thread.getAllStackTraces();

		Iterator iterator = allThreads.keySet().iterator();

		StringBuffer stringBuffer = new StringBuffer();

		while (iterator.hasNext()) {

			Thread key = (Thread) iterator.next();
			if (key.getName().equals("Thread-1") || key.getName().equals("Thread-2")) {
				StackTraceElement[] trace = (StackTraceElement[]) allThreads.get(key);

				stringBuffer.append(key + "\r\n");

				for (int i = 0; i < trace.length; i++) {

					stringBuffer.append(" " + trace[i] + "\r\n");

				}
				stringBuffer.append("");
			}

		}

		String dump = stringBuffer.toString();
		return ResponseEntity.ok(dump);

	}

}
