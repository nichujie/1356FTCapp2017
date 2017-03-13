package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by asus-user on 2017/3/13.
 */
@Autonomous(name="VuforiaTest",group="test")
public class VuforiaTest extends LinearOpMode{
    DcMotor motor1 = null;
    DcMotor motor2 = null;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while(opModeIsActive()){}

    }
}
