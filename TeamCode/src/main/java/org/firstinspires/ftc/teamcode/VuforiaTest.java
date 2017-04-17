package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by asus-user on 2017/3/13.
 */
@Autonomous(name="VuforiaTest",group="test")
public class VuforiaTest extends LinearOpMode{
    DcMotor motor1 = null;
    DcMotor motor2 = null;

    @Override
    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);

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

        VuforiaTrackableDefaultListener wheels = (VuforiaTrackableDefaultListener) beacons.get(0).getListener();

        beacons.activate();

        waitForStart();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor1.setPower(0.2);
        motor2.setPower(0.2);

        while( opModeIsActive() && wheels.getRawPose() == null){
            idle();
        }
        motor1.setPower(0);
        motor2.setPower(0);

        VectorF angles = angleFromTarget(wheels);

        VectorF trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angles.get(0)) - 90,new VectorF(500,0,0));

        if(trans.get(0)>0){
            motor1.setPower(0.02);
            motor2.setPower(-0.02);
        }
        else{
            motor1.setPower(-0.02);
            motor2.setPower(0.02);
        }

        do{
            if(wheels.getPose()!=null){
                trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angles.get(0)) - 90,new VectorF(500,0,0));
            }
            idle();
        } while(opModeIsActive() && Math.abs(trans.get(0))>30);

        motor1.setPower(0);
        motor2.setPower(0);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setTargetPosition((int)(motor1.getCurrentPosition() + ((Math.hypot(trans.get(0),trans.get(2)) + 150 / 409.575 * 560))));
        motor2.setTargetPosition((int)(motor2.getCurrentPosition() + ((Math.hypot(trans.get(0),trans.get(2)) + 150 / 409.575 * 560))));

        motor1.setPower(0.3);
        motor2.setPower(0.3);

        while(opModeIsActive() && motor1.isBusy() && motor2.isBusy()){
            idle();
        }

        motor1.setPower(0);
        motor2.setPower(0);

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(opModeIsActive() && (wheels.getPose() == null || Math.abs(wheels.getPose().getTranslation().get(0))>10)){
            if(wheels.getPose() != null){
                if(wheels.getPose().getTranslation().get(0) > 0){
                    motor1.setPower(-0.3);
                    motor2.setPower(0.3);
                }else{
                    motor1.setPower(0.3);
                    motor2.setPower(-0.3);
                }
            }
            else{
                motor1.setPower(-0.3);
                motor2.setPower(0.3);
            }
        }

        motor1.setPower(0);
        motor2.setPower(0);


    }

    public VectorF navOffWall(VectorF trans, double robotAngle, VectorF offWall){
        return new VectorF((float) (trans.get(0)-offWall.get(0) * Math.sin(Math.toRadians(robotAngle)) - offWall.get(2) * Math.cos(Math.toRadians(robotAngle))),trans.get(1),(float) (trans.get(2) + offWall.get(0)*Math.cos(Math.toRadians(robotAngle))-offWall.get(2)*Math.sin(Math.toRadians(robotAngle))));
    }
    public VectorF angleFromTarget(VuforiaTrackableDefaultListener image){
        float[] data = image.getRawPose().getData();
        float[] [] rotation = {{data[0],data[1]},{data[4],data[5],data[6]},{data[8],data[9],data[10]}};
        double thetaX = Math.atan2(rotation[2][1],rotation[2][2]);
        double thetaY = Math.atan2(-rotation[2][0],Math.sqrt(rotation[2][1]*rotation[2][1]+rotation[2][2]*rotation[2][2]));
        double thetaZ = Math.atan2(rotation[1][0],rotation[0][0]);
        return new VectorF((float)thetaX,(float)thetaY,(float)thetaZ);
    }

}
