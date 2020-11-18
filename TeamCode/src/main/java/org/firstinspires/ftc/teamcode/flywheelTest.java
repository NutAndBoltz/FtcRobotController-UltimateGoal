package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class flywheelTest extends LinearOpMode {

    //robotInit
    public DcMotor pitcherMotor; //flywheel motor
    public void runOpMode() {
        pitcherMotor = hardwareMap.get(DcMotor.class, "flywheel");
        pitcherMotor.setDirection(DcMotor.Direction.FORWARD);

        if(gamepad1.dpad_down){
            pitcherMotor.setPower(0);
        }

        if(gamepad1.dpad_left){
            pitcherMotor.setPower(0.05);
        }

        if(gamepad1.dpad_up){
            pitcherMotor.setPower(0.25);
        }

        if(gamepad1.dpad_right){
            pitcherMotor.setPower(0.5);
        }
    }
}

