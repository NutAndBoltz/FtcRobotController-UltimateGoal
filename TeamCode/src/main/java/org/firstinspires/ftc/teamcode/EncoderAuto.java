/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")
@Disabled
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
