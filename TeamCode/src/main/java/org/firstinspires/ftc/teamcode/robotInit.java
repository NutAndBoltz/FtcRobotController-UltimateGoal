package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class robotInit {
    /* Public OpMode members. */
    public DcMotor motorFL;
    public DcMotor motorFR;
    public DcMotor motorBL;
    public DcMotor motorBR;
    //    public DcMotor motorArm1;
//    public DcMotor motorArm2;
    public Servo foundationClaw;
    public Servo servoOrange;
    public Servo servo2;
//    public Servo servoArm;

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

        //gyroSensor define and initialization

        //gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");

        // Define and Initialize Motors

        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        motorFR.setDirection(DcMotor.Direction.FORWARD);
        motorBR.setDirection(DcMotor.Direction.FORWARD);


//        motorArm1 = hardwareMap.get(DcMotor.class, "motorArm1");
//        motorArm1.setDirection(DcMotor.Direction.FORWARD);
//
//        motorArm2 = hardwareMap.get(DcMotor.class, "motorArm2");
//        motorArm2.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        motorBL.setPower(0);
        motorBR.setPower(0);
        motorFR.setPower(0);
        motorFL.setPower(0);
//        motorArm1.setPower(0);
//        motorArm2.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motorArm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motorArm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
        foundationClaw = hardwareMap.get(Servo.class, "foundationClaw");
        servoOrange = hardwareMap.get(Servo.class, "servoOrange");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        //init servoArm so it doesn't drag on ground
        servo2.setPosition(0.8); //0.3
        servoOrange.setPosition(0.8);
    }
}
