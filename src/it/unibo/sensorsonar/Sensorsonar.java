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
	  protected String distance = ""; // conterr� stringhe del tipo: d( distance )
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
	      //println("Process in C READS: " + getDistanceFromSonar());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public void getDistanceFromSonar(int pos) {
	    try {
	      String inpS = readerC.readLine();
	      this.distance = "data( "+ counter++ + ", distance, d("+inpS+") )"; 
	      println("getDistanceFromSonar " + inpS);
	      //int inpSInt = Integer.parseInt(inpS);
	      this.addRule("obstacledata("+ inpS +"," + pos +")" );
	      //return inpSInt;
	    } catch (Exception e) {
	      e.printStackTrace();
	      //return 0;
	    }

	  }
	}
