/* Generated by AN DISI Unibo */
/*
This code is generated only ONCE
*/
package it.unibo.sensorsonar;

import java.io.BufferedReader;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.QActorContext;

public class Sensorsonar extends AbstractSensorsonar {
	protected BufferedReader readerC;
	protected int counter = 1;

	public Sensorsonar(String actorId, QActorContext myCtx, IOutputEnvView outEnvView) throws Exception {
		super(actorId, myCtx, outEnvView);
	}

	public void startSonarC() {
		try {
			println("Start Sonar C");
			Process p = Runtime.getRuntime().exec("sudo ./SonarAlone");
			this.readerC = new BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
			println("Process in C STARTED: " + this.readerC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDistanceFromSonar(int pos) {
		try {
			String inpS = this.readerC.readLine();
			println("getDistanceFromSonar " + inpS);
			this.addRule("obstacledata(" + inpS + "," + pos + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
