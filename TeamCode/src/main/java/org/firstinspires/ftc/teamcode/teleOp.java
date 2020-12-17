package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@TeleOp
public class teleOp extends LinearOpMode {
    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = -gamepad1.left_stick_x; //move left, right
            double turn = -gamepad1.right_stick_x; //turn left, right

            telemetry.addData("Motor FL", robot.motorFL);
            telemetry.addLine();
            telemetry.update();

            robot.motorFL.setPower(vertical + horizontal + turn);
            robot.motorFR.setPower(vertical - horizontal - turn);
            robot.motorBL.setPower(vertical - horizontal + turn);
            robot.motorBR.setPower(vertical + horizontal - turn);

            if(gamepad1.dpad_down){
                robot.pitcherMotor.setPower(0);
            }

            if(gamepad1.dpad_left){
                robot.pitcherMotor.setPower(0.6);
            }

            if(gamepad1.dpad_up){
                robot.pitcherMotor.setPower(0.8);
            }

            if(gamepad1.dpad_right){
                robot.pitcherMotor.setPower(0.9);
            }

//            if (gamepad1.dpad_down) {
//
//            }
//            if (gamepad1.dpad_up) {
//
//            }
//            if (gamepad1.dpad_left) {
//
//            }
//            if (gamepad1.dpad_right) {
//
//            }
            if (gamepad1.left_trigger > 0.2) {
                robot.intakeMotor.setPower(1);
            }
//            if (gamepad1.a) {
//
//            }
//            if (gamepad1.x) {
//
//            }
            boolean openToggle = false;
            if (gamepad1.b && openToggle == false) {
                //open the claw
                robot.wobbleSnatcher.setPosition(0.6);

            }
            if (gamepad1.b && openToggle == true) {
                //close the claw
                robot.wobbleSnatcher.setPosition(0.3);

            }


        }


            telemetry.addData("Status", "Running");
            telemetry.addLine();
            telemetry.update();

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
}

