package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class armTeleOp extends LinearOpMode {
    armGeneralMovements general = new armGeneralMovements();

    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = gamepad1.left_stick_x; //move left, right
            double turn = gamepad1.right_stick_x; //turn left, right
            boolean openToggle = false;
//            if (gamepad1.b && openToggle == false) {
//                //open the claw
//                general.robot.wobbleSnatcher.setPosition(0.6);
//
//            }
//            if (gamepad1.b && openToggle == true) {
//                //close the claw
//                general.robot.wobbleSnatcher.setPosition(0.3);
//
//            }

            // 11/5/20 not the problem, but logic still needs to be checked when we get rid of null obj ref error
            if (gamepad1.b) {
                //close the claw
                general.robot.wobbleSnatcher.setPosition(0.6);
            }
            if (gamepad1.y) {
                //open the claw
                general.robot.wobbleSnatcher.setPosition(0.3);
            }
        }
    }

}