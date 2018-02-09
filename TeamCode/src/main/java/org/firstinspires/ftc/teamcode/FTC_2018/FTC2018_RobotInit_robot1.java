package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.LightSensor;


public class FTC2018_RobotInit_robot1 {

    public Servo clipL0;
    public Servo clipL1;
    public Servo clipR0;
    public Servo clipSpare;

    public Servo ArmBase;
    public Servo ArmTop;

    public LightSensor ColourSensorL;
    //public ColorSensor ColourSensorTest;

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

    public final double clipL0open = 0.5;
    public final double clipR0open = 0.5;
    public final double clipL0close = 0;
    public final double clipR0close = 1;

    public final double clipL1open = 0.2;
    public final double clipSpareopen = 0.4;
    public final double clipL1close = 0.8;
    public final double clipSpareclose = 1;

    public final double ArmBaseBackward = 0.6;
    public final double ArmBaseCentre = 0.5;
    public final double ArmBaseForward = 0.4;
    public final double ArmTopOpen = 0.95;
    public final double ArmTopClose = 0;

    public final double clipBRfullopen = 0;
    public final double clipBLfullopen = 1;

    public double Lfronttmp = 0;
    public double Lbacktmp = 0;
    public double Rfronttmp = 0;
    public double Rbacktmp = 0;

    public double Lfrontforward = 0;
    public double Lbackforward = 0;
    public double Rfrontforward = 0;
    public double Rbackforward = 0;

    public double tmp;

    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        clipL0 = _hw.servo.get("clipL0");
        clipR0 = _hw.servo.get("clipR0");
        clipL1 = _hw.servo.get("clipL1");
        clipSpare = _hw.servo.get("clipSpare");

        ArmBase = _hw.servo.get("ArmBase");   //unuesd
        ArmTop = _hw.servo.get("ArmTop");   //unuesd

        rope = _hw.dcMotor.get("rope");
        lifting = _hw.dcMotor.get("lifting");

        Lrope = _hw.dcMotor.get("Lrope");
        Rrope = _hw.dcMotor.get("Rrope");

        Lroll = _hw.dcMotor.get("Lroll");
        Rroll = _hw.dcMotor.get("Rroll");

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");

        ColourSensorL = _hw.lightSensor.get("ColourSensorL");

        Rfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Rback.setDirection(DcMotorSimple.Direction.REVERSE);
        Rrope.setDirection(DcMotorSimple.Direction.REVERSE);
        Rroll.setDirection(DcMotorSimple.Direction.REVERSE);
        rope.setDirection(DcMotorSimple.Direction.REVERSE);
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