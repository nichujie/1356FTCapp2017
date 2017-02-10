package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
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
 * Created by asus-user on 2017/1/24.
 */

@TeleOp(name = "TestOp", group = "test")

public class TestOp  extends LinearOpMode
{
    DcMotor motor1 = null;
    DcMotor motor2 = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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

        waitForStart();

        beacons.activate();
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(opModeIsActive())
        {
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

            motor1.setPower( gamepad1.left_stick_y);
            motor2.setPower( gamepad1.right_stick_y);
            idle();

        }
    }
}
