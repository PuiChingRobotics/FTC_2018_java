package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_SZ;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;


public class FTC_2018_SZ_Robot2_Init {

    /*public Servo clipL;
    public Servo clipR;*/


    public Servo unusedL;
    public Servo unusedR;
    public Servo unusedClip;

    public Servo ArmBase;
    public Servo ArmTop;

    public ColorSensor ColourSensor0;

    public DcMotor rope;
    public DcMotor lifting;

    /*public DcMotor Lrope;
    public DcMotor Rrope;

    public DcMotor Lroll;
    public DcMotor Rroll;*/

    public DcMotor Lfront;
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;

    public final double clipDown = 0;   //continuous servo value
    public final double clipUp = 1;     //continuous servo value
    public final double clipStop = 0.5;//continuous servo value

    //Auto
    public final double ArmBaseBackward = 0.8;
    public final double ArmBaseCentre = 0.5;
    public final double ArmBaseForward = 0.2;
    public final double ArmTopOpenUpper = 0.9;
    public final double ArmTopOpenLower = 0.92;
    public final double ArmTopClose = 0.2;

    public final double JewelBlueUpper = 0.25;
    public final double JewelBlueLower = 0.1;
    public final double JewelRedUpper = 0.5;
    public final double JewelRedLower = 0.27;

    //Drive
    public double Lfronttmp = 0;
    public double Lbacktmp = 0;
    public double Rfronttmp = 0;
    public double Rbacktmp = 0;

    public double Lfrontforward = 0;
    public double Lbackforward = 0;
    public double Rfrontforward = 0;
    public double Rbackforward = 0;

    //Roll
    public double Rolltmp = 0;


    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        /*clipL = _hw.servo.get("clipL");
        clipR = _hw.servo.get("clipR");*/

        ArmBase = _hw.servo.get("ArmBase");
        ArmTop = _hw.servo.get("ArmTop");

        /*Lrope = _hw.dcMotor.get("Lrope");
        Rrope = _hw.dcMotor.get("Rrope");

        Lroll = _hw.dcMotor.get("Lroll");
        Rroll = _hw.dcMotor.get("Rroll");*/

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");

        ColourSensor0 = _hw.colorSensor.get("ColourSensor0");

        Rfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Rback.setDirection(DcMotorSimple.Direction.REVERSE);
        /*Lrope.setDirection(DcMotorSimple.Direction.REVERSE);
        Rroll.setDirection(DcMotorSimple.Direction.REVERSE);*/

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
        else if (mode == "reset") {
            Lfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Rfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Rback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        }
    }
}