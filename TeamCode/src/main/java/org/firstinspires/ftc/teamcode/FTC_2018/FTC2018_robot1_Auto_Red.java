package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Set;


@Autonomous(name="FTC2018_robot1_Auto_Red", group ="FTC 2018")

public class FTC2018_robot1_Auto_Red extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.2;

    FTC2018_RobotInit_robotDummy robot = new FTC2018_RobotInit_robotDummy();

    public void initial(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);  //init
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
        robot.Lback.setPower(0);
        robot.Lfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Rfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Lback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Rback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Lfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void SetDistanceToGo(double DistanceInCm, double LocalPowerAll, int LfrontEncoder, int RfrontEncoder, int LbackEncoder, int RbackEncoder){
        //declare variable

        double DiameterOfWheel = 10;
        final double EncoderValue = 1680;
        final double GearRatio = 2;
        final double Pi = Math.PI;
        double Circumference = DiameterOfWheel*Pi;
        double DistanceOfEachCircleOfMotor = Circumference*GearRatio;
        double ValueForEncoderFor1Cm = (EncoderValue/DistanceOfEachCircleOfMotor);
        double ValueForEncoderTemp = 0;
        int ValueForEncoder = 0;
        int tmp = 0;
        ValueForEncoderTemp = ValueForEncoderFor1Cm*DistanceInCm;
        ValueForEncoder = (int) ValueForEncoderTemp;
        robot.Lfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.Lfront.setTargetPosition(ValueForEncoder*LfrontEncoder);
        robot.Rfront.setTargetPosition(ValueForEncoder*RfrontEncoder);
        robot.Lback.setTargetPosition(ValueForEncoder*LbackEncoder);
        robot.Rback.setTargetPosition(ValueForEncoder*RbackEncoder);
        robot.Lfront.setPower(LocalPowerAll);
        robot.Rfront.setPower(LocalPowerAll);
        robot.Lback.setPower(LocalPowerAll);
        robot.Rback.setPower(LocalPowerAll);

        while (opModeIsActive() && robot.Lfront.isBusy() && robot.Rfront.isBusy() && robot.Lback.isBusy() && robot.Rback.isBusy()){
            telemetry.update();
        }
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);
    }
    public void forward(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,-1,1,-1,1);
    }
    public void backward(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,1,-1,1,-1);
    }
    public void left(double Distance){
        //double Distance2 = 0;
        Distance = Distance/0.8;
        SetDistanceToGo(Distance,LocalSpeed,1,1,-1,-1);
    }
    public void right(double Distance){
        //double Distance2 = 0;
        Distance = Distance/0.80;
        SetDistanceToGo(Distance,LocalSpeed,-1,-1,1,1);
    }
    @Override public void runOpMode() {
        initial();
        int check = 0;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AQbfLYb/////AAAAGYSvbQxpHU4Jnf1iT7yycDF6Cl/8h1ou6rt1cOj11DkC4pkctTMfrczppSNUD6tN3lM5QfL/VwEnAw0tAo17vRzdoSUzWtStgnFoq9JH3sCiCnf/w+ejflO2PuIROF9+fCEOTx/TfOC11azstm/svhL3iVlbVV+VddSf1a8QnK6IBXw6fqP7/P7noNET6Uxhrjq222nftV3fFNNFIUyFmmtee1NlxxO4rf6AcTgq7168juVwix2xZuEk7k+E3CACpxkJ/f0ROKosM0KSw6wimdcCF8zHt9wWGlztpzTyHcPyJC8cvhiK7qkZse5MedJeSY+49M6LqVkzLO3PIGKgi1KmSnj8XTuGNCWsMIykTj2X";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData("Status", "Camera is ready");
        telemetry.update();

        waitForStart();
        relicTrackables.activate();
        while (opModeIsActive() && check < 1) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("Result",vuMark);

                if (vuMark != RelicRecoveryVuMark.CENTER){
                    if (vuMark != RelicRecoveryVuMark.LEFT){
                        check = 3;
                    }
                }
                if (vuMark != RelicRecoveryVuMark.CENTER){
                    if (vuMark != RelicRecoveryVuMark.RIGHT){
                        check = 2;
                    }
                }
                if (vuMark != RelicRecoveryVuMark.RIGHT){
                    if (vuMark != RelicRecoveryVuMark.LEFT){
                        check = 1;
                    }
                }
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Position", format(pose));

                /*if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;

                    String pX="",pY="";
                    if (tX == 0) pX = "Center";
                    else if (tX > 0) pX = "Left";
                    else if (tX < 0) pX = "Right";

                    if (tY == 0) pY = "Center";
                    else if (tY > 0) pY = "Down";
                    else if (tY < 0) pY = "Up";

                    telemetry.addData("X-Coordinate","%.2f %s",tX,pX);
                    telemetry.addData("Y-Coordinate","%.2f %s",tY,pY);
                    telemetry.addData("Z-Coordinate","%.2f",tZ);
                }*/
            }
            else {
                telemetry.addData("Result", "not visible");
            }

            telemetry.update();
        }
        telemetry.addData("Check",check);
        telemetry.update();
        if (check == 3 ){           //right
            forward(60);
            left(30);
        }
        if (check == 2 ){           //left
            forward(60);
            right(30);
        }
        if (check == 1 ){           //centre
            forward(60);
            left(30);
            forward(60);
        }
        if (check == 0 ){           //unknown
            forward(60);
            left(60);
            backward(60);
        }

    }




    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

}
