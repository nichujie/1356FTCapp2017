package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by asus-user on 2017/3/13.
 */
@TeleOp(name="SensorTest",group="test")

public class SensorTest extends LinearOpMode{
    IrSeekerSensor ir = null;
    OpticalDistanceSensor ods = null;

    @Override
    public void runOpMode() throws InterruptedException {

        ir = hardwareMap.irSeekerSensor.get("ir");
        ods = hardwareMap.opticalDistanceSensor.get("ods");
        ods.enableLed(true);
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("IR: %f:",ir.getAngle());
            telemetry.addData("Threshold: %f",ir.getSignalDetectedThreshold());
            telemetry.addData("ODS Raw Light: %f",ods.getRawLightDetected());
            telemetry.addData("ODS Light: %f",ods.getLightDetected());
            //telemetry.addData("",ods.enableLed();)
            telemetry.update();

        }
    }
}
