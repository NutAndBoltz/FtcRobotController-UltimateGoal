//FILE NOT BEING USED IN 2021 SEASON FOR FILE STRUCTURE SIMPLICITY

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class generalMovements{
    int cameraMonitorViewId=0; //used for Vuforia_Thread

    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    //auto mode functions
    public void resetEncoder()
    {
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void startEncoderMode()
    {
        //Set Encoder Mode
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void stopEncoderMode()
    {
        robot.motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



    //movement functions
    public void moveForward(double inches)
    {
        String opMode=this.opMode;

        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));

            // Stop all motion;
            stopRobot();

            // Turn off RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
        else
        {
            moveForward();
        }
    }
    public void moveForward()
    {

        robot.motorFL.setPower(robot.teleOP_FORWARD_SPEED);       robot.motorFR.setPower(robot.teleOP_FORWARD_SPEED);
        robot.motorBL.setPower(robot.teleOP_FORWARD_SPEED);       robot.motorBR.setPower(robot.teleOP_FORWARD_SPEED);

    }
    public void moveForward(float speed, String mode)
    {
        robot.motorFL.setPower(speed);       robot.motorFR.setPower(speed);
        robot.motorBL.setPower(speed);       robot.motorBR.setPower(speed);


    }

    public void moveBackward(double inches)
    {
        String opMode=this.opMode;

        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;



            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));

            // Stop all motion;
            stopRobot();

            // Turn off RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
        else
        {
            moveBackward();
        }
    }
    public void moveBackward()
    {

        robot.motorFL.setPower(-robot.teleOP_FORWARD_SPEED);      robot.motorFR.setPower(-robot.teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(-robot.teleOP_FORWARD_SPEED);      robot.motorBR.setPower(-robot.teleOP_FORWARD_SPEED);

    }
    public void moveBackward(float speed, String mode)
    {

        robot.motorFL.setPower(-speed);      robot.motorFR.setPower(-speed);

        robot.motorBL.setPower(-speed);      robot.motorBR.setPower(-speed);

    }
//    void reportTick()
//    {
//        int FLnow =robot.motorFL.getCurrentPosition();
//        int FRnow =robot.motorFR.getCurrentPosition();
//        int BLnow =robot.motorBL.getCurrentPosition();
//        int BRnow =robot.motorBR.getCurrentPosition();
//        while (opModeIsActive() &&
//                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {
//
//            // Display it for the driver.
//            telemetry.addData("motorFL", "%7d", Math.abs(robot.motorFL.getCurrentPosition()-FLnow));
//            telemetry.addData("motorFR", "%7d", Math.abs(robot.motorFR.getCurrentPosition()-FRnow));
//            telemetry.addData("motorBL", "%7d", Math.abs(robot.motorBL.getCurrentPosition()-BLnow));
//            telemetry.addData("motorBR", "%7d", Math.abs(robot.motorBR.getCurrentPosition()-BRnow));
//            telemetry.update();
//            // Turn off RUN_TO_POSITION
//            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        }
//
//    }

    public void moveRight(double inches)
    {
        String opMode=this.opMode;
        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;


            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));

            // Stop all motion;
            stopRobot();

            // Turn off RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
        else
        {
            moveRight();
        }
    }
    public void moveRight()
    {
        robot.motorFL.setPower(robot.teleOP_FORWARD_SPEED);       robot.motorFR.setPower(-robot.teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(-robot.teleOP_FORWARD_SPEED);      robot.motorBR.setPower(robot.teleOP_FORWARD_SPEED);

    }
    public void moveRight(float speed, String mode)
    {
        robot.motorFL.setPower(speed);       robot.motorFR.setPower(-speed);

        robot.motorBL.setPower(-speed);      robot.motorBR.setPower(speed);

    }
    public void driftRight(float speed, String mode)
    {
        robot.motorFL.setPower(0);       robot.motorFR.setPower(0);

        robot.motorBL.setPower(-speed);      robot.motorBR.setPower(speed);

    }
    public void moveLeft(double inches)
    {
        String opMode=this.opMode;
        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;


            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));

            // Stop all motion;
            stopRobot();

            // Turn off RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        }
        else
        {
            moveLeft();
        }
    }
    public void moveLeft()
    {
        robot.motorFL.setPower(-robot.teleOP_FORWARD_SPEED);      robot.motorFR.setPower(robot.teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(robot.teleOP_FORWARD_SPEED);       robot.motorBR.setPower(-robot.teleOP_FORWARD_SPEED);

    }
    public void moveLeft(float speed, String mode)
    {
        robot.motorFL.setPower(-speed);      robot.motorFR.setPower(speed);

        robot.motorBL.setPower(speed);       robot.motorBR.setPower(-speed);

    }
    public void driftLeft(float speed, String mode)
    {
        robot.motorFL.setPower(0);      robot.motorFR.setPower(0);

        robot.motorBL.setPower(speed);       robot.motorBR.setPower(-speed);

    }
    public void turnleft(double speed)
    {

        robot.motorFL.setPower(-speed);         robot.motorFR.setPower(speed);
        robot.motorBL.setPower(-speed);         robot.motorBR.setPower(speed);
    }
    public void turnright(double speed)
    {

        robot.motorFL.setPower(speed);          robot.motorFR.setPower(-speed);
        robot.motorBL.setPower(speed);          robot.motorBR.setPower(-speed);
    }

    public void grabWobble(){
        //move motor down
//        robot.elbowMotor.setPower(1);
        runtime.reset();
        while (runtime.seconds() < 0.2){
        }

        //unclamp servo
//        robot.elbowMotor.setPower(0);
        robot.wobbleSnatcher.setPosition(0.6);

        //wait
        runtime.reset();
        while (runtime.seconds() < 1){
        }

        //clamp wobble goal
        robot.wobbleSnatcher.setPosition(0.3);

        //move arm back up
//        robot.elbowMotor.setPower(-1);
        runtime.reset();
        while (runtime.seconds() < 0.2){
        }
//        robot.elbowMotor.setPower(0);
    }

    public void launchRing(){
        //servo pushes ring forward
        //wheel spins until launch, spin speed = distance launched
    }

    public void stopRobot()
    {
        robot.motorFL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
    }
//    public void stopRobot(int seconds)
//    {
//        //delay
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < seconds)) {
//            stopRobot();
//            telemetry.addData("Motor", "Stopped");    //
//            telemetry.update();
//        }
//    }



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
