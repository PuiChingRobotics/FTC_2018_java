package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="FTC2018_robot2_Manual", group="FTC 2018")
public class FTC2018_robot2_Manual extends OpMode{

    FTC2018_RobotInit_robot2 robot = new FTC2018_RobotInit_robot2();

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.peter1.setPower(0);
    }

}