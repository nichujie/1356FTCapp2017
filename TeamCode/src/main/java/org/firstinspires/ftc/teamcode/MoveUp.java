package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by asus-user on 2017/3/11.
 */
@Autonomous(name = "Move",group = "final")
public class MoveUp extends LinearOpMode{

    DcMotor motor1 = null;
    DcMotor motor2 = null;
    int t = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);



        while(opModeIsActive()&&t==0){
            wait(5000);
            motor1.setPower(1);
            motor2.setPower(1);
            wait(4000);
            motor1.setPower(0);
            motor2.setPower(0);
            wait(1000);
            motor1.setPower(0.5);
            motor2.setPower(-0.5);
            wait(1000);
            motor1.setPower(1);
            motor2.setPower(1);
            wait(4000);
            t=1;
            idle();
        }
    }
}
