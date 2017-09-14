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
		println("sendDataToGui: " + distance + " " + angle);
		this.radarControl.update(Integer.toString(distance), Integer.toString(angle));
	}
	
	public void reset() {
		println("SONO NEL RESET");
		this.sensorsData.clear();
		this.sensorToReach = 1;
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
