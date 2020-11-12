package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class teleOp extends LinearOpMode {
    generalMovements general = new generalMovements();

    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = gamepad1.left_stick_x; //move left, right
            double turn = gamepad1.right_stick_x; //turn left, right

            general.robot.motorFL.setPower(vertical + horizontal + turn);
            general.robot.motorFR.setPower(vertical - horizontal - turn);
            general.robot.motorBL.setPower(vertical - horizontal + turn);
            general.robot.motorBR.setPower(vertical + horizontal - turn);

            if (gamepad1.dpad_down) {

            }
            if (gamepad1.dpad_up) {

            }
            if (gamepad1.dpad_left) {

            }
            if (gamepad1.dpad_right) {

            }
            if (gamepad1.y) {

            }
            if (gamepad1.a) {

            }
            if (gamepad1.x) {

            }
            boolean openToggle = false;
            if (gamepad1.b && openToggle == false) {
                //open the claw
                general.robot.wobbleSnatcher.setPosition(0.6);

            }
            if (gamepad1.b && openToggle == true) {
                //close the claw
                general.robot.wobbleSnatcher.setPosition(0.3);

            }


            }



            telemetry.addData("Status", "Running");
            telemetry.addLine();
            telemetry.update();

        }
    }

