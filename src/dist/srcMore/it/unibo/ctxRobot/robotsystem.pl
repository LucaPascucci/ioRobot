%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.robotSystem.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8079" ).  		 
%%% -------------------------------------------
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","alarm,obstacle,sensordata").  
%%% -------------------------------------------
qactor( robot_actor , ctxrobot, "it.unibo.robot_actor.MsgHandle_Robot_actor" ). 
qactor( robot_actor_ctrl , ctxrobot, "it.unibo.robot_actor.Robot_actor" ). 

