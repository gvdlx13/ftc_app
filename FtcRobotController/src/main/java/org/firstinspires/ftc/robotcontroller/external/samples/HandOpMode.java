package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-11-22.
 */
@TeleOp


public class HandOpMode extends LinearOpMode {
    private Servo hand;

    public void runOpMode(){
        hand = hardwareMap.get(Servo.class, "hand");

        double open = 0.7;
        double close = 0;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(this.gamepad1.left_bumper)
            {
                hand.setPosition(open);
                telemetry.addData("Button", "Pressed");
            } else if(this.gamepad1.right_bumper)
            {
                hand.setPosition(close);
                telemetry.addData("Button", "Pressed");
            }
            telemetry.addData("Status", "Running");
            telemetry.update();

        }


    }

}
