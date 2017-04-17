package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by asus-user on 2017/2/4.
 */

@Autonomous(name = "Ball",group = "final")
public class TestAuto extends LinearOpMode {
    DcMotor motor3 = null;
    DcMotor motor4 = null;
    Servo servo1 = null;
    double status_servo = 0;
    int t = 0;

    //           TouchSensor ts = null;
    //           OpticalDistanceSensor ods;
    public void runOpMode() throws InterruptedException {


        waitForStart();
        status_servo = servo1.getPosition();
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);
        motor4.setDirection(DcMotorSimple.Direction.FORWARD);


        while (opModeIsActive() && t == 0) {

            motor3.setPower(1);
            motor4.setPower(1);
            wait(2000);
            servo1.setPosition(status_servo - 0.42);
            wait(1000);
            motor3.setPower(0);
            motor4.setPower(0);
            servo1.setPosition(0.73);
            t = 1;

        }
    }
}
