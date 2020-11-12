package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class armTeleOp extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        armGeneralMovements general = new armGeneralMovements();
        general.robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            double lower = gamepad1.left_trigger;
            double raise = gamepad1.right_trigger;
            lower = Range.clip(lower, 0, 0.5);
            raise = Range.clip(raise, 0, 0.5);

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

//            general.robot.elbowMotor.setPower(raise - lower);

            if(gamepad1.right_bumper){
                general.raise(3);
            }

            if(gamepad1.left_bumper) {
                general.lower(3);
            }

            // 11/5/20 not the problem, but logic still needs to be checked when we get rid of null obj ref error
            if (gamepad1.b) {
                //close the claw
                general.robot.wobbleSnatcher.setPosition(0.3);
            }
            if (gamepad1.y) {
                //open the claw
                general.robot.wobbleSnatcher.setPosition(1);
            }
        }
    }

}