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


@Autonomous(name="FTC2018_robot1_Auto", group ="FTC 2018")

public class FTC2018_robot1_Auto extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.5;

    FTC2018_RobotInit_robotDummy robot = new FTC2018_RobotInit_robotDummy();

    public void initial(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);  //init
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
        robot.Lback.setPower(0);
        robot.Lfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Rback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Lfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Rback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void SetDistanceToGo(double DistanceInCm, double LfrontPower, double RfrontPower, double LbackPower, double RbackPower){
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

        robot.Lfront.setTargetPosition(ValueForEncoder);
        robot.Rfront.setTargetPosition(ValueForEncoder);
        robot.Lback.setTargetPosition(ValueForEncoder);
        robot.Rback.setTargetPosition(ValueForEncoder);
        robot.Lfront.setPower(LfrontPower);
        robot.Rfront.setPower(RfrontPower);
        robot.Lback.setPower(LbackPower);
        robot.Rback.setPower(RbackPower);

        while (opModeIsActive() && robot.Lfront.isBusy() && robot.Rfront.isBusy() && robot.Lback.isBusy() && robot.Rback.isBusy()){
            robot.Lfront.setPower(0);
            robot.Rfront.setPower(0);
            robot.Lback.setPower(0);
            robot.Rback.setPower(0);
            telemetry.addData("Done","!");
            telemetry.update();
            telemetry.update();
        }
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);
    }
    public void forward(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,LocalSpeed,LocalSpeed,LocalSpeed);
    }
    public void backward(double Distance){
        SetDistanceToGo(Distance,-LocalSpeed,-LocalSpeed,-LocalSpeed,-LocalSpeed);
    }
    public void left(double Distance){
        SetDistanceToGo(Distance,-LocalSpeed,LocalSpeed,LocalSpeed,-LocalSpeed);
    }
    public void right(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,-LocalSpeed,-LocalSpeed,LocalSpeed);
    }
    @Override public void runOpMode() {
        initial();

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

        forward(1000);
        /*relicTrackables.activate();
        while (opModeIsActive()) {
            telemetry.addData("Position: ",robot.Lfront.getCurrentPosition());
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("Result",vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Position", format(pose));

                if (pose != null) {
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
                }
            }
            else {
                telemetry.addData("Result", "not visible");
                telemetry.addData("Position: ",robot.Lfront.getCurrentPosition());

            }

            telemetry.update();
        }*/
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
