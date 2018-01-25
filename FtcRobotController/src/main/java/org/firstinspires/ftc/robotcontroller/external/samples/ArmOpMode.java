package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-11-09.
 */
@TeleOp

public class ArmOpMode extends LinearOpMode {
    private DcMotor armMotor;
    private Servo wrist;


    @Override
    public void runOpMode() {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        wrist = hardwareMap.servo.get("wrist");

        double closed = 0.4;
        double open = 0;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (this.gamepad1.dpad_down)
            {
                armMotor.setPower(-0.3);
            }
            else if (this.gamepad1.dpad_up)
            {
                armMotor.setPower(0.3);
            }
            else {
                armMotor.setPower(0);
            }

//            if (this.gamepad1.a)
//            {
//                wrist.close();
//            }
//
//            if (this.gamepad1.b)
//            {
//                wrist.setPosition(0.5);
//            }
//
//            if (this.gamepad1.x)
//            {
//                wrist.setPosition(0.971);
//            }

            telemetry.addData("wrist", wrist.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
