%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.robotSystem.pl 
%====================================================================================
context(ctxradar, "172.20.10.2",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "172.20.10.4",  "TCP", "8133" ).  		 
context(ctxrobot, "172.20.10.4",  "TCP", "8079" ).  		 
%%% -------------------------------------------
qactor( radargui , ctxradar, "it.unibo.radargui.MsgHandle_Radargui"   ). %%store msgs 
qactor( radargui_ctrl , ctxradar, "it.unibo.radargui.Radargui"   ). %%control-driven 
qactor( usercmdhandler , ctxradar, "it.unibo.usercmdhandler.MsgHandle_Usercmdhandler"   ). %%store msgs 
qactor( usercmdhandler_ctrl , ctxradar, "it.unibo.usercmdhandler.Usercmdhandler"   ). %%control-driven 
qactor( sensorsonar , ctxsensoremitter, "it.unibo.sensorsonar.MsgHandle_Sensorsonar"   ). %%store msgs 
qactor( sensorsonar_ctrl , ctxsensoremitter, "it.unibo.sensorsonar.Sensorsonar"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","alarm,obstacle,sensordata").  
%%% -------------------------------------------
qactor( robot_actor , ctxrobot, "it.unibo.robot_actor.MsgHandle_Robot_actor" ). 
qactor( robot_actor_ctrl , ctxrobot, "it.unibo.robot_actor.Robot_actor" ). 

