package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
public class robotInit {

    /* Public OpMode members. */
    public DcMotor motorFL;
    public DcMotor motorFR;
    public DcMotor motorBL;
    public DcMotor motorBR;
    public DcMotorEx pitcherMotor; //rotating wheel to launch rings
    public DcMotor elbowMotor; //wobble goal arm
    public DcMotor intakeMotor;

    public Servo wobbleSnatcher; //wobble goal servo claw
    public Servo ringFlicker; // launch ring pusher


    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 537.6 ;
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.95;
    double     teleOP_FORWARD_SPEED    = 1;

    /* local OpMode members. */
    HardwareMap hardwareMap           =  null;

    /* Constructor */
    public robotInit(){

    }


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hardwareMap = ahwMap;


        // Define and Initialize Motors
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        pitcherMotor = hardwareMap.get(DcMotorEx.class, "pitcherMotor");
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        motorFL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        pitcherMotor.setDirection(DcMotor.Direction.REVERSE);
        elbowMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        motorBL.setPower(0);
        motorBR.setPower(0);
        motorFR.setPower(0);
        motorFL.setPower(0);
        pitcherMotor.setPower(0);
        elbowMotor.setPower(0);
        intakeMotor.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pitcherMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbowMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
        wobbleSnatcher = hardwareMap.get(Servo.class, "wobbleSnatcher");
        ringFlicker = hardwareMap.get(Servo.class, "ringFlicker");


        //init servos
        wobbleSnatcher.setPosition(1);

        //PIDF calculations and settings
        pitcherMotor.setVelocityPIDFCoefficients(1.343, 0.1343, 0.1, 13.43);
        //max velocity 2440
        // F: 13.43
        // P: 1.343
        // I: 0.1343
        // D: 0

    }
}
