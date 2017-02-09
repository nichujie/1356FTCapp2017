package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by asus-user on 2017/1/24.
 */

@TeleOp(name = "TestOp", group = "test")

public class TestOp  extends LinearOpMode
{
    DcMotor motor1 = null;
//    DcMotor motor2 = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motor1 = hardwareMap.dcMotor.get("motor1");
//        motor2 = hardwareMap.dcMotor.get("motor2");

        waitForStart();
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//        motor2.setDirection(DcMotorSimple.Direction.REVERSE);

        while(opModeIsActive())
        {
            motor1.setPower( gamepad1.left_stick_x);
//            motor2.setPower(1.0);
            idle();

        }
    }
}
