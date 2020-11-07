package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Disabled
@Autonomous(name="Autonomous w/ Encoders", group="Pushbot")
public class EncoderAuto extends LinearOpMode {

    generalMovements general = new generalMovements();
    EasyOpenCVExample cvTest = new EasyOpenCVExample();

    @Override
    public void runOpMode() {


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Wobble goal put in the correct target zone

        //Scan to see how many rings there are
        telemetry.addData("Analysis", cvTest.pipeline.getAnalysis());
        telemetry.addData("Position", cvTest.pipeline.position);
        telemetry.update();

        if(cvTest.pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR){
            telemetry.addData("Detected", "four rings - your if statment");
            telemetry.update();
            //4 = C, farthest
            general.moveForward(109);
            general.moveLeft(12);

            //Place the wobble goal


            //move to the launch line's designates spot
            general.moveRight(20);
            general.moveBackward(50); // go back 3 tiles

        } else if (cvTest.pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.ONE){
            telemetry.addData("Detected", "one ring - your if statment");
            telemetry.update();
            //1 = B, middle
            general.moveForward(90);
            general.moveRight(12);

            //Place the wobble goal

            //move to the launch line's designates spot
            general.moveBackward(25); // go back 1 whole tile

        } else if (cvTest.pipeline.position == EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.NONE){
            telemetry.addData("Detected", "no rings - your if statment");
            telemetry.update();

            //0 = A, close
            general.moveForward(65);
            general.moveLeft(12);

            //Place the wobble goal

            //move to the launch line's designates spot
            general.moveRight(24);

        }



        //Launching rings into high goal (12 pts each, max 36)

            //Launch ring 1
                //servo pushes ring forward
                //wheel spins until launch, spin speed = distance launched

            //Launch ring 2
                //servo pushes ring forward
                //wheel spins until launch, spin speed = distance launched

            //Launch ring 3
                //servo pushes ring forward
                //wheel spins until launch, spin speed = distance launched

            // make sure we're over the line
            // Stop, take a well deserved breather



        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


}
