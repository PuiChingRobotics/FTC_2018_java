package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FTC2018_RobotInit_robot1 {

    public Servo clipL0;
    public Servo clipL1;
    public Servo clipR0;
    public Servo clipR1;

    public Servo clipBL;
    public Servo clipBR;

    public DcMotor rope;
    public DcMotor lifting;
    public DcMotor clipM1;
    // public DcMotor Lruler;
    // public DcMotor Rruler;

    public DcMotor Lfront;
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;

    public final double clipDown = 1;   //continuous servo value
    public final double clipUp = 0;     //continuous servo value
    public final double clipStop = 0.52;//continuous servo value

    public final double clipL0open = 1;
    public final double clipR0open = 0;
    public final double clipL0close = 0;
    public final double clipR0close = 1;

    public final double clipL1open = 0;
    public final double clipR1open = 1;
    public final double clipL1close = 1;
    public final double clipR1close = 0;

    public final double clipBLopen = 0.03;      //unuesd
    public final double clipBLclose = 0;        //unuesd
    public final double clipBLfullopen = 0.5;   //unuesd
    public final double clipBRopen = 0.97;      //unuesd
    public final double clipBRclose = 1;        //unuesd
    public final double clipBRfullopen = 0.5;   //unuesd

    public double Lfronttmp = 0;
    public double Lbacktmp = 0;
    public double Rfronttmp = 0;
    public double Rbacktmp = 0;

    public double Lfrontforward = 0;
    public double Lbackforward = 0;
    public double Rfrontforward = 0;
    public double Rbackforward = 0;

    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        clipL0 = _hw.servo.get("clipL0");
        clipR0 = _hw.servo.get("clipR0");
        clipL1 = _hw.servo.get("clipL1");
        clipR1 = _hw.servo.get("clipR1");

        clipBL = _hw.servo.get("clipBL");   //unuesd
        clipBR = _hw.servo.get("clipBR");   //unuesd

        rope = _hw.dcMotor.get("rope");
        lifting = _hw.dcMotor.get("lifting");
        clipM1 = _hw.dcMotor.get("clipM1");

        //Lruler = _hw.dcMotor.get("Lruler");
        //Rruler = _hw.dcMotor.get("Rruler");

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");



        Lfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Lback.setDirection(DcMotorSimple.Direction.REVERSE);

    }

}
