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
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

@Autonomous(name="Auto w/ Enco", group="Pushbot")
public class EncoderAuto extends LinearOpMode {

    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    OpenCvCamera webcam;
    EasyOpenCVExample.SkystoneDeterminationPipeline pipeline;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);

        pipeline = new EasyOpenCVExample.SkystoneDeterminationPipeline();
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
        telemetry.addLine("Waiting for start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        //Wobble goal put in the correct target zone

        while (opModeIsActive()) {
            //Scan to see how many rings there are
            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.addData("Position", pipeline.position);
            telemetry.update();
        }
/*
        if(cvTest.pipeline.position == "FOUR"){
            telemetry.addData("Detected", "four rings - your if statment");
            telemetry.update();
            //4 = C, farthest
            moveForward(109);
            moveLeft(12);

            //Place the wobble goal
//            dropWobbleGoal();

            //move to the launch line's designates spot
            moveRight(20);
            moveBackward(50); // go back 3 tiles

        } else if (cvTest.pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.ONE){
            telemetry.addData("Detected", "one ring - your if statment");
            telemetry.update();
            //1 = B, middle
            moveForward(90);
            moveRight(12);

            //Place the wobble goal
//            dropWobbleGoal();

            //move to the launch line's designates spot
            moveBackward(25); // go back 1 whole tile

        } else if (cvTest.pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.NONE){
            telemetry.addData("Detected", "no rings - your if statment");
            telemetry.update();

            //0 = A, close
            moveForward(65);
            moveLeft(12);

            //Place the wobble goal
//            dropWobbleGoal();

            //move to the launch line's designates spot
            moveRight(24);

        }

*/
        //TODO move robot to optimal launching place behind the launch line

        //TODO Launching rings into high goal (12 pts each, max 36)

//            for (int i = 0; i < 3; i++){
//                launchRingHigh(4);
//            }


        // TODO make sure we're over the line
        // Stop, take a well deserved breather



//        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }



    //Functions
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
    public void moveForward(int inches)
    {
        telemetry.addData("Func", "in Move Forward");
        telemetry.update();

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


    public void moveBackward(double inches)
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


    public void moveRight(double inches)
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

    public void moveLeft(double inches)
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



    public void launchRingHigh(int seconds){
        //servo pushes ring forward
        robot.ringFlicker.setPosition(0.6); //We need to test this

        //wheel spins until launch, spin speed = distance launched
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            robot.pitcherMotor.setPower(0.9);
        }
        robot.pitcherMotor.setPower(0);
    }

    public void stopRobot()
    {
        robot.motorFL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
    }

    public void stopRobot(int seconds)
    {
        //delay
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            stopRobot();
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
    }

    public void raise(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() + (int) (count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));

    }

    public void lower(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() - (int) (count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));

    }

    public void pickUp() {

        //move into position
        robot.wobbleSnatcher.setPosition(0.3); //close the claw
        raise(10);
        telemetry.addData("The Wobble Goal", "Has Risen");
        telemetry.update();

    }

    // The function for the drop-off of the wobble goal
    public void dropWobbleGoal() {
        lower(5); // lower arm
        robot.wobbleSnatcher.setPosition(0.6); // open claw
        raise(5); // raise arm
    }

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
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(100,130);

        static final int REGION_WIDTH = 90;
        static final int REGION_HEIGHT = 90;

        final int FOUR_RING_THRESHOLD = 150;
        final int ONE_RING_THRESHOLD = 135;

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
        public volatile EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR;

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

            position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.ONE;
            }else{
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
}
