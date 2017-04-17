package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by asus-user on 2017/3/17.
 */
@TeleOp(name = "servotest",group = "test")

public class servotest extends LinearOpMode{

    Servo servo1 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.servo.get("servo1");

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.x) servo1.setPosition(0);
            if(gamepad1.y) servo1.setPosition(1);
            idle();
        }
    }
}
