/* Generated by AN DISI Unibo */
/*
This code is generated only ONCE
*/
package it.unibo.radargui;

import java.util.HashMap;
import java.util.Map;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.QActorUtils;

public class Radargui extends AbstractRadargui {
	private static final int DMIN_EXPRESSION = 70;
	private static final int DMIN_SONAR = 40;
	private static final int NUM_OF_SONARS = 2;
	private int sensorToReach = 1;
	private int minSensor1 = Integer.MAX_VALUE;
	private int minSensor2 = Integer.MAX_VALUE;
	private Map<Integer, Integer> sensorsData = new HashMap<>();
	protected RadarControl radarControl;

	public Radargui(String actorId, QActorContext myCtx, IOutputEnvView outEnvView) throws Exception {
		super(actorId, myCtx, outEnvView);
	}

	public boolean activateGui() throws Exception {
		println(this.getName() + " activateGui ");
		this.radarControl = new RadarControl(this.myCtx, this.outEnvView);
		return true;
	}

	public void sendDataToGui(int distance, int angle) {
		if (angle == 330 && distance < this.minSensor1 && this.sensorToReach == 1) {
			this.minSensor1 = distance;
		} else if (angle == 30 && distance < this.minSensor2 && this.sensorToReach == 2) {
			this.minSensor2 = distance;
		}
		println("sendDataToGui: " + distance + " " + angle + " MIN1: " + this.minSensor1 + " MIN2: " + this.minSensor2);
		this.radarControl.update(Integer.toString(distance), Integer.toString(angle));
		this.addRule("p(" + distance + "," + angle + ")");
	}
	
	public void reset() {
		println("SONO NEL RESET");
		this.sensorsData.clear();
		this.sensorToReach = 1;
		
		this.minSensor1 = Integer.MAX_VALUE;
		this.minSensor2 = Integer.MAX_VALUE;
	}
	
	public void checkSonars(int distance, int angle) throws Exception {
		this.sensorsData.put(angle, distance);

		if (this.sensorsData.get(330) <= DMIN_SONAR && this.sensorToReach == 1 ) {
			QActorUtils.raiseEvent(this.getQActorContext(), "radargui", "reachedsensor", "reachedsensor(" + this.sensorToReach + ")");
			this.sensorToReach = 2;
		} else if (this.sensorsData.get(30) < DMIN_SONAR && this.sensorToReach == 2) {
			QActorUtils.raiseEvent(this.getQActorContext(), "radargui", "reachedsensor", "reachedsensor(" + this.sensorToReach + ")");
			this.sensorToReach = -1;
		} else if (this.sensorsData.size() == 2 && sensorToReach == 1) {
			int numerator = this.sensorsData.values().stream().mapToInt(Number::intValue).sum();
			int denominator = NUM_OF_SONARS - this.sensorToReach + 1;
			int valueOfExpression = numerator / denominator;
			if (valueOfExpression < DMIN_EXPRESSION) {
				QActorUtils.raiseEvent(this.getQActorContext(), "radargui", "alarm", "alarm(" + valueOfExpression + ")");
			}
		}
	}
}
