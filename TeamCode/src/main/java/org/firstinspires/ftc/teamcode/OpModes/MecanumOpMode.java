package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class MecanumOpMode extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight;

    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested()) {

            drive(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x,1f);


        }

    }

    private void drive(double leftX, double leftY, double rightX, double speedFactor) {

        speedFactor = Math.min(speedFactor, 1);
        speedFactor = Math.max(speedFactor, 0);

        double r = Math.hypot(leftX, leftY);
        double robotAngle = Math.atan2(-leftY, leftX) - Math.PI / 4;

        final double v1;
        final double v2;
        final double v3;
        final double v4;
//        if(speedFactor + Math.abs(rightX) > 1) {
//            v1 = (r * Math.cos(robotAngle) + rightX) / speedFactor + Math.abs(rightX);
//            v2 = (r * Math.sin(robotAngle) - rightX) / speedFactor + Math.abs(rightX);
//            v3 = (r * Math.sin(robotAngle) + rightX) / speedFactor + Math.abs(rightX);
//            v4 = (r * Math.cos(robotAngle) - rightX) / speedFactor + Math.abs(rightX);
//        } else {
        v1 = r * Math.cos(robotAngle) + rightX;
        v2 = r * Math.sin(robotAngle) - rightX;
        v3 = r * Math.sin(robotAngle) + rightX;
        v4 = r * Math.cos(robotAngle) - rightX;
//        }

        frontLeft.setPower(v1 * speedFactor);
        frontRight.setPower(v2 * speedFactor);
        backLeft.setPower(v3 * speedFactor);
        backRight.setPower(v4 * speedFactor);
    }


}
