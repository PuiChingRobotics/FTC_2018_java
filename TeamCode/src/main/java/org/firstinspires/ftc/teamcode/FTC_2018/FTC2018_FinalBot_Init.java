package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;


public class FTC2018_FinalBot_Init {

    public Servo clipL;
    public Servo clipR;

    public Servo unusedL;
    public Servo unusedR;
    public Servo unusedClip;

    public Servo ArmBaseBlue;
    public Servo ArmTopBlue;
    public Servo ArmBaseRed;
    public Servo ArmTopRed;

    public LightSensor ColourSensorBlue;
    public LightSensor ColourSensorRed;

    public DcMotor rope;
    public DcMotor lifting;

    public DcMotor Lrope;
    public DcMotor Rrope;

    public DcMotor Lroll;
    public DcMotor Rroll;

    public DcMotor Lfront;
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;

    public final double clipDown = 0;   //continuous servo value
    public final double clipUp = 1;     //continuous servo value
    public final double clipStop = 0.5;//continuous servo value

    //Auto
    public final double ArmBaseBackwardBlue = 0.8;
    public final double ArmBaseCentreBlue = 0.5;
    public final double ArmBaseForwardBlue = 0.2;
    public final double ArmTopOpen1Blue = 0.9;
    public final double ArmTopOpen2Blue = 0.92;
    public final double ArmTopCloseBlue = 0.2;

    public final double ArmBaseBackwardRed = 0.2;
    public final double ArmBaseCentreRed = 0.46;
    public final double ArmBaseForwardRed = 0.8;
    public final double ArmTopOpen1Red = 0.1;
    public final double ArmTopOpen2Red = 0.05;
    public final double ArmTopCloseRed = 0.8;

    public final double JewelBlueUpper = 0.25;
    public final double JewelBlueLower = 0.1;
    public final double JewelRedUpper = 0.5;
    public final double JewelRedLower = 0.27;

    public final double turning90 = 36;
    public final double turning180 = 48;

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

        clipL = _hw.servo.get("clipL");
        clipR = _hw.servo.get("clipR");

        ArmBaseBlue = _hw.servo.get("ArmBaseBlue");
        ArmTopBlue = _hw.servo.get("ArmTopBlue");
        ArmBaseRed = _hw.servo.get("ArmBaseRed");
        ArmTopRed = _hw.servo.get("ArmTopRed");

        Lrope = _hw.dcMotor.get("Lrope");
        Rrope = _hw.dcMotor.get("Rrope");

        Lroll = _hw.dcMotor.get("Lroll");
        Rroll = _hw.dcMotor.get("Rroll");

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");

        ColourSensorBlue = _hw.lightSensor.get("ColourSensorBlue");
        ColourSensorRed = _hw.lightSensor.get("ColourSensorRed");

        Rfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Rback.setDirection(DcMotorSimple.Direction.REVERSE);
        Lrope.setDirection(DcMotorSimple.Direction.REVERSE);
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