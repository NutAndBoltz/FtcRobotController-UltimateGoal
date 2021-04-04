package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class testPID extends LinearOpMode {
    DcMotorEx motor;
    Servo ringFlicker; // launch ring pusher
    double currentVelocity;
    double maxVelocity = 0.0;

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {


        motor = hardwareMap.get(DcMotorEx.class, "pitcherMotor");
        ringFlicker = hardwareMap.get(Servo.class, "ringFlicker");

        motor.setDirection(DcMotor.Direction.REVERSE);

        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setVelocityPIDFCoefficients(1.343, 0.1343, 0.1, 13.43);



        waitForStart();

//        motor.setVelocity(1659);

        while (opModeIsActive()) {
            launchRingHigh(3);
//            currentVelocity = motor.getVelocity();
//
//            if (currentVelocity > maxVelocity) {
//                maxVelocity = currentVelocity;
//            }
//
//            telemetry.addData("Velocity", motor.getVelocity());
//            telemetry.addData("maximum velocity", maxVelocity);
//            telemetry.update();
//
//            launchRingHigh(3);
        }
    }

    public void launchRingHigh(int times) {
        motor.setVelocity(1659); //0.6257 mid, 0.65 speed when 11.8 V, 0.67 when 11.7 V, 0.69 when 10.22 V
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
        }

        for (int i = 0; i < times; i++) { //launch
            telemetry.addData("Launching High Ring #", i + 1);
            telemetry.addData("Velocity", motor.getVelocity());
            telemetry.update();

            //servo pushes ring forward
            ringFlicker.setPosition(0.5);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            }

            //bring flicker back
            ringFlicker.setPosition(0.25);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            }
        }
        motor.setPower(0); //power off flywheel
    }

}

//max velocity 2440
// F: 13.43
// P: 1.343
// I: 0.1343
// D: 0