package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp
public class driveTest extends LinearOpMode {

    //robotInit
    public DcMotor motorFL;
    public DcMotor motorFR;
    public DcMotor motorBL;
    public DcMotor motorBR;
    public DcMotor pitcherMotor; //flywheel motor

    public Servo wobbleSnatcher;

    public void runOpMode() {

        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        pitcherMotor = hardwareMap.get(DcMotor.class, "pitcherMotor");

        motorFL.setDirection(DcMotor.Direction.FORWARD); //before r
        motorFR.setDirection(DcMotor.Direction.REVERSE); //before f
        motorBR.setDirection(DcMotor.Direction.FORWARD);
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        pitcherMotor.setDirection(DcMotor.Direction.FORWARD);

        wobbleSnatcher = hardwareMap.get(Servo.class, "wobbleSnatcher");
        wobbleSnatcher.setPosition(0.3);

        //teleop
        waitForStart();

        while (opModeIsActive()) {

            double vertical = gamepad1.left_stick_y; //move forward, backward
            double horizontal = -gamepad1.left_stick_x; //move left, right
            double turn = -gamepad1.right_stick_x; //turn left, right

            //TODO make the three class system work (robotInit, generalMovements, teleOp/EncouderAuto

            motorFL.setPower(vertical + horizontal + turn);
            motorFR.setPower(vertical - horizontal - turn);
            motorBL.setPower(vertical - horizontal + turn);
            motorBR.setPower(vertical + horizontal - turn);

            if(gamepad1.dpad_down){
                pitcherMotor.setPower(0);
            }

            if(gamepad1.dpad_left){
                pitcherMotor.setPower(0.6);
            }

            if(gamepad1.dpad_up){
                pitcherMotor.setPower(0.8);
            }

            if(gamepad1.dpad_right){
                pitcherMotor.setPower(0.9);
            }

            boolean openToggle = false;
            if (gamepad1.b && openToggle == false) {
                //open the claw
                wobbleSnatcher.setPosition(0.6);

            }
            if (gamepad1.b && openToggle == true) {
                //close the claw
                wobbleSnatcher.setPosition(0.3);

            }

        }

    }
}


