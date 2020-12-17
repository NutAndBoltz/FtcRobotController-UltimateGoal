package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
public class robotInit {

    /* Public OpMode members. */
    public DcMotor motorFL;
    public DcMotor motorFR;
    public DcMotor motorBL;
    public DcMotor motorBR;
    public DcMotor  pitcherMotor; //rotating wheel to launch rings
    public DcMotor elbowMotor; //wobble goal arm
    public DcMotor intakeMotor;

    public Servo wobbleSnatcher; //wobble goal servo claw
    public Servo ringFlicker; // launch ring pusher




    public ModernRoboticsI2cGyro   gyro    = null;

    int AUTO = 1;
    int TELEOP = 0;

    double     MID_SERVO  =  0.5 ;


    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 537.6 ;
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.5;
    double     teleOP_FORWARD_SPEED    = 1;

    /* local OpMode members. */
    HardwareMap hardwareMap           =  null;
    ElapsedTime runtime  = new ElapsedTime();

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
        pitcherMotor = hardwareMap.get(DcMotor.class, "pitcherMotor");
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        motorFL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        pitcherMotor.setDirection(DcMotor.Direction.FORWARD);
        elbowMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);

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
        pitcherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elbowMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
        wobbleSnatcher = hardwareMap.get(Servo.class, "wobbleSnatcher");
        ringFlicker = hardwareMap.get(Servo.class, "ringFlicker");


        //init servos
        wobbleSnatcher.setPosition(0.3);

    }
}
