package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FTC2018_robotTest_Manual", group="FTC 2018")
public class FTC2018_robotTest_Manual extends OpMode{

    FTC2018_RobotInit_robotTest robot = new FTC2018_RobotInit_robotTest();

    @Override
    public void init(){
        robot.init(hardwareMap);
        //robot.left.setPower(0);
        //robot.right.setPower(0);
        robot.servoo.setPosition(0);
        robot.motorl.setPower(0);
        robot.motorr.setPower(0);
        /*robot.lefttop.setPower(0);
        robot.leftbottom.setPower(0);
        robot.righttop.setPower(0);
        robot.rightbottom.setPower(0);*/

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

        /*double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double localSpeed = 0.6;
        double speed_new = 0.2;
        double speed_old = 0.8;*/

        //robot.left.setPower(gamepad1.right_stick_y);
        robot.servoo.setPosition(gamepad1.left_stick_y);
        robot.motorl.setPower(gamepad1.left_stick_y);
        robot.motorr.setPower(gamepad1.right_stick_y);
        telemetry.addData("Running","Robot 1");
        telemetry.update();
    }

    @Override
    public void stop(){

    }
}
