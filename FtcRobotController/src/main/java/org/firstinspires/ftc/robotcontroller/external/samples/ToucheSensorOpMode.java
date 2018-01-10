package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Garrick on 2017-12-22.
 */

@TeleOp
public class ToucheSensorOpMode extends LinearOpMode {
    private DigitalChannel touch1;
    private LynxI2cColorRangeSensor color;
//    private DigitalChannel touch2;

    @Override

    public void runOpMode() {

        touch1 = hardwareMap.get(DigitalChannel.class, "touch2");
        color = hardwareMap.get(LynxI2cColorRangeSensor.class, "color");

        touch1.setMode(DigitalChannel.Mode.INPUT);

            waitForStart();

            while (opModeIsActive()){
                if (touch1.getState()){
                    telemetry.addData("Touch", "Is Not Pressed");
                } else {
                    telemetry.addData("Touch", "Is Pressed");
                }
                telemetry.addData("color", color.getDistance(DistanceUnit.CM));
                telemetry.addData( "color2", color.getLightDetected());
                telemetry.addData( "color2", color.red());
                telemetry.addData( "color2", color.blue());
                telemetry.addData( "color2", color.green());
//                if (touch2.getState()){
//                    telemetry.addData("Touch", "Is Not Pressed");
//                } else {
//                    telemetry.addData("Touch", "Is Pressed");
//                }
                telemetry.update();
            }
    }
}
