package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by asus-user on 2017/2/4.
 */

@Autonomous(name = "TestAuto",group = "testauto")
public class TestAuto extends LinearOpMode {
            DcMotor motor1 = null;
            TouchSensor ts = null;
            public void runOpMode() throws InterruptedException{
                    motor1 = hardwareMap.dcMotor.get("motor1");
                    ts = hardwareMap.touchSensor.get("ts");

                    waitForStart();

                    while(opModeIsActive()){
                        if(ts.isPressed()){
                            motor1.setPower(1.0);
                        }
                        else{
                            motor1.setPower(0.0);
                        }
                    }
            }
}
