package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by asus-user on 2017/1/24.
 */

@TeleOp(name = "TestOp", group = "test")
//@Disabled
public class TestOp  extends LinearOpMode
{
    private DcMotor motor1;
    @Override
    public void runOpMode() throws InterruptedException
    {
        motor1=hardwareMap.dcMotor.get("motor1");
        waitForStart();

        while(opModeIsActive())
        {
            motor1.setPower(0.1);
            idle();

        }
    }
}
