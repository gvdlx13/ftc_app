package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-12-29.
 */

public class EncoderOpMode extends LinearOpMode {
    private DcMotor armMotor;
    private DigitalChannel touch1;

    public void runOpMode() {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        touch1 = hardwareMap.get(DigitalChannel.class, "touch2");
        armMotor.setPower(.1);
        while (touch1.getState())
        {
            telemetry.addData("Status", touch1.getState());
            telemetry.update();
        }
        armMotor.setPower(0);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double handOpen = 0.7;
        double handClose = 0;
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
                armMotor.setTargetPosition(-40);
                armMotor.setPower(.05);
            } else if (this.gamepad1.dpad_up){
                armMotor.setTargetPosition(0);
                armMotor.setPower(.05);
            }

            telemetry.addData("motorPosition", armMotor.getCurrentPosition());
            telemetry.addData("LeftStick", this.gamepad1.left_stick_y);
            telemetry.addData("RightStick", this.gamepad1.right_stick_y);
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
