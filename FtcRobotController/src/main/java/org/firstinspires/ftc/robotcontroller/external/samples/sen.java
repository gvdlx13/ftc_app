package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;



@Autonomous(name="sen", group="Linear Opmode")
public class sen extends LinearOpMode {
    private ColorSensor color_sensor1;
    //private ColorSensor color_sensor2;
    private Servo hand;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor armMotor;
    int beforeRed;
    int beforeBlue;

    int afterRed;
    int afterBlue;

    @Override
    public void runOpMode() throws InterruptedException {
        color_sensor1 = hardwareMap.get(ColorSensor.class, "color2");
        //color_sensor2 = hardwareMap.get(LynxI2cColorRangeSensor.class, "color1");
        //motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //touch = hardwareMap.get(DigitalChannel.class, "touch2");
        //touch.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorLeft = hardwareMap.get(DcMotor.class, "leftWheel");
        motorRight = hardwareMap.get(DcMotor.class, "rightWheel");
        hand = hardwareMap.get(Servo.class, "hand");
        boolean myTeamRed = false;
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);

        color_sensor1.blue();
        color_sensor1.red();

        beforeBlue = color_sensor1.blue();
        beforeRed = color_sensor1.red();

        int x = 0;
        //color_sensor2.blue();
        waitForStart();


        while (true) {
            if (this.gamepad1.a) {
                break;
            } else if (this.gamepad1.b) {
                myTeamRed = true;
                break;
            }
        }
        armMotor.setTargetPosition(19100);
        armMotor.setPower(1);
        sleep(7500);
        armMotor.setPower(0);
        sleep(100);


        motorRight.setPower(0.1);
        motorLeft.setPower(0.1);
        sleep(1700);
        motorRight.setPower(0);
        motorLeft.setPower(0);
        sleep(1000);

        afterBlue = color_sensor1.blue();
        afterRed = color_sensor1.red();

        /*armMotor.setTargetPosition(19300);
        armMotor.setPower(1);
        sleep(100);*/

        while (true) {
            if (myTeamRed == true && afterRed > beforeRed) {
                motorRight.setPower(0.2);
                motorLeft.setPower(-0.2);
                sleep(300);

                motorLeft.setPower(0);
                motorRight.setPower(0);
                sleep(100);

                motorRight.setPower(-0.2);
                motorLeft.setPower(0.2);
                sleep(300);
                break;
            } else if (afterBlue > beforeBlue) {
                motorLeft.setPower(0.2);
                motorRight.setPower(-0.2);
                sleep(300);

                motorLeft.setPower(0);
                motorRight.setPower(0);
                sleep(100);

                motorLeft.setPower(-0.2);
                motorRight.setPower(0.2);
                sleep(300);
                break;
            }
             else {
                hand.setPosition(hand.getPosition()+0.1);
            }
            if (myTeamRed == false && afterRed > beforeRed) {
                motorLeft.setPower(0.2);
                motorRight.setPower(-0.2);

                sleep(300);

                motorLeft.setPower(0);
                motorRight.setPower(0);
                sleep(100);

                motorLeft.setPower(-0.2);
                motorRight.setPower(0.2);

                sleep(300);
                break;
            } else if (afterBlue> beforeBlue) {
                motorRight.setPower(0.2);
                motorLeft.setPower(-0.2);
                sleep(300);

                motorLeft.setPower(0);
                motorRight.setPower(0);
                sleep(100);

                motorRight.setPower(-0.2);
                motorLeft.setPower(0.2);
                sleep(300);
                break;
            }
           else {
                hand.setPosition(hand.getPosition()+0.1);
            }
        }
        motorRight.setPower(-0.3);
        motorLeft.setPower(-0.3);
        sleep(600);

        motorLeft.setPower(0);
        motorRight.setPower(0);

        sleep(100);

                /*armMotor.setTargetPosition(15000);
        armMotor.setPower(1);
        sleep(100);*/


        telemetry.update();
    }
}
    /*private void seekTopArmPosition(){
        armMotor.setTargetPosition(6750);
        armMotor.setPower(1);
        armTopPosition = 2;
        seekWristVertical();
    }

    private void seekLowerArmPosition(){
        armMotor.setTargetPosition(19300);
        armMotor.setPower(1);
        armTopPosition = 0;
        seekWristHorizontal();*/






