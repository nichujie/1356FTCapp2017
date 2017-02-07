package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
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

@Autonomous(name = "TestAuto",group = "testauto")
public class TestAuto extends LinearOpMode {
            DcMotor motor1 = null;
            TouchSensor ts = null;
 //           OpticalDistanceSensor ods;
            public void runOpMode() throws InterruptedException{
                motor1 = hardwareMap.dcMotor.get("motor1");
                ts = hardwareMap.touchSensor.get("ts");
                //ods = hardwareMap.opticalDistanceSensor.get("ods");

                VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
                params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
                params.vuforiaLicenseKey = "ATr0z1r/////AAAAGVfpauzOAUu8nGPNi2hK7VMYgbUXT2kDrI/0SvTjUOIQ7bf53yh3ZZqZiB25kIEEQZK6usWSpFTYBvVzCLPc4zfYWN1NOv0ZIF0fT9yI8u1K/AAzf0ISyYlaEriQ9G948QH51WGaTtB/z2dbLvnustTEy60KR4G65s0PPMgY7f13ElsBvcuompfcUh/byEyyPqvTtTUWt81cYd7/S/X88Bp2TZJh+bgTi+jG9ecRIkpsQ+OHmHiSQum9gu6/DX1IQJmhdoI22goZi58uchtVSSD9mqXYBGysbaMjAJjWz68bDZTyloZVw/jyzV0ARj4y+HZC3XTXFU76vqNf12yM0DKhTnDmTk0OGXqBpH7CpmWD";
                params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

                VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
                Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS,4);

                VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
                beacons.get(0).setName("Wheels");
                beacons.get(1).setName("Tools");
                beacons.get(0).setName("Lego");
                beacons.get(0).setName("Gears");

                waitForStart();

                beacons.activate();
                while(opModeIsActive()){
                    for(VuforiaTrackable beac : beacons){
                        OpenGLMatrix pose = ( (VuforiaTrackableDefaultListener) beac.getListener() ).getPose();

                        if(pose != null){
                            VectorF translation = pose.getTranslation();

                            telemetry.addData(beac.getName() + "-Translation", translation);

                            double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2) ) );

                            telemetry.addData(beac.getName() + "Degrees", degreesToTurn);

                        }
                    }
                    telemetry.update();

                }
            }
}
