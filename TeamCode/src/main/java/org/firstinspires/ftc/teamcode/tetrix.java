package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by asus-user on 2017/3/18.
 */
@TeleOp(name="tetrix",group = "tetrix")
public class tetrix extends LinearOpMode{
    DcMotor motor1 = null;
    DcMotor motor2 = null;
    DcMotor motor3 = null;
    DcMotor motor4 = null;
    int status = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.FORWARD);
        motor4.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double pos = motor4.getCurrentPosition();

        while(opModeIsActive()){
            motor1.setPower(gamepad1.left_stick_y);
            motor2.setPower(gamepad1.right_stick_y);
            if(gamepad1.x) status = 1 - status;
            motor3.setPower(status);
            if(gamepad1.a && !motor4.isBusy()){hit(); }
            if(gamepad1.b && !motor4.isBusy()){rev(); }
        }
    }

    public void hit(){
        motor4.setPower(1);
        motor4.setTargetPosition(motor4.getCurrentPosition()+500);
    }

    public void rev(){
        motor4.setPower(-1);
        motor4.setTargetPosition(motor4.getCurrentPosition()-500);
    }
}
