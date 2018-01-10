package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

/**
 * Created by Garrick on 2017-11-22.
 */
@TeleOp


public class TwistWristOpMode extends LinearOpMode {
    private CRServo wrist;

    public void runOpMode(){
        wrist = hardwareMap.get(CRServo.class, "wrist");

        double vertical = 0;
        double horizontal = 1;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(this.gamepad1.left_bumper)
            {
                wrist.setPower(0.5);
                telemetry.addData("Button", "Pressed");
            }
            else if (this.gamepad1.right_bumper)
            {

                wrist.setPower(-0.5);
                telemetry.addData("Button", "Pressed");
            }

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }

}
