package com.comcast.app.controller;


public class DeadLockCreator {

	String s = "ABC";
	String t = "XYZ";

	public void deadLock() {

		Thread thread1 = new Thread("Thread-1") {

			public void run() {

				synchronized (s) {

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}


					synchronized (t) {

					}
				}
			}
		};
		Thread thread2 = new Thread("Thread-2") {

			public void run() {

				synchronized (t) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					synchronized (s) {

					}
				}
			}
		};

		thread1.start();

		thread2.start();

	}

}
