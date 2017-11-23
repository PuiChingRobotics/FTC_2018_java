package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="FTC2018_robotDummy_Manual", group="FTC 2018")
public class FTC2018_robotDummy_Manual extends OpMode{

    FTC2018_RobotInit_robotDummy robot = new FTC2018_RobotInit_robotDummy();

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);
        robot.Lfront.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.Lback.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double Lfronttmp = 0;
        double Lbacktmp = 0;
        double Rfronttmp = 0;
        double Rbacktmp = 0;

        telemetry.addData("Running", "Robot 1");
        //player1
        //drive
        Lfronttmp = leftStickX*0.35+leftStickY*0.35+rightStickX*0.3;
        Rfronttmp = -leftStickX*0.35+leftStickY*0.35-rightStickX*0.3;
        Lbacktmp = -leftStickX*0.35+leftStickY*0.35+rightStickX*0.3;
        Rbacktmp = leftStickX*0.35+leftStickY*0.35-rightStickX*0.3;
        robot.Lfront.setPower(Lfronttmp);
        robot.Lback.setPower(Lbacktmp);
        robot.Rfront.setPower(Rfronttmp);
        robot.Rback.setPower(Rbacktmp);
        telemetry.addData("gamepad1: ",!gamepad1.atRest());
        telemetry.addData("gamepad2: ",!gamepad2.atRest());
        telemetry.update();
    }

    @Override
    public void stop(){

    }
}
