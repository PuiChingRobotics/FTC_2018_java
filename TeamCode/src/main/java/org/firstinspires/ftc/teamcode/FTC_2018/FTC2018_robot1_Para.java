package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="FTC2018_robot1_Para", group="FTC 2018")
public class FTC2018_robot1_Para extends OpMode {

    FTC2018_RobotInit_robot1 robot = new FTC2018_RobotInit_robot1();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.clipM1.setPower(0);
        robot.clipM2.setPower(0);
        robot.clipM2.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.left_trigger != 0) robot.clipM1.setPower(0.2);
        if (gamepad1.right_trigger != 0) robot.clipM2.setPower(0.15);

        if (gamepad1.left_stick_y != 0) robot.clipM1.setPower(-0.6 * gamepad1.left_stick_y);
        else robot.clipM1.setPower(0);
        if (gamepad1.right_stick_y != 0) robot.clipM2.setPower(-0.6 * gamepad1.right_stick_y);
        else robot.clipM2.setPower(0);

        if (gamepad1.a) {//close
            robot.clipL0.setPosition(0);
            robot.clipR0.setPosition(1);
        }
        if (gamepad1.b) {//open
            robot.clipL0.setPosition(0.6);
            robot.clipR0.setPosition(0.4);
        }
        if (gamepad1.x) {//open
            robot.clipL1.setPosition(0.2);
            robot.clipR1.setPosition(0.8);
        }
        if (gamepad1.y) {//close
            robot.clipL1.setPosition(1);
            robot.clipR1.setPosition(0);
        }

        telemetry.addData("Running", "Robot 1");
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}

