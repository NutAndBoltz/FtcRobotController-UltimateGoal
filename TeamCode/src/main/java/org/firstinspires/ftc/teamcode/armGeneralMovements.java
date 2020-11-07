package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class armGeneralMovements {
    int cameraMonitorViewId=0; //used for Vuforia_Thread

    armRobotInit robot = new armRobotInit();
    ElapsedTime runtime = new ElapsedTime();


    String opMode ="";

    public void setMode(int opMode) {
        if(opMode==1)
        {
            this.opMode="AUTO";
        }
        else
        {
            this.opMode ="TELEOP";
        }
    }
}
