package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class teleOp extends LinearOpMode {
    public robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        boolean openToggle = false;

        robot.elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            //TODO decrease speed in teleop so movements are not as jerky

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = -gamepad1.left_stick_x; //move left, right
            double turn = -gamepad1.right_stick_x; //turn left, right

            //driving
            robot.motorFL.setPower(vertical + horizontal + turn);
            robot.motorFR.setPower(vertical - horizontal - turn);
            robot.motorBL.setPower(vertical - horizontal + turn);
            robot.motorBR.setPower(vertical + horizontal - turn);

            //flywheel
            if(gamepad1.dpad_down){ //turn off flywheel
                robot.pitcherMotor.setPower(0);
            }

            if(gamepad1.dpad_left){ // mid goal
                launchRing("midGoal");
                telemetry.addData("Launching Ring", "Mid");
                telemetry.update();
            }

            if(gamepad1.dpad_up){ //high goal
                launchRing("highGoal");
                telemetry.addData("Launching Ring", "High");
                telemetry.update();
            }

            if(gamepad1.dpad_right){
                launchRing("powerShot");
                telemetry.addData("Launching Ring", "Power");
                telemetry.update();
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

            if(gamepad1.y){ //snatch wobble and raise arm
                raise(75);
            }
            if(gamepad1.a){ //lower arm and release wobble
                lower(75);
            }


            if(gamepad1.x){ //release wobble servo
                robot.wobbleSnatcher.setPosition(1);
            }
            if(gamepad1.b) { //clamp wobble servo
                robot.wobbleSnatcher.setPosition(0.5);
            }

            if(gamepad1.back){
                //servo pushes ring forward
                robot.ringFlicker.setPosition(0.5);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.5)) {
                }

                //bring flicker back
                robot.ringFlicker.setPosition(0.25);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.5)) {
                }
            }


            //up arm
            if(gamepad1.left_bumper){
                robot.intakeMotor.setPower(0);
            }

            //down arm
            if(gamepad1.right_bumper){
                robot.intakeMotor.setPower(0.5);
            }

        }
            telemetry.addData("Status", "Running");
            telemetry.addLine();
            telemetry.update();
    }
    String previousTarget = "";
    public void launchRing(String target){

        //TODO no matter what target we choose, the motor's velocity sequentially gets set to powerShot --> midGoal --> highGoal
        if(target.equals("powerShot")) {
            robot.pitcherMotor.setPower(0.65);
        }else if(target.equals("midGoal")){
            robot.pitcherMotor.setPower(0.62);
        }else if(target.equals("highGoal")){
            robot.pitcherMotor.setPower(0.68);
        }
        if (robot.pitcherMotor.getPower() == 0 || previousTarget != target){
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 2)) {
                telemetry.addData("Please wait", "Getting up to speed");
                telemetry.addLine();
                telemetry.update();
            }
            previousTarget = target;
        }


    }

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

//        robot.elbowMotor.setPower(0);
//
//        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

//        robot.elbowMotor.setPower(0);
//
//        robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}

