package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Garrick on 2017-11-24.
 */

public class fullRobotOpMode extends LinearOpMode {
    private DcMotor armMotor;
    private Servo hand;
    private CRServo finger;
    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;
    private Servo wrist;
    private DigitalChannel touch1;
    private boolean armTopPosition;
    private int handOpened;

    @Override
    public void runOpMode() throws InterruptedException {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        finger = hardwareMap.get(CRServo.class, "finger");
        finger.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand = hardwareMap.get(Servo.class, "hand");
        wrist = hardwareMap.get(Servo.class, "wrist");
        leftWheelMotor = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheelMotor = hardwareMap.get(DcMotor.class, "rightWheel");
        touch1 = hardwareMap.get(DigitalChannel.class, "touch2");

        boolean twoOperators = false;

        telemetry.addLine("Choose Operator Mode Using Controller 1");
        telemetry.addData("Press X", "1 Operator");
        telemetry.addData("Press B", "2 Operators");
        telemetry.update();

        while(true)
        {
            if(this.gamepad1.x)
            {
                break;
            }
            else if (this.gamepad1.b)
            {
                twoOperators = true;
                break;
            }
            else if (isStopRequested())
            {
                break;
            }
        }

        boolean aHeld = false;
        boolean bHeld = false;
        boolean yHeld = false;

        rightWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        hand.scaleRange(0.1, 0.6);
        wrist.scaleRange(0.1, .8);

        seekLowerArmPosition();
        telemetry.addData("Status", touch1.getState());
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            boolean operator2 = armTopPosition && twoOperators;
            Gamepad currentOperator = operator2 ? this.gamepad2 : this.gamepad1;

            if(currentOperator.b  && !bHeld)
            {
                bHeld = true;
                if(armTopPosition)
                {
                    bHeld = true;
                    pushBlocks();
                }
                else
                {
                    if(handOpened != 2){
                        closeHand();
                    }
                    else{
                        openHand();
                    }
                }
            }
            else if(!currentOperator.b && bHeld)
            {
                bHeld = false;
            }

            if(currentOperator.y  && !bHeld)
            {
                yHeld = true;
                if(armTopPosition)
                {
                    yHeld = true;
                    pushBlocks();
                }
                else
                {
                    if(handOpened != 3){
                        close2Hand();
                    }
                    else{
                        openHand();
                    }
                }
            }
            else if(!currentOperator.y && yHeld)
            {
                bHeld = false;
            }


            telemetry.addData("B Held Down", bHeld);

            if (currentOperator.dpad_down)
            {
                seekDefaultArmPosition();
            }

            if (currentOperator.a && !aHeld)
            {
                aHeld = true;
                if (armTopPosition)
                {
                    seekLowerArmPosition();
                }
                else
                {
                    seekTopArmPosition();
                }
            }
            else if (!currentOperator.a && aHeld)
            {
                aHeld = false;
            }

            telemetry.addData("A Held Down", aHeld);

            telemetry.addData("armPosition", armMotor.getCurrentPosition());

            leftWheelMotor.setPower(armTopPosition ? currentOperator.left_stick_y *  -1 : currentOperator.right_stick_y);
            rightWheelMotor.setPower(armTopPosition ? currentOperator.right_stick_y *  -1 : currentOperator.left_stick_y);

            telemetry.addData("LeftStick", currentOperator.left_stick_y);
            telemetry.addData("RightStick", currentOperator.right_stick_y);
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }

    private void pushBlocks() {
        openHand();
        sleep(2000);
        finger.setPower(1);
        leftWheelMotor.setPower(-0.05);
        rightWheelMotor.setPower(-0.05);
        sleep(2000);
        finger.setPower(0);
        seekLowerArmPosition();
        sleep(1000);
        leftWheelMotor.setPower(0);
        rightWheelMotor.setPower(0);
    }

    private void seekTopArmPosition(){
        armMotor.setTargetPosition(6750);
        armMotor.setPower(1);
        armTopPosition = true;
        seekWristVertical();
    }

    private void seekLowerArmPosition(){
        armMotor.setTargetPosition(19300);
        armMotor.setPower(1);
        armTopPosition = false;
        seekWristHorizontal();
    }

    private void seekDefaultArmPosition(){
        armMotor.setTargetPosition(0);
        armMotor.setPower(1);
        hand.setPosition(0.5);
        seekWristHorizontal();
    }

    private void seekWristHorizontal(){
        wrist.setPosition(0.9);
    }

    private void seekWristVertical(){
        wrist.setPosition(0.2);
    }

    private void close2Hand(){
        hand.setPosition(0.5);
        handOpened = 3;
    }

    private void openHand(){
        hand.setPosition(0.9);
        handOpened = 1;
    }

    private void closeHand(){
        hand.setPosition(0.1);
        handOpened = 2;
    }


}
