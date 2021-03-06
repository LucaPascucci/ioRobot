/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.robotsonar;
import java.io.BufferedReader;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.QActorUtils;

public class Robotsonar extends AbstractRobotsonar { 
	private BufferedReader readerC;
	private int[] sensorData = new int[2];
	private int i = 0;

	public Robotsonar(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
	}
	
	public void startSonarC() {
		try {
			println("Start Robot Sonar C");
			Process p = Runtime.getRuntime().exec("sudo ./SonarAlone");
			this.readerC = new BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
			println("Process in C STARTED: " + this.readerC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDistanceFromSonar() {
		try {
			String strDistance = this.readerC.readLine();
			this.sensorData[this.i % 2] = Integer.parseInt(strDistance);
			this.i++;
			if (this.i >= 2) {
				int sum = 0;
				for (int k : this.sensorData) {
					sum += k;
				}
				int average = sum / 2;
				if (average < 10) {
					QActorUtils.raiseEvent(this.getQActorContext(), "sensor", "obstacle", "obstacle(" + average + ")");
				}
				println("RobotSonar " + average);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
