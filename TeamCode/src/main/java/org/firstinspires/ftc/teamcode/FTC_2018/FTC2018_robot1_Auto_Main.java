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


@Autonomous(name="FTC2018_robot1_Auto_Main", group ="FTC 2018")

public class FTC2018_robot1_Auto_Main extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.3;

    FTC2018_RobotInit_robot1 robot = new FTC2018_RobotInit_robot1();

    public void initial(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);  //init
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
        robot.Lback.setPower(0);
        robot.runModeSet("encoder");
        robot.runModeSet("reset");
        robot.runModeSet("position");
        robot.ArmBase.setPosition(robot.ArmBaseCentre);
        robot.ColourSensorL.enableLed(true);
        robot.clipL0.setPosition(0);//0.1
        robot.clipR0.setPosition(1);//0.9
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

    public void forward(double Distance){

        SetDistanceToGo(Distance,LocalSpeed,1,1,1,1);
    }

    public void backward(double Distance)
    {
        SetDistanceToGo(Distance,LocalSpeed,-1,-1,-1,-1);
    }

    public void left(double Distance) {
        SetDistanceToGo(Distance,LocalSpeed,-1,1,-1,1);
    }

    public void right(double Distance) {
        SetDistanceToGo(Distance,LocalSpeed,1,-1,1,-1);
    }

    public void Vuforia(String check) {
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


        robot.ArmTop.setPosition(robot.ArmTopOpen);
        long EndTimeScan = System.currentTimeMillis()+3000;
        while (System.currentTimeMillis() < EndTimeScan) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark == RelicRecoveryVuMark.CENTER) check = "Center";
            else if (vuMark == RelicRecoveryVuMark.LEFT) check = "Left";
            else if (vuMark == RelicRecoveryVuMark.RIGHT) check = "Right";
            else check = "Empty";
            telemetry.addData("Result", vuMark);
            telemetry.update();
        }

        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

        telemetry.addData("Final Result", check);
        telemetry.update();
    }


    public void Kick_Jewel(String team) {
        double data = 0;
        String jewel = "Blue";

        if (data > 0.26){
            jewel = "Red";
        }
        else {
            jewel = "Blue";
        }
        telemetry.addData("JewelColour",jewel);
        telemetry.update();

        if (jewel == "Red") {
            if (team == "Blue") {
                robot.ArmBase.setPosition(robot.ArmBaseForward);
            }
            else {
                robot.ArmBase.setPosition(robot.ArmBaseBackward);
            }
            sleep(300);
        }
        else if (jewel == "Blue") {
            if (team == "Blue") {
                robot.ArmBase.setPosition(robot.ArmBaseBackward);
            }
            else {
                robot.ArmBase.setPosition(robot.ArmBaseForward);
            }
            sleep(300);
        }
        robot.ArmBase.setPosition(robot.ArmBaseCentre);
        robot.ArmTop.setPosition(robot.ArmTopClose);
        sleep(2000);

        telemetry.addData("JewelColour",jewel);
        telemetry.update();
    }

    public void Glyph_Blue1(String team, String alliance, String check) {
        backward(45);
        sleep(500);
        forward(15);
        sleep(300);
        backward(15); //allign

        sleep(300);
        left(37);
        sleep(300);


        if (check == "Left") {//Left
            forward(15);
            sleep(300);
        }
        else if (check == "Centre") {//Centre
            forward(30);
            sleep(300);
        }
        else if (check == "Right") {//Right
            forward(45);
            sleep(300);
        }
        else {//unknown
            forward(30);
            sleep(300);
        }

        left(37);
        sleep(300);

        long EndTimeRoll = System.currentTimeMillis()+800;
        while (System.currentTimeMillis() < EndTimeRoll) {
            robot.Lroll.setPower(-0.5);
            robot.Rroll.setPower(-0.5);
            telemetry.addData("Rolling", System.currentTimeMillis());
            telemetry.update();
        }
        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);
        sleep(300);

        robot.clipL0.setPosition(0.6);//1
        robot.clipR0.setPosition(0.4);//0
        sleep(300);
        forward(8);
        sleep(300);
        forward(10);
        sleep(300);
        backward(10);
    }

    public void Glyph_Blue2(String team, String alliance, String check) {
        if (check == "Left") {//Left
        }
        else if (check == "Centre") {//Centre
        }
        else if (check == "Right") {//Right
        }
        else {//unknown
        }
    }

    public void Glyph_Red1(String team, String alliance, String check) {
        if (check == "Left") {//Left
        }
        else if (check == "Centre") {//Centre
        }
        else if (check == "Right") {//Right
        }
        else {//unknown
        }
    }

    public void Glyph_Red2(String team, String alliance, String check) {
        if (check == "Left") {//Left
        }
        else if (check == "Centre") {//Centre
        }
        else if (check == "Right") {//Right
        }
        else {//unknown
        }
    }

    public void Place_Glyph(String team, String alliance, String check) {
        if (team == "Blue" && alliance == "1") {
            Glyph_Blue1(team,alliance,check);
        }
        else if (team == "Blue" && alliance == "2") {
            Glyph_Blue2(team,alliance,check);
        }
        else if (team == "Red" && alliance == "1") {
            Glyph_Red1(team,alliance,check);
        }
        else if (team == "Red" && alliance == "2") {
            Glyph_Red2(team,alliance,check);
        }
    }

    @Override public void runOpMode() {
        String check = "Empty";
        String team = "Blue";
        String alliance = "1";

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


        robot.ArmTop.setPosition(robot.ArmTopOpen);
        long EndTimeScan = System.currentTimeMillis()+3000;
        while (System.currentTimeMillis() < EndTimeScan) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark == RelicRecoveryVuMark.CENTER) check = "Center";
            else if (vuMark == RelicRecoveryVuMark.LEFT) check = "Left";
            else if (vuMark == RelicRecoveryVuMark.RIGHT) check = "Right";
            else check = "Empty";
            telemetry.addData("Result", vuMark);
            telemetry.update();
        }

        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

        telemetry.addData("Final Result", check);
        telemetry.update();

        /*robot.LArm.setPosition(robot.LArmOpen);
        for (int i=1; i<=5; i++) {
            robot.LArm.setPosition(robot.LArmOpen-i*0.05);
            sleep(500);
            data = Math.max(robot.ColourSensorL.getLightDetected(),data);
        }
        sleep(3000);*/

        Kick_Jewel(team);

        Place_Glyph(team,alliance,check);

        telemetry.update();
    }
}