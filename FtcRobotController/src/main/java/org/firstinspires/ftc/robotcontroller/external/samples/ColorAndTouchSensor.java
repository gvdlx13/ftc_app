package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class ColorAndTouchSensor extends LinearOpMode {
    private LynxI2cColorRangeSensor colorSensor;
    private DigitalChannel touchSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        touchSensor = hardwareMap.get(DigitalChannel.class, "touch2");
        colorSensor = hardwareMap.get(LynxI2cColorRangeSensor.class, "color2");

        if (touchSensor.getState())
        {
            telemetry.addData("Touch Sensor Status", touchSensor.getState());
        }

        int blue = colorSensor.blue();
        int green = colorSensor.green();
        int red = colorSensor.red();

        telemetry.addData("Color Sensor Distance (MM)", colorSensor.getDistance(DistanceUnit.MM));
        telemetry.addData("Color Sensor Blue Value", blue);
        telemetry.addData("Color Sensor Distanct (MM)", green);
        telemetry.addData("Color Sensor Distanct (MM)", red);

        if (blue > green && blue > red){

        }
    }
}
