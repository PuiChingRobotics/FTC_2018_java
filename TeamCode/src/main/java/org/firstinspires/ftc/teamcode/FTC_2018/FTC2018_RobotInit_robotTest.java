package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FTC2018_RobotInit_robotTest {

    /*public DcMotor lefttop;
    public DcMotor leftbottom;
    public DcMotor righttop;
    public DcMotor rightbottom;*/
    //public DcMotor left;
    //public DcMotor right;
    public Servo servoo;
    public DcMotor motorl;
    public DcMotor motorr;

    public HardwareMap _hw;

    public void init(HardwareMap hw){
        _hw = hw;

        //left = _hw.dcMotor.get("left");
        //right = _hw.dcMotor.get("right");
        servoo = _hw.servo.get("servoo");
        motorl = _hw.dcMotor.get("motorl");
        motorr = _hw.dcMotor.get("motorr");

        /*lefttop = _hw.dcMotor.get("lefttop");
        leftbottom = _hw.dcMotor.get("leftbottom");
        righttop = _hw.dcMotor.get("righttop");
        rightbottom = _hw.dcMotor.get("rightbottom");*/

    }

}
