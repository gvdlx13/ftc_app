package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-11-24.
 */

public class fullRobotOpMode extends LinearOpMode {
    private DcMotor armMotor;
    private Servo hand;
    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;
    private CRServo wrist;
    private DigitalChannel touch1;

    @Override
    public void runOpMode() {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        hand = hardwareMap.get(Servo.class, "hand");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        leftWheelMotor = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheelMotor = hardwareMap.get(DcMotor.class, "rightWheel");
        touch1 = hardwareMap.get(DigitalChannel.class, "touch2");

        rightWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        hand.scaleRange(0.2, 0.6);
        double handOpen = 0;
        double handSlightlyOpen = 0.3;
        double handClose = 1;
        double defaultPosition = .42;
        double horizontal = 0.65;
        double vertical = -0.35;

        telemetry.addData("Status", touch1.getState());
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(this.gamepad1.x)
            {
                hand.setPosition(handOpen);
            } else if(this.gamepad1.b)
            {
                hand.setPosition(handClose);
            } else if (this.gamepad1.y)
            {
                hand.setPosition(defaultPosition);
            } else if (this.gamepad1.a)
            {
                hand.setPosition(handSlightlyOpen);
            }

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


            if(this.gamepad1.left_bumper)
            {
                wrist.setPower(horizontal);
                telemetry.addData("Button", "Pressed");
            }
            else if (this.gamepad1.right_bumper)
            {
                wrist.setPower(vertical);
                telemetry.addData("Button", "Pressed");
            }

            leftWheelMotor.setPower(this.gamepad1.left_stick_y);
            rightWheelMotor.setPower(this.gamepad1.right_stick_y);


            telemetry.addData("LeftStick", this.gamepad1.left_stick_y);
            telemetry.addData("RightStick", this.gamepad1.right_stick_y);
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
