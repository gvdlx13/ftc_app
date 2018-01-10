package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-12-22.
 */
@TeleOp
public class MotorTestOpMode extends LinearOpMode {
    DcMotor motor;
    Servo servo;

    @Override
    public void runOpMode(){
        servo = hardwareMap.get(Servo.class, "hand");
        motor = hardwareMap.get(DcMotor.class, "leftWheel");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){
            if(this.gamepad1.right_bumper)
            {
                servo.setPosition(.5);
            } else if (this.gamepad1.left_bumper)
            {
                servo.setPosition(0);
            }

            if(this.gamepad1.a)
            {
                motor.setPower(.5);
            }
            else
            {
                motor.setPower(0);
            }

            motor.setPower(this.gamepad1.left_stick_y);
        }



    }

}
