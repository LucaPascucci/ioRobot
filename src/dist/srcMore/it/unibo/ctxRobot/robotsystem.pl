%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.robotSystem.pl 
%====================================================================================
context(ctxradar, "172.20.10.2",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "172.20.10.4",  "TCP", "8133" ).  		 
context(ctxrobot, "172.20.10.14",  "TCP", "8079" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( alarmhandler , ctxradar, "it.unibo.alarmhandler.MsgHandle_Alarmhandler"   ). %%store msgs 
qactor( alarmhandler_ctrl , ctxradar, "it.unibo.alarmhandler.Alarmhandler"   ). %%control-driven 
qactor( photoreceiver , ctxradar, "it.unibo.photoreceiver.MsgHandle_Photoreceiver"   ). %%store msgs 
qactor( photoreceiver_ctrl , ctxradar, "it.unibo.photoreceiver.Photoreceiver"   ). %%control-driven 
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
qactor( robotsonar , ctxrobot, "it.unibo.robotsonar.MsgHandle_Robotsonar"   ). %%store msgs 
qactor( robotsonar_ctrl , ctxrobot, "it.unibo.robotsonar.Robotsonar"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","alarm,cmd").  
%%% -------------------------------------------
qactor( robot_actor , ctxrobot, "it.unibo.robot_actor.MsgHandle_Robot_actor" ). 
qactor( robot_actor_ctrl , ctxrobot, "it.unibo.robot_actor.Robot_actor" ). 

