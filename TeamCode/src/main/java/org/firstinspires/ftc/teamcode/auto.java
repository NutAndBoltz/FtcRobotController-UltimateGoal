package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

@Autonomous(name="Auto", group="Pushbot")
public class auto extends LinearOpMode {

    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    OpenCvCamera webcam;
    SkystoneDeterminationPipeline pipeline;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);

        pipeline = new SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);

        resetEncoder();
        startEncoderMode();

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
        });

        // Wait for the game to start (driver presses PLAY)
        telemetry.addLine("Waiting for start");
        telemetry.update();
        waitForStart();

        // wait for 3 seconds for camera to register num of rings
        sleep(2500);

        //Place wobble goal in the correct target zone
        if (pipeline.position == SkystoneDeterminationPipeline.RingPosition.FOUR) {
            telemetry.addData("Detected", "four rings!");
            telemetry.update();

            pickUpWobble();

            //clear the rings
            moveRight(8);

            //move to launch line
            moveForward(51);

            //center robot to front of goal
            moveLeft(10);

            //launch 3 preloaded rings
            launchRingHigh(3);

            //turn to face C box
            turnleft(8);

            //move to box
            moveForward(60);

            //Place the wobble goal
            dropWobbleGoal();
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            }
            // Predicted end, need to drive to park line

//
//            //Turn to face other wobble goal at start line
//            turnleft(40);
//
//            //move to wobble goal
//            moveForward(50);
//
//            //pick up wobble goal
//            pickUpWobble();
//
//            //turn to face box
//            turnleft(40);
//
//            //move to box C
//            moveForward(80);
//
//            //Place the wobble goal
//            dropWobbleGoal();
//            runtime.reset();
//            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
//            }

            //drive to parking line
            moveBackward(50);
        }

        else if (pipeline.position == SkystoneDeterminationPipeline.RingPosition.ONE) {
            telemetry.addData("Detected", "one ring"); //1 = B, middle
            telemetry.update();

            pickUpWobble();

            //move to launch line       //90 in total distance
            moveForward(56);

            moveLeft(16);

            //launch 3 preloaded rings
            launchRingHigh(3);

            moveForward(25);

            //Place the wobble goal
            dropWobbleGoal();
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            }

            raise(40);

            //angle to 2nd wobble
            turnleft(44);


            lower(80);
            moveForward(50);

            //pick up wobble without raising first
            robot.wobbleSnatcher.setPosition(0.3);

            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 0.5) { }
            raise(150);

            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 0.1) { }

            moveBackward(50);

            turnright(50);

//            turnright(10);
//
//            dropWobbleGoal(); //to bring the arm down
//            moveBackward(30);
//
//            turnright(43);
//
//            moveForward(22);
//            sleep(1000);
//
//            //pick up wobble without raising first
//            robot.wobbleSnatcher.setPosition(0.3);
//
//            runtime.reset();
//            while (opModeIsActive() && runtime.seconds() < 0.5) { }
//            raise(150);
//
//            runtime.reset();
//            while (opModeIsActive() && runtime.seconds() < 0.1) { }
//
//
//            moveBackward(22);
//
//            turnleft(43);
//
//            moveForward(30);

            //Place the wobble goal
            dropWobbleGoal();
/*
            //Move away from the rings on the field
            moveLeft(38);

            //move to launch line       //90 in total distance
            moveForward(81);

//            //angle launch ramp to high goal
//            turnright(5.7);
//
//            //launch 3 preloaded rings
//            launchRingHigh(3);
//
//            //turn to face forward
//            turnleft(3);
//
//            //move to box B
//            moveForward(26);

            moveRight(25);

            //Place the wobble goal
            dropWobbleGoal();
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            }

            //drive backwards until parked over line
//            moveBackward(5);
*/
        }

        else if (pipeline.position == SkystoneDeterminationPipeline.RingPosition.NONE) {
            telemetry.addData("Detected", "no rings");
            telemetry.update();

            pickUpWobble();

            //move to launch line       //90 in total distance
            moveForward(56);

            moveLeft(18);

            //launch 3 preloaded rings
            launchRingHigh(3);

            turnleft(15); //90 deg

            moveForward(10);

            //Place the wobble goal
            dropWobbleGoal();
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            }

            raise(50);

            turnleft(29);


            lower(50);
            dropWobbleGoal(); //to bring the arm down

            moveForward(29);
            sleep(1000);

            //pick up wobble without raising first
                robot.wobbleSnatcher.setPosition(0.3);

                runtime.reset();
                while (opModeIsActive() && runtime.seconds() < 0.5) { }
                raise(150);

                runtime.reset();
                while (opModeIsActive() && runtime.seconds() < 0.1) { }
                telemetry.addData("The Wobble Goal", "Has Risen");
                telemetry.update();

            turnright(4);

            moveBackward(45);

            turnright(18); //90 deg

            //Place the wobble goal
            dropWobbleGoal();

//            moveRight(15);


//            //angle launch ramp to high goal
//            turnright(5.7);
//
//            //launch 3 preloaded rings
//            launchRingHigh(3);
//
//            //turn to face forward
//            turnleft(4);


        }

        // Stop, take a well deserved breather
        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }















    /* FUNCTIONS */

    public void launchRingHigh(int times){
        robot.pitcherMotor.setPower(0.65); //0.6257 mid, 0.65 speed when 11.8 V, 0.67 when 11.7 V, 0.69 when 10.22 V
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) { }

        for (int i = 0; i < times; i++){ //launch
            telemetry.addData("Launching High Ring #", i+1);
            telemetry.update();

            //servo pushes ring forward
            robot.ringFlicker.setPosition(0.5);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.5)) { }

            //bring flicker back
            robot.ringFlicker.setPosition(0.25);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.5)) { }
        }
        robot.pitcherMotor.setPower(0); //power off flywheel
    }

    /* ARM MOVEMENT */
    public void raise(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() + (int)(count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(0.3);

        runtime.reset();
        while (opModeIsActive() && robot.elbowMotor.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d", newElbowMotorTarget);
            telemetry.update();
        }

    }

    public void lower(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() - (int) (count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(0.3);

        runtime.reset();
        while (opModeIsActive() && robot.elbowMotor.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d", newElbowMotorTarget);
            telemetry.update();
        }
    }

    public void pickUpWobble() {

        //move into position
        raise(60);
        robot.wobbleSnatcher.setPosition(0.3);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1) { }
        raise(130);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1) { }
        telemetry.addData("The Wobble Goal", "Has Risen");
        telemetry.update();

    }

    public void dropWobbleGoal() {
        lower(70);
        robot.wobbleSnatcher.setPosition(1); // open claw
    }

    /* ENCODER FUNCTIONS */
    public void resetEncoder()
    {
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void startEncoderMode()
    {
        //Set Encoder Mode
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    /* MOVEMENT FUNCTIONS */
    public void moveForward(int inches) {
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
        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void moveBackward(double inches) {
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

        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    public void moveRight(double inches) {
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

        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moveLeft(double inches) {
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

        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void turnleft(double inches) {
        int newmotorFLTarget;
        int newmotorFRTarget;
        int newmotorBLTarget;
        int newmotorBRTarget;

        // Determine new target position, and pass to motor controller
        newmotorFLTarget = robot.motorFL.getCurrentPosition() +  (int)(inches * robot.COUNTS_PER_INCH);      newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);
        newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);       newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);

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
        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnright(double inches) {
        int newmotorFLTarget;
        int newmotorFRTarget;
        int newmotorBLTarget;
        int newmotorBRTarget;

        // Determine new target position, and pass to motor controller
        newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);      newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
        newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)(inches * robot.COUNTS_PER_INCH);      newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)(inches * robot.COUNTS_PER_INCH);
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
        runtime.reset();
        while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget );
            telemetry.update();
        }

        // Stop all motion;
        stopRobot();

        // Turn off RUN_TO_POSITION
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stopRobot() {
        robot.motorFL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
    }

    public void stopRobot(int seconds) {
        //delay
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            stopRobot();
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
    }


    /* VUFORIA RING DETECTION */
    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */
        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(85,183);

        static final int REGION_WIDTH = 50;
        static final int REGION_HEIGHT = 50;

        final int FOUR_RING_THRESHOLD = 145; //143-148
        final int ONE_RING_THRESHOLD = 125; //119-131

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        public volatile RingPosition position = RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = RingPosition.ONE;
            }else{
                position = RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
}
