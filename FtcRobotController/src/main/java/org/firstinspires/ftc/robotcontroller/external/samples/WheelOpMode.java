package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Garrick on 2017-11-22.
 */
@TeleOp


public class WheelOpMode extends LinearOpMode {
    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;

    @Override
    public void runOpMode() {
        leftWheelMotor = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheelMotor = hardwareMap.get(DcMotor.class, "rightWheel");

        rightWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // armMotor.setPower(this.gamepad1.left_stick_y);



            leftWheelMotor.setPower(this.gamepad1.left_stick_y);
            rightWheelMotor.setPower(this.gamepad1.right_stick_y);


            telemetry.addData("Status", "Running");
            telemetry.addData("LeftStick", this.gamepad1.left_stick_y);
            telemetry.addData("RightStick", this.gamepad1.right_stick_y);
            telemetry.update();

        }
    }

}
