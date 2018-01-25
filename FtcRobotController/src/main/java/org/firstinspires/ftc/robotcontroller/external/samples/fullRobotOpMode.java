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
//    private CRServo finger2;
    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;
    private Servo wrist;
    private DigitalChannel touch1;
    private int armTopPosition;
    private int handOpened;
    private boolean seekDefault;

    @Override
    public void runOpMode() throws InterruptedException {
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        finger = hardwareMap.get(CRServo.class, "finger");
//        finger2 = hardwareMap.get(CRServo.class, "finger2");
        finger.setDirection(DcMotorSimple.Direction.REVERSE);
        finger.setDirection(DcMotorSimple.Direction.REVERSE);
//        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand = hardwareMap.get(Servo.class, "hand");
        wrist = hardwareMap.get(Servo.class, "wrist");
        leftWheelMotor = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheelMotor = hardwareMap.get(DcMotor.class, "rightWheel");
        touch1 = hardwareMap.get(DigitalChannel.class, "touchsen");

        boolean twoOperators = false;
        seekDefault = false;

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
        boolean rBumperHeld = false;
        boolean bHeld = false;
        boolean yHeld = false;

        rightWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        hand.scaleRange(0.1, 0.6);
        wrist.scaleRange(0.1, .8);

        armTopPosition = 4;

        telemetry.addData("Status", touch1.getState());
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
            boolean operator2 = armTopPosition == 2 && twoOperators;
            Gamepad currentOperator = operator2 ? this.gamepad2 : this.gamepad1;
            if(!seekDefault) {
                if (currentOperator.b && !bHeld) {
                    bHeld = true;
                    if (armTopPosition >= 1 && armTopPosition != 4) {
                        bHeld = true;
                        pushBlocks();
                    } else if (armTopPosition != 4) {
                        if (handOpened != 2) {
                            closeHand();
                        } else {
                            openHand();
                        }
                    }
                } else if (!currentOperator.b && bHeld) {
                    bHeld = false;
                }

                telemetry.addData("B Held Down", bHeld);

                if (currentOperator.y && !yHeld) {
                    yHeld = true;
                    if (armTopPosition >= 1 && armTopPosition != 4) {
                        yHeld = true;
                        pushBlocks();
                    } else if (armTopPosition !=4 ) {
                        if (handOpened != 3) {
                            close2Hand();
                        } else {
                            openHand();
                        }
                    }
                } else if (!currentOperator.y && yHeld) {
                    yHeld = false;
                }


                telemetry.addData("Y Held Down", yHeld);

                if (currentOperator.left_bumper) {
                    seekDefaultPosition();
                }

                if (currentOperator.a && !aHeld) {
                    aHeld = true;
                    if (armTopPosition == 2 || armTopPosition == 4) {
                        seekLowerArmPosition();
                    } else {
                        seekTopArmPosition();
                    }
                } else if (!currentOperator.a && aHeld) {
                    aHeld = false;
                }

                telemetry.addData("A Held Down", aHeld);

                if (currentOperator.right_bumper && !rBumperHeld) {
                    rBumperHeld = true;
                    if (armTopPosition == 1) {
                        seekLowerArmPosition();
                    } else {
                        seekLowerDropOffArmPosition();
                    }
                } else if (!currentOperator.right_bumper && rBumperHeld) {
                    rBumperHeld = false;
                }

                telemetry.addData("RB Held Down", rBumperHeld);

                telemetry.addData("armPosition", armMotor.getCurrentPosition());

                if (currentOperator.dpad_up) {
                    moveForward();
                } else if (currentOperator.dpad_down) {
                    moveBackward();
                } else {
                    leftWheelMotor.setPower(armTopPosition == 2 ? currentOperator.left_stick_y * -1 : currentOperator.right_stick_y);
                    rightWheelMotor.setPower(armTopPosition == 2 ? currentOperator.right_stick_y * -1 : currentOperator.left_stick_y);
                }
            }
            else
            {
                if(!touch1.getState())
                {
                    seekDefault = false;
                    armMotor.setPower(0);
                    armTopPosition = 4;
                    armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }
            telemetry.addData("touch", touch1.getState());
            telemetry.addData("HandState", handOpened);
            telemetry.addData("LeftStick", currentOperator.left_stick_y);
            telemetry.addData("RightStick", currentOperator.right_stick_y);
            telemetry.addData("Status", "Running");
            telemetry.addData("Arm", armMotor.getCurrentPosition());
            telemetry.addData("Wrist", wrist.getPosition());
            telemetry.update();

        }
    }

    private void pushBlocks() {
        hand.setPosition(0.25);
        sleep(2000);
        finger.setPower(-1);
//        finger2.setPower(-1);
        leftWheelMotor.setPower(armTopPosition == 2 ? -0.09 : 0.09);
        rightWheelMotor.setPower(armTopPosition == 2 ? -0.09 : 0.09);
        sleep(2000);
        openHand();
        finger.setPower(0);
//        finger2.setPower(0);
        seekLowerArmPosition();
        sleep(1000);
        leftWheelMotor.setPower(0);
        rightWheelMotor.setPower(0);
    }

    private void seekTopArmPosition(){
        armMotor.setTargetPosition(6750);
        armMotor.setPower(1);
        armTopPosition = 2;
        seekWristVertical();
    }

    private void seekLowerArmPosition(){
        armMotor.setTargetPosition(19500);
        armMotor.setPower(1);
        armTopPosition = 0;
        seekWristHorizontal();
    }

    private void seekLowerDropOffArmPosition(){
        armMotor.setTargetPosition(18750);
        armMotor.setPower(1);
        armTopPosition = 1;
        seekWristVertical();
    }

    private void seekDefaultPosition(){
        openHand();
        sleep(2000);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor.setPower(-0.5);
        sleep(1000);
        if(armTopPosition == 2)
        {
            defaultHand();
        }
        seekWristHorizontal();
        seekDefault = true;
    }

    private void seekWristHorizontal(){
        wrist.setPosition(0.9);
    }

    private void seekWristVertical(){
        wrist.setPosition(0.2);
    }

    private void close2Hand(){
        hand.setPosition(0.4);
        handOpened = 3;
    }

    private void defaultHand(){
        hand.setPosition(0.5);
        handOpened = 3;
    }

    private void openHand(){
        hand.setPosition(0.1);
        handOpened = 1;
    }

    private void closeHand(){
        hand.setPosition(0.9);
        handOpened = 2;
    }

    private void moveForward(){
        rightWheelMotor.setPower(armTopPosition == 2 ? 1 : -1);
        leftWheelMotor.setPower(armTopPosition == 2 ? 1 : -1);
    }

    private void moveBackward(){
        rightWheelMotor.setPower(armTopPosition == 2 ? -1 : 1);
        leftWheelMotor.setPower(armTopPosition == 2 ? -1 : 1);
    }

}
