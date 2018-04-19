package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_SZ;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;


public class FTC_2018_SZ_Robot2_Init {


    public Servo ArmR;
    public Servo ArmL;
    public LightSensor ColorUpperL;
    public LightSensor ColorLowerL;
    public LightSensor ColorUpperR;
    public LightSensor ColorLowerR;

    public DcMotor plate;
    public DcMotor lifting;

    public DcMotor Lroll;
    public DcMotor Rroll;

    public DcMotor Lfront;
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;

    public Servo GolfL;
    public Servo GolfR;
    public Servo Clip1;
    public Servo Clip2;

    public final double clipDown = 0;   //continuous servo value
    public final double clipUp = 1;     //continuous servo value
    public final double clipStop = 0.5;//continuous servo value

    //Auto
    public final double ArmLOpen = 1;
    public final double ArmLClose = 0.4;
    public final double ArmROpen = 0;
    public final double ArmRClose = 0.6;

    public final double JewelBlueUpper = 0.25;
    public final double JewelBlueLower = 0.1;
    public final double JewelRedUpper = 0.5;
    public final double JewelRedLower = 0.27;

    public final double GolfLOpen = 1;
    public final double GolfROpen = 0;
    public final double GolfLClose = 0;
    public final double GolfRClose = 1;

    public final double Clip1Open = 0.5;
    public final double Clip1Close = 0.25;
    public final double Clip2Open = 0.5;
    public final double Clip2Close = 0.25;

    //Drive
    public double Lfronttmp = 0;
    public double Lbacktmp = 0;
    public double Rfronttmp = 0;
    public double Rbacktmp = 0;

    public double Lfrontforward = 0;
    public double Lbackforward = 0;
    public double Rfrontforward = 0;
    public double Rbackforward = 0;

    public int plate_encoder = -3750;
    public int lifting_encoder = 3400;
    public boolean plate_is_flip = false;

    public boolean toggle_Golf = false;
    public boolean state_Golf = false;
    public boolean toggle_Clip = false;
    public boolean state_Clip = false;


    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        ArmL = _hw.servo.get("ArmL");
        ArmR = _hw.servo.get("ArmR");

        plate = _hw.dcMotor.get("plate");
        lifting = _hw.dcMotor.get("lifting");

        Lroll = _hw.dcMotor.get("Lroll");
        Rroll = _hw.dcMotor.get("Rroll");

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");

        GolfL = _hw.servo.get("GolfL");
        GolfR = _hw.servo.get("GolfR");

        Clip1 = _hw.servo.get("Clip1");
        Clip2 = _hw.servo.get("Clip2");

        ColorUpperL = _hw.lightSensor.get("ColorUpperL");
        ColorLowerL = _hw.lightSensor.get("ColorLowerL");
        ColorUpperR = _hw.lightSensor.get("ColorUpperR");
        ColorLowerR = _hw.lightSensor.get("ColorLowerR");

        Rfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Rback.setDirection(DcMotorSimple.Direction.REVERSE);
        Rroll.setDirection(DcMotorSimple.Direction.REVERSE);

        Lfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Lback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Rfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Rback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void runModeSet(String mode) {
        if (mode == "position") {
            Lfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Rfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Rback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (mode == "position2") {
            plate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifting.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (mode == "reset") {
            Lfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Rfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Rback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else if (mode == "reset2") {
            plate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lifting.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else if (mode == "tele") {
            Lfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Lback.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Rfront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Rback.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else if (mode == "encoder") {
            Lfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Rfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Rback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lroll.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Rroll.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else if (mode == "encoder2") {
            plate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lifting.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}