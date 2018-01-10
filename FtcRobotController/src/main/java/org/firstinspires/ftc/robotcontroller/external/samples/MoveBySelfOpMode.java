package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Garrick on 2017-12-29.
 */
@Autonomous
public class MoveBySelfOpMode extends LinearOpMode {
    private boolean findBlue;
    private LynxI2cColorRangeSensor color;
    @Override
    public void runOpMode() {
        color = hardwareMap.get(LynxI2cColorRangeSensor.class, "color");
        findBlue = true;
        telemetry.addData("Select Color", "X: Blue B: Red");
        telemetry.update();

        while(true){
            if(this.gamepad1.x){
                findBlue = true;
                break;
            }
            else if(this.gamepad1.b){
                findBlue = false;
                break;
            }
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {




            telemetry.addData("Distance", color.getDistance(DistanceUnit.MM));
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
