package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name="FTC2018_robot1_Auto_Blue_2", group ="FTC 2018")

public class FTC2018_robot1_Auto_Blue_2 extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.5;

    FTC2018_RobotInit_robot1 robot = new FTC2018_RobotInit_robot1();

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
        robot.RArm.setPosition(robot.RArmOpen);

    }
    public void SetDistanceToGo(double DistanceInCm, double LocalPowerAll, int LfrontEncoder, int RfrontEncoder, int LbackEncoder, int RbackEncoder){
        //declare variable
        double DiameterOfWheel = 10;
        final double EncoderValue = 1680;
        final double GearRatio = 1;
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
        SetDistanceToGo(Distance,LocalSpeed,1,1,1,1);
    }
    public void backward(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,-1,-1,-1,-1);
    }
    public void left(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,-1,1,-1,1);
    }
    public void right(double Distance){
        SetDistanceToGo(Distance,LocalSpeed,1,-1,1,-1);
    }
    @Override public void runOpMode() {
        initial();
        String check = "Empty";
        String team = "Red";
        String jewel = "Blue";

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

        telemetry.addData("Status", "Scanning");
        telemetry.update();

        while (opModeIsActive() && check == "Empty") {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark == RelicRecoveryVuMark.CENTER) check = "Center";
            else if (vuMark == RelicRecoveryVuMark.LEFT) check = "Left";
            else if (vuMark == RelicRecoveryVuMark.RIGHT) check = "Right";
            telemetry.addData("Result", vuMark);
            telemetry.update();
        }

        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

        telemetry.addData("Check", check);
        telemetry.update();

        if (team == "Red") {
            if (jewel == "Red") {
                SetDistanceToGo(8,0.3,1,-1,1,-1);
                robot.RArm.setPosition(robot.RArmClose);
                SetDistanceToGo(8,0.3,-1,1,-1,1);
            }
            else if (jewel == "Blue") {
                SetDistanceToGo(8,0.3,-1,1,-1,1);
                SetDistanceToGo(8,0.3,1,-1,1,-1);
            }
        }

        sleep(3000);

        if (check == "Center") {           //centre
            forward(60);
            left(30);
            forward(60);
        }
        else if (check == "Right") {           //right
            forward(60);
            sleep(200);
            backward(20);
        }
        else if (check == "Left") {           //left
            forward(60);
            right(30);
        }
        else {           //unknown
            forward(60);
            sleep(200);
        }
        telemetry.update();
    }
}