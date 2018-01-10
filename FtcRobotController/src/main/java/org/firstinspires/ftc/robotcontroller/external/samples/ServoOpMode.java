package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-12-29.
 */

public class ServoOpMode extends LinearOpMode {
    private Servo servo;
    private DigitalChannel touchSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "hand");
        touchSensor = hardwareMap.get(DigitalChannel.class, "touch2");

        double defaultPosition = 0;

        servo.setPosition(defaultPosition);

        waitForStart();

        while(opModeIsActive()){
            if(this.gamepad1.a){
                servo.setPosition(0.5);
            }


            telemetry.addData("Touch Sensor Status", touchSensor.getState());

        }


    }
}
