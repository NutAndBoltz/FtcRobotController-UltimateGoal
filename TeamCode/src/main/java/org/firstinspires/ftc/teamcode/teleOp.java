package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@TeleOp
public class teleOp extends LinearOpMode {
    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        boolean openToggle = false;

        waitForStart();

        while (opModeIsActive()) {

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = -gamepad1.left_stick_x; //move left, right
            double turn = -gamepad1.right_stick_x; //turn left, right

            //driving
            robot.motorFL.setPower(vertical + horizontal + turn);
            robot.motorFR.setPower(vertical - horizontal - turn);
            robot.motorBL.setPower(vertical - horizontal + turn);
            robot.motorBR.setPower(vertical + horizontal - turn);

            //flywheel
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

//            if (gamepad1.left_trigger > 0.2) {
//                robot.intakeMotor.setPower(1);
//            }

            //wobble servo open/close
//            if (gamepad1.b && openToggle == false) {
//                //open the claw
//                robot.wobbleSnatcher.setPosition(0.6);
//
//            }
//            if (gamepad1.b && openToggle == true) {
//                //close the claw
//                robot.wobbleSnatcher.setPosition(0.3);
//
//            }

            if(gamepad1.a){
                robot.wobbleSnatcher.setPosition(1);
            }
            if(gamepad1.y){
                robot.wobbleSnatcher.setPosition(0);
            }

            if(gamepad1.x){
                robot.ringFlicker.setPosition(0.5);
            }
            if(gamepad1.b) {
                robot.ringFlicker.setPosition(0.25);
            }


            //up arm
            if(gamepad1.left_bumper){
                lower(30);
            }

            //down arm
            if(gamepad1.right_bumper){
                raise(10);
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

    public void raise(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() + (int) (count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(0.3);

        while (opModeIsActive() && robot.elbowMotor.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d", newElbowMotorTarget);
            telemetry.update();
        }

//         Stop all motion;
//        stopRobot();

//         Turn off RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void lower(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() - (int) (count);
        robot.elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.elbowMotor.setPower(0.3);

        while (opModeIsActive() && robot.elbowMotor.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d", newElbowMotorTarget);
            telemetry.update();
        }

//        // Stop all motion;
//        stopRobot();
//
//        // Turn off RUN_TO_POSITION
//        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

