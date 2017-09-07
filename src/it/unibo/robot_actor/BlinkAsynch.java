package it.unibo.robot_actor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import it.unibo.bls.raspberry.components.DeviceLedPi4j;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedActiveObject;

public class BlinkAsynch extends SituatedActiveObject {
	private DeviceLedPi4j led;
	private boolean goon = true;
	private int blink_delay = 300;

	public BlinkAsynch(String name, IOutputEnvView outEnvView, DeviceLedPi4j led) {
		super(outEnvView, name);
		this.led = led;
	}

	public void activate() {
		int numberOfCores = Runtime.getRuntime().availableProcessors();
		ScheduledExecutorService executorNumberOfCores = Executors.newScheduledThreadPool(numberOfCores);
		super.activate(executorNumberOfCores);
	}

	public void suspendWork() {
		goon = false;
	}

	@Override
	protected void startWork() throws Exception {
		goon = true;
	}

	@Override
	protected void doJob() throws Exception {
		while (goon) {
			led.turnOn();
			delay(this.blink_delay);
			if (!goon) {
				break;
			}
			led.turnOff();
			delay(this.blink_delay);
		}
	}

	@Override
	protected void endWork() throws Exception {
		led.turnOff();
	}

	protected static void delay(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}