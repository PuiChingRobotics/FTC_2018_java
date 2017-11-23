package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FTC2018_RobotInit_robot2 {
    public DcMotor peter1;
    public DcMotor peter2;
    public DcMotor peter3;
    public DcMotor peter4;
    public Servo siuku;
    /*public LightSensor John;
    public GyroSensor Johnn;*/

    public HardwareMap _hw;

    public void Init(HardwareMap hw){
        _hw = hw;
        peter1 =_hw.dcMotor.get("peter1");
        peter2 =_hw.dcMotor.get("peter2");
        peter3 = _hw.dcMotor.get("peter3");
        peter4 = _hw.dcMotor.get("peter4");
        siuku =_hw.servo.get("siuku");
        /*John = _hw.lightSensor.get("John");
        Johnn = _hw.gyroSensor.get("Johnn");*/
    }
}