package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class armRobotInit {
    public Servo wobbleSnatcher; //wobble goal servo claw
    public DcMotor elbowMotor; //wobble goal arm

    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 537.6 ;
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.5;
    double     teleOP_FORWARD_SPEED    = 1;


    /* local OpMode members. */
    HardwareMap hardwareMap           =  null;

    /* Constructor */
    public armRobotInit(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hardwareMap = ahwMap;

        // Define and initialize ALL installed servos.
        wobbleSnatcher = hardwareMap.get(Servo.class, "wobbleSnatcher");
        wobbleSnatcher.setPosition(1); //open the latch


        //Define and initialize ALL installed motors
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        elbowMotor.setDirection(DcMotor.Direction.FORWARD);


    }
}
