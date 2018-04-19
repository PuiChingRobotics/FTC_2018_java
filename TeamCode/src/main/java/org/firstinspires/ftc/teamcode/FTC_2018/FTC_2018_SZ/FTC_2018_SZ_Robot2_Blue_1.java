package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_SZ;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;


@Autonomous(name="FTC_2018_SZ_Robot2_Blue_1", group ="FTC 2018")

public class FTC_2018_SZ_Robot2_Blue_1 extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    final public double LocalSpeed = 0.5;
    String jewel = "Empty";
    String image = "Empty";
    String team = "Blue";
    String alliance = "1";
    double dataUpper = 0;
    double dataLower = 0;


    FTC_2018_SZ_Robot2_Init robot = new FTC_2018_SZ_Robot2_Init();

    public void initial(){
        robot.init(hardwareMap);

        robot.plate.setPower(0);
        robot.lifting.setPower(0);

        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);

        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);

        robot.Clip1.setPosition(robot.Clip1Open);
        robot.Clip2.setPosition(robot.Clip2Open);

        robot.GolfL.setPosition(robot.GolfLOpen);
        robot.GolfR.setPosition(robot.GolfROpen);

        robot.ColorUpperL.enableLed(true);
        robot.ColorLowerL.enableLed(true);
        robot.ColorUpperR.enableLed(true);
        robot.ColorLowerR.enableLed(true);

        robot.ArmL.setPosition(robot.ArmLClose);
        robot.ArmR.setPosition(robot.ArmRClose);
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

    public void left_horizontal(double Distance, double Power) {
        SetDistanceToGo(Distance, Power,-1,1,1,-1);
    }

    public void right_horizontal(double Distance, double Power) {
        SetDistanceToGo(Distance, Power,1,-1,-1,1);
    }


    public void Kick_Jewel() {
        robot.ArmR.setPosition(robot.ArmROpen);
        sleep(1000);

        dataUpper = robot.ColorUpperR.getLightDetected();
        dataLower = robot.ColorLowerR.getLightDetected();

        if (robot.JewelBlueLower < dataLower && dataLower < robot.JewelBlueUpper){
            jewel = "Blue";
        }
        else if (robot.JewelBlueUpper <= dataLower && dataLower <= robot.JewelRedLower) {//over-lapped data
            if (robot.JewelBlueLower < dataUpper && dataUpper < robot.JewelBlueUpper){
                jewel = "2nd time-Blue";
            }
            else if (robot.JewelBlueUpper <= dataUpper && dataUpper <= robot.JewelRedLower) {//over-lapped data
                jewel = "2nd time-Over-lapped";
            }
            else if (robot.JewelRedLower < dataUpper && dataUpper < robot.JewelRedUpper){
                jewel = "2nd time-Red";
            }
        }
        else if (robot.JewelRedLower < dataLower && dataLower < robot.JewelRedUpper){
            jewel = "Red";
        }

        telemetry.addData("Jewel",jewel);
        telemetry.addData("SensorValueLower",dataLower);
        telemetry.addData("SensorValueUpper",dataUpper);
        telemetry.update();

        if (jewel == "Blue") {
            right(5,0.3);
            sleep(500);
        }
        else if (jewel == "Red") {
            left(5,0.3);
            sleep(500);
        }

        robot.ArmR.setPosition(robot.ArmRClose);
        sleep(1000);

        if (jewel == "Blue") {
            left(5,0.3);
            sleep(500);
        }
        else if (jewel == "Red") {
            right(5,0.3);
            sleep(500);
        }

        telemetry.addData("Jewel",jewel);
        telemetry.addData("SensorValueLower",dataLower);
        telemetry.addData("SensorValueUpper",dataUpper);
        telemetry.update();
    }

    public void Glyph_Red1() {

    }

    public void Glyph_Red2() {

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

        waitForStart();

        Kick_Jewel();
        sleep(1000);

        //Place_Glyph();
        backward(40,1);
        sleep(500);
        right(33,1);
        sleep(500);
        backward(25,1);
        sleep(500);
        left(33,1);
        sleep(500);
        backward(10,1);
        sleep(500);

        /*forward(20,1);
        sleep(500);
        backward(20,1);
        sleep(500);*/

        telemetry.addData("Jewel",jewel);
        telemetry.addData("SensorValueLower",dataLower);
        telemetry.addData("SensorValueUpper",dataUpper);
        telemetry.update();
    }
}
