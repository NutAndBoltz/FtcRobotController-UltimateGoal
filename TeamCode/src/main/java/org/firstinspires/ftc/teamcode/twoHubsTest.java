package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled

@TeleOp
public class twoHubsTest extends LinearOpMode {
    //robotInit
    public DcMotor controlMotor; //control hub motor
    public Servo exServo; //expansion hub servo



    public void runOpMode() {

        controlMotor = hardwareMap.get(DcMotor.class, "cMotor");
        controlMotor.setDirection(DcMotor.Direction.FORWARD);

        exServo = hardwareMap.get(Servo.class, "exServo");
        exServo.setPosition(0.3);


        //teleop
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_down) {
                controlMotor.setPower(0);
            }

            if (gamepad1.dpad_left) {
                controlMotor.setPower(0.6);
            }

            if (gamepad1.dpad_up) {
                controlMotor.setPower(0.8);
            }

            if (gamepad1.dpad_right) {
                controlMotor.setPower(0.9);
            }
            if (gamepad1.y) {
                exServo.setPosition(0.9);
            }
            if (gamepad1.a) {
                exServo.setPosition(0.2);
            }

        }

    }

}

