package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class armGeneralMovements {

    armRobotInit robot = new armRobotInit();


    String opMode ="";


    //movement functions
    public void raise(double inches) {

        int newelbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newelbowMotorTarget = robot.elbowMotor.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
        robot.elbowMotor.setTargetPosition(newelbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));

        // Stop all motion;
        robot.elbowMotor.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void lower(double inches) {

        int newelbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newelbowMotorTarget = robot.elbowMotor.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
        robot.elbowMotor.setTargetPosition(newelbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));

        // Stop all motion;
        robot.elbowMotor.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
        
        
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
