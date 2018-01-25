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



@Autonomous(name="colorSensorTest", group="Linear Opmode")
public class colorSensorTest extends LinearOpMode {
    private ColorSensor color_sensor1;
    //private ColorSensor color_sensor2;
    private Servo hand;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor armMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        boolean pushFoundBall = true;
        boolean myTeamRed = false;
        int beforeRed, beforeBlue;
        int afterRed, afterBlue;
        int dBlue, dRed;

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
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);

        //int x = 0;
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


        beforeBlue = color_sensor1.blue();
        beforeRed = color_sensor1.red();

        /*armMotor.setTargetPosition(19300);
        armMotor.setPower(1);
        sleep(100);*/

        for(int i=0; i < 2 ;  i++) {
            afterBlue = color_sensor1.blue();
            afterRed = color_sensor1.red();

            dBlue = Math.abs(afterBlue - beforeBlue);
            dRed = Math.abs(afterRed - beforeRed);

            telemetry.addData("beforeBlue", beforeBlue);
            telemetry.addData("beforeRed", beforeRed);
            telemetry.addData("dBlue", dBlue);
            telemetry.addData("dRed", dRed);
            telemetry.update();

            if (Math.abs(dRed - dBlue) > 25) {
                if (dBlue > dRed) {
                    if ( myTeamRed == true )
                        pushFoundBall = true; // The found ball is blue, push it!
                    else
                        pushFoundBall = false; // The found ball is red, push the other one!

                    break;
                } else {
                    if ( myTeamRed == false )
                        pushFoundBall = true; // The found ball is red, push it!
                    else
                        pushFoundBall = false; // The found ball is blue, push the other one!

                    break;
                }
            }
            hand.setPosition(hand.getPosition()+0.1);
            sleep(300);
        }

        if( pushFoundBall == true ) {
            motorLeft.setPower(0.3);
            motorRight.setPower(-0.3);
        } else {
            motorLeft.setPower(-0.3);
            motorRight.setPower(0.3);
        }

        sleep(600);
        motorLeft.setPower(0);
        motorRight.setPower(0);
        sleep(100);


        if( pushFoundBall == true ) {
            motorLeft.setPower(-1);
            motorRight.setPower(-0.6);
        } else {
            motorLeft.setPower(-0.6);
            motorRight.setPower(-1);
        }

        sleep(600);
        motorRight.setPower(0);
        motorLeft.setPower(0);
        sleep(1000);

        /*armMotor.setTargetPosition(15000);
        armMotor.setPower(1);
        sleep(100);*/

        telemetry.update();
    }
}