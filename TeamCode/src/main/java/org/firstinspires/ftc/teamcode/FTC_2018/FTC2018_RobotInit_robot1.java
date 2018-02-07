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
    public Servo clipR1;

    public Servo LArm;
    public Servo RArm;

    public LightSensor ColourSensorL;

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
    public final double clipR1open = 0.4;
    public final double clipL1close = 0.8;
    public final double clipR1close = 1;

    public final double LArmOpen = 1;
    public final double LArmClose = 0;
    public final double clipBLfullopen = 1;
    public final double RArmOpen = 0.1;
    public final double RArmClose = 1;
    public final double clipBRfullopen = 0;

    public double Lfronttmp = 0;
    public double Lbacktmp = 0;
    public double Rfronttmp = 0;
    public double Rbacktmp = 0;

    public double Lfrontforward = 0;
    public double Lbackforward = 0;
    public double Rfrontforward = 0;
    public double Rbackforward = 0;

    public double tmp;
    //
    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        clipL0 = _hw.servo.get("clipL0");
        clipR0 = _hw.servo.get("clipR0");
        clipL1 = _hw.servo.get("clipL1");
        clipR1 = _hw.servo.get("clipR1");

        LArm = _hw.servo.get("LArm");   //unuesd
        RArm = _hw.servo.get("RArm");   //unuesd

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

}