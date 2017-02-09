package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TutorialOpMode", group = "Tutorial")

public  class TutorialOpMode extends LinearOpMode {

    DcMotor motor1 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while(opModeIsActive())
        {
            motor1.setPower(0.5);
            idle();
        }
    }
}
