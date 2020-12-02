package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp
public class armTest extends LinearOpMode{

    //robot init
    public Servo wobbleSnatcher; //wobble goal servo claw
    public DcMotor elbowMotor; //wobble goal arm

    //from Encoder Sample
    double     COUNTS_PER_MOTOR_GB223   = 753.2 ;
    double     DRIVE_SPEED             = 0.5;

    public void runOpMode() {
        // Define and initialize ALL installed servos.
        wobbleSnatcher = hardwareMap.get(Servo.class, "wobbleSnatcher");
        wobbleSnatcher.setPosition(1); //open the latch
        boolean openToggle = true;

        //Define and initialize ALL installed motors
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        elbowMotor.setDirection(DcMotor.Direction.FORWARD);


        //teleop
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.b && !openToggle) {
                //open the claw
                openToggle = true;
                wobbleSnatcher.setPosition(0.6);
                telemetry.addData("Claw Status", "Open");
                telemetry.update();
            }
            if (gamepad1.b && openToggle) {
                //close the claw
                openToggle = false;
                wobbleSnatcher.setPosition(1);
                telemetry.addData("Claw Status", "Closed");
                telemetry.update();
            }



            if (gamepad1.right_bumper) {
                raise(5);
            }

            if (gamepad1.left_bumper) {
                lower(5);
            }

            //pick up function
            if (gamepad1.y) {
                pickUp();
            }

            //drop off function
            if (gamepad1.a) {
                dropWobbleGoal();
            }

        }
    }



    //General Movements
    public void raise(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = elbowMotor.getCurrentPosition() + (int) (count);
        elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elbowMotor.setPower(Math.abs(DRIVE_SPEED));

    }

    public void lower(double count) {

        int newElbowMotorTarget;

        // Determine new target position, and pass to motor controller
        newElbowMotorTarget = elbowMotor.getCurrentPosition() - (int) (count);
        elbowMotor.setTargetPosition(newElbowMotorTarget);

        // Turn On RUN_TO_POSITION
        elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elbowMotor.setPower(Math.abs(DRIVE_SPEED));

    }

    public void pickUp() {

        //move into position
        wobbleSnatcher.setPosition(0.3); //close the claw
        raise(10);
        telemetry.addData("The Wobble Goal", "Has Risen");
        telemetry.update();

    }

    // The function for the drop-off of the wobble goal
    public void dropWobbleGoal() {
        lower(5); // lower arm
        wobbleSnatcher.setPosition(0.6); // open claw
        raise(5); // raise arm
    }

}

