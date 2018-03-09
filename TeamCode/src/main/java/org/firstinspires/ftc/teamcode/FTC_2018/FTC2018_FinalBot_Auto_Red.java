package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name="FTC2018_FinalBot_Auto_Red", group ="FTC 2018")

public class FTC2018_FinalBot_Auto_Red extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.5;
    String jewel = "Empty";
    String image = "Empty";
    String team = "Red";
    String alliance = "2";
    double data = 0;


    FTC2018_FinalBot_Init robot = new FTC2018_FinalBot_Init();

    public void initial(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
        robot.Lback.setPower(0);

        robot.runModeSet("encoder");
        robot.runModeSet("reset");
        robot.runModeSet("position");


        robot.ColourSensorBlue.enableLed(true);
        robot.ArmBaseBlue.setPosition(robot.ArmBaseCentreBlue);
        robot.ArmTopBlue.setPosition(robot.ArmTopCloseBlue);


        robot.ColourSensorRed.enableLed(true);
        robot.ArmBaseRed.setPosition(robot.ArmBaseCentreRed);
        robot.ArmTopRed.setPosition(robot.ArmTopCloseRed);

        robot.clipL.setPosition(0);
        robot.clipR.setPosition(1);
    }

    public void SetDistanceToGo(double DistanceInCm, double LocalPowerAll, int LfrontEncoder, int RfrontEncoder, int LbackEncoder, int RbackEncoder){
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

        robot.runModeSet("reset");
        robot.runModeSet("position");

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

    public void forward(double Distance, double Power)
    {

        SetDistanceToGo(Distance, Power,1,1,1,1);
    }

    public void backward(double Distance, double Power)
    {
        SetDistanceToGo(Distance, Power,-1,-1,-1,-1);
    }

    public void left(double Distance, double Power)
    {
        SetDistanceToGo(Distance, Power,-1,1,-1,1);
    }

    public void right(double Distance, double Power)
    {
        SetDistanceToGo(Distance, Power,1,-1,1,-1);
    }

    public void Kick_Jewel() {
        data = robot.ColourSensorRed.getLightDetected();
        if (robot.JewelRedLower < data && data < robot.JewelRedUpper){
            jewel = "Red";
        }
        else if (robot.JewelBlueUpper <= data && data <= robot.JewelRedLower) {//over-lapped data
            jewel = "Over-lapped";
        }
        else if (robot.JewelBlueLower < data && data < robot.JewelBlueUpper){
            jewel = "Blue";
        }

        telemetry.addData("Image",image);
        telemetry.addData("Jewel",jewel);
        telemetry.addData("SensorValue",data);
        telemetry.update();

        if (jewel == "Blue") {
            robot.ArmBaseRed.setPosition(robot.ArmBaseBackwardRed);
            sleep(500);
        }
        else if (jewel == "Red") {
            robot.ArmBaseRed.setPosition(robot.ArmBaseForwardRed);
            sleep(500);
        }
        else {
            robot.ArmTopRed.setPosition(0.2);
            sleep(500);
            robot.ArmBaseRed.setPosition(0.8);
            sleep(500);
        }

        robot.ArmTopRed.setPosition(robot.ArmTopCloseRed);
        sleep(1000);
        robot.ArmBaseRed.setPosition(robot.ArmBaseCentreRed);
        sleep(2000);

        telemetry.addData("Image",image);
        telemetry.addData("Jewel",jewel);
        telemetry.addData("SensorValue",data);
        telemetry.update();
    }

    public void Glyph_Red1() {
        //Allign
        backward(45,0.4);
        sleep(800);
        forward(20,LocalSpeed);
        sleep(500);
        backward(15,LocalSpeed);

        right(31, LocalSpeed);
        sleep(500);


        if (image == "Left") {//Left
            forward(45,LocalSpeed);
            sleep(500);
        }
        else if (image == "Centre") {//Centre
            forward(30,LocalSpeed);
            sleep(500);
        }
        else if (image == "Right") {//Right
            forward(15,LocalSpeed);
            sleep(500);
        }
        else {//Not found
            forward(30,LocalSpeed);
            sleep(500);
        }

        right(30,LocalSpeed);
        sleep(500);
        forward(5,LocalSpeed);

        //Shoot
        long EndTimeRoll = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < EndTimeRoll) {
            robot.Lroll.setPower(-1);
            robot.Rroll.setPower(-1);
            telemetry.update();
        }
        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);
        sleep(500);

        //Push
        robot.clipL.setPosition(0.6);
        robot.clipR.setPosition(0.4);
        sleep(500);
        forward(20, LocalSpeed);
        sleep(500);
        backward(15, LocalSpeed);
        sleep(500);

        //Ready
        right(66,LocalSpeed);
        sleep(500);
        backward(15, LocalSpeed);
        sleep(500);
    }

    public void Glyph_Red2() {
        //Allign
        backward(45,0.4);
        sleep(800);
        forward(20,LocalSpeed);
        sleep(500);

        if (image == "Left") {//Left
            backward(54,LocalSpeed);
            sleep(500);
        }
        else if (image == "Centre") {//Centre
            backward(39,LocalSpeed);
            sleep(500);
        }
        else if (image == "Right") {//Right
            backward(24,LocalSpeed);
            sleep(500);
        }
        else {//Not found
            backward(39,LocalSpeed);
            sleep(500);
        }

        left(33,LocalSpeed);
        sleep(500);
        forward(12,LocalSpeed);
        sleep(500);

        //Shoot
        long EndTimeRoll = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < EndTimeRoll) {
            robot.Lroll.setPower(-1);
            robot.Rroll.setPower(-1);
            telemetry.update();
        }
        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);
        sleep(500);

        //Push
        robot.clipL.setPosition(0.6);
        robot.clipR.setPosition(0.4);
        sleep(500);
        forward(10,LocalSpeed);
        sleep(500);
        backward(15,LocalSpeed);
        sleep(500);

        //Ready
        right(66,LocalSpeed);
        sleep(500);
        backward(15, LocalSpeed);
        sleep(500);

    }

    public void Place_Glyph() {
        telemetry.addData("image",image);
        telemetry.update();
        if (team == "Red" && alliance == "1") {
            Glyph_Red1();
        }
        else if (team == "Red" && alliance == "2") {
            Glyph_Red2();
        }
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

        relicTrackables.activate();

        telemetry.addData("Status", "Scanning");
        telemetry.update();

        long EndTimeScan = System.currentTimeMillis()+2000;
        while (System.currentTimeMillis() < EndTimeScan) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark == RelicRecoveryVuMark.CENTER) image = "Center";
            else if (vuMark == RelicRecoveryVuMark.LEFT) image = "Left";
            else if (vuMark == RelicRecoveryVuMark.RIGHT) image = "Right";
            else image = "Not found";
            robot.Lroll.setPower(0.3);
            robot.Rroll.setPower(0.3);
            telemetry.addData("Result", vuMark);
            telemetry.update();
        }

        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

        robot.ArmTopRed.setPosition(robot.ArmTopOpen1Red);
        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);
        sleep(1000);

        robot.ArmTopRed.setPosition(robot.ArmTopOpen2Red);
        sleep(1000);

        telemetry.addData("Image", image);
        telemetry.update();

        Kick_Jewel();

        Place_Glyph();

        telemetry.addData("Image", image);
        telemetry.addData("Jewel", jewel);
        telemetry.addData("data", data);
        telemetry.update();
        sleep(30000);
        //write something to push

    }
}
