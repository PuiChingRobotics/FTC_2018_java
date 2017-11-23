package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FTC2018_RobotInit_robot1 {

    public Servo clipL0;
    public Servo clipR0;
    public Servo clipL1;
    public Servo clipR1;
    public Servo clipF1;
    public Servo clipF2;
    public Servo clipBL;
    public Servo clipBR;
    public DcMotor rope;
    public DcMotor Lfront;
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;
    public DcMotor clipM1;
    public DcMotor clipM2;
    public DcMotor lifting;
    public DcMotor Lruler;
    public DcMotor Rruler;
    public double cnt = 0;
    public final double clipDown = 1;
    public final double clipUp = 0;
    public final double clipStop = 0.52;
    public final double clipL0open = 0.6;
    public final double clipR0open = 0.4;
    public final double clipL0close = 0;
    public final double clipR0close = 1;
    public final double clipL1open = 1;
    public final double clipR1open = 0;
    public final double clipL1close = 0.2;
    public final double clipR1close = 0.8;
    public final double clipBLopen = 0.03;
    public final double clipBLclose = 0;
    public final double clipBLfullopen = 0.5;
    public final double clipBRopen = 0.97;
    public final double clipBRclose = 1;
    public final double clipBRfullopen = 0.5;

    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        clipL0 = _hw.servo.get("clipL0");
        clipR0 = _hw.servo.get("clipR0");
        clipL1 = _hw.servo.get("clipL1");
        clipR1 = _hw.servo.get("clipR1");
        //clipF1 = _hw.servo.get("clipF1");
        //clipF2 = _hw.servo.get("clipF2");
        clipBL = _hw.servo.get("clipBL");
        clipBR = _hw.servo.get("clipBR");
        rope = _hw.dcMotor.get("rope");
        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");
        clipM1 = _hw.dcMotor.get("clipM1");
        clipM2 = _hw.dcMotor.get("clipM2");
        lifting = _hw.dcMotor.get("lifting");
        //Lruler = _hw.dcMotor.get("Lruler");
        //Rruler = _hw.dcMotor.get("Rruler");

    }

}
