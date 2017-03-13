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
            public void runOpMode() throws InterruptedException{


                VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
                params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
                params.vuforiaLicenseKey = "ATr0z1r/////AAAAGVfpauzOAUu8nGPNi2hK7VMYgbUXT2kDrI/0SvTjUOIQ7bf53yh3ZZqZiB25kIEEQZK6usWSpFTYBvVzCLPc4zfYWN1NOv0ZIF0fT9yI8u1K/AAzf0ISyYlaEriQ9G948QH51WGaTtB/z2dbLvnustTEy60KR4G65s0PPMgY7f13ElsBvcuompfcUh/byEyyPqvTtTUWt81cYd7/S/X88Bp2TZJh+bgTi+jG9ecRIkpsQ+OHmHiSQum9gu6/DX1IQJmhdoI22goZi58uchtVSSD9mqXYBGysbaMjAJjWz68bDZTyloZVw/jyzV0ARj4y+HZC3XTXFU76vqNf12yM0DKhTnDmTk0OGXqBpH7CpmWD";
                params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

                VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
                Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS,4);

                VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
                beacons.get(0).setName("Wheels");
                beacons.get(1).setName("Tools");
                beacons.get(2).setName("Legos");
                beacons.get(3).setName("Gears");

                beacons.activate();
                waitForStart();
                status_servo = servo1.getPosition();
                motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                motor4.setDirection(DcMotorSimple.Direction.FORWARD);


                while(opModeIsActive()&&t==0){

                    motor3.setPower(1);
                    motor4.setPower(1);
                    wait(2000);
                    servo1.setPosition(status_servo-0.42);
                    wait(1000);
                    motor3.setPower(0);
                    motor4.setPower(0);
                    servo1.setPosition(0.73);
                    t=1;
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
