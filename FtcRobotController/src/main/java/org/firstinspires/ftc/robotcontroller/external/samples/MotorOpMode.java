package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-12-29.
 */
@TeleOp
public class MotorOpMode extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor arm;
    private Servo finger;
    private CRServo wrist;
    private Servo hand;
    private LynxI2cColorRangeSensor color;
    private DigitalChannel touchSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        touchSensor = hardwareMap.get(DigitalChannel.class, "touchSensor");
        color =hardwareMap.get(LynxI2cColorRangeSensor.class, "color");
        waitForStart();

        while(opModeIsActive()){
            leftWheel.setPower(this.gamepad1.left_stick_y);
            touchSensor.getState();

            color.green();


        }
    }
}
