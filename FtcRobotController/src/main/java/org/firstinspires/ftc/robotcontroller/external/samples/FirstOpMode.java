package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-11-09.
 */
@TeleOp

public class FirstOpMode extends LinearOpMode {
    private DcMotor armMotor;
    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;
    private Servo leftClawServo;
    private Servo rightClawServo;


    @Override
    public void runOpMode() {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        leftWheelMotor = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheelMotor = hardwareMap.get(DcMotor.class, "rightWheel");
        rightClawServo = hardwareMap.get(Servo.class, "rightClaw");
        leftClawServo = hardwareMap.get(Servo.class, "leftClaw");
        leftClawServo.setDirection(Servo.Direction.REVERSE);
        rightClawServo.setDirection(Servo.Direction.FORWARD);

        rightWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double closed = 0.4;
        double open = 0;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(this.gamepad1.left_bumper)
            {
                leftClawServo.setPosition(closed);
                rightClawServo.setPosition(closed);
            }
            else if (this.gamepad1.right_bumper)
            {
                leftClawServo.setPosition(open);
                rightClawServo.setPosition(open);
            }

            if(this.gamepad1.dpad_down)
            {
                leftClawServo.setPosition(closed);
                rightClawServo.setPosition(closed);
            }
            else if (this.gamepad1.right_bumper)
            {
                leftClawServo.setPosition(open);
                rightClawServo.setPosition(open);
            }

            // armMotor.setPower(this.gamepad1.left_stick_y);

            leftWheelMotor.setPower(this.gamepad1.left_stick_y);
            rightWheelMotor.setPower(this.gamepad1.right_stick_y);


            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
