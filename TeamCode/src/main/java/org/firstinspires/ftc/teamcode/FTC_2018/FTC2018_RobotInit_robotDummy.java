package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FTC2018_RobotInit_robotDummy {

    public DcMotor Lfront;  //test
    public DcMotor Rfront;
    public DcMotor Lback;
    public DcMotor Rback;
//    public Servo ball_kicker;

    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        Lfront = _hw.dcMotor.get("Lfront");
        Rfront = _hw.dcMotor.get("Rfront");
        Lback = _hw.dcMotor.get("Lback");
        Rback = _hw.dcMotor.get("Rback");
        Lfront.setDirection(DcMotorSimple.Direction.REVERSE);
        Lback.setDirection(DcMotorSimple.Direction.REVERSE);
//        ball_kicker = _hw.servo.get("ball_kicker");

    }

}
