package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="FTC2018_robot1_Manual", group="FTC 2018")
public class FTC2018_robot1_Manual extends OpMode{

    FTC2018_RobotInit_robot1 robot = new FTC2018_RobotInit_robot1();

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);
        robot.rope.setPower(0);
        robot.lifting.setPower(0);
//         robot.clipL0.setPosition(0.6);
//         robot.clipR0.setPosition(0.4);
        robot.clipM1.setPower(0);
//        robot.clipM2.setPower(0);
//         robot.clipF1.setPosition(0);
//         robot.clipF2.setPosition(robot.clipStop);
//         robot.clipBL.setPosition(robot.clipBLopen);
//         robot.clipBR.setPosition(robot.clipBRopen);
//        robot.clipM2.setDirection(DcMotorSimple.Direction.REVERSE);


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
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickY = -gamepad2.right_stick_y;
        double Lfronttmp = 0;
        double Lbacktmp = 0;
        double Rfronttmp = 0;
        double Rbacktmp = 0;
        double speed_new = 0.2;
        double speed_old = 0.8;

        telemetry.addData("Running", "Robot 1");
        //player1
        //drive
        robot.Lfrontforward = robot.Lfrontforward*speed_old+leftStickY*speed_new;
        robot.Rfrontforward = robot.Rfrontforward*speed_old+leftStickY*speed_new;
        robot.Lbackforward = robot.Lbackforward*speed_old+leftStickY*speed_new;
        robot.Rbackforward = robot.Rbackforward*speed_old+leftStickY*speed_new;
        Lfronttmp = robot.Lfrontforward*0.6+leftStickX*0.6+rightStickX*0.4;
        Rfronttmp = robot.Rfrontforward*0.6-leftStickX*0.6-rightStickX*0.4;
        Lbacktmp = robot.Lbackforward*0.6-leftStickX*0.6+rightStickX*0.4;
        Rbacktmp = robot.Rbackforward*0.6+leftStickX*0.6-rightStickX*0.4;
     /*   Lfronttmp = leftStickX*0.35+leftStickY*0.35+rightStickX*0.3;
        Rfronttmp = -leftStickX*0.35+leftStickY*0.35-rightStickX*0.3;
        Lbacktmp = -leftStickX*0.35+leftStickY*0.35+rightStickX*0.3;
        Rbacktmp = leftStickX*0.35+leftStickY*0.35-rightStickX*0.3;*/
        robot.Lfront.setPower(Lfronttmp);
        robot.Lback.setPower(Lbacktmp);
        robot.Rfront.setPower(Rfronttmp);
        robot.Rback.setPower(Rbacktmp);

        //Relic
        if (gamepad1.x) {//open
            robot.clipBL.setPosition(robot.clipBLopen);
            robot.clipBR.setPosition(robot.clipBRopen);
        }
        else if (gamepad1.y) {//close
            robot.clipBL.setPosition(robot.clipBLclose);
            robot.clipBR.setPosition(robot.clipBRclose);
        }
        else if (gamepad1.b) {//fully open
            robot.clipBL.setPosition(robot.clipBLfullopen);
            robot.clipBR.setPosition(robot.clipBRfullopen);
        }

        if (gamepad1.dpad_left == true) robot.lifting.setPower(0.3);
        else if (gamepad1.dpad_right == true) robot.lifting.setPower(-0.3);
        else robot.lifting.setPower(0);

        //rope
        if (gamepad1.left_trigger != 0) robot.rope.setPower(1);
        else if (gamepad1.right_trigger != 0) robot.rope.setPower(-1);
        else robot.rope.setPower(0);
        
        /*if (gamepad1.dpad_up) robot.clipF2.setPosition(robot.clipUp);
        else if (gamepad1.dpad_down) robot.clipF2.setPosition(robot.clipDown);
        else robot.clipF2.setPosition(robot.clipStop);/*

        /*if (gamepad1.left_bumper) robot.clipF1.setPosition(1);
        else if (gamepad1.right_bumper) robot.clipF1.setPosition(0);*/
        telemetry.addData("gamepad1: ",!gamepad1.atRest());
        telemetry.addData("leftStickX: ",leftStickX);
        telemetry.addData("leftStickY: ",leftStickY);
        telemetry.addData("rightStickX: ",rightStickX);
        telemetry.addData("rightStickY: ",rightStickY);

        telemetry.addData("Lfrontforward: ",robot.Lfrontforward);
        telemetry.addData("Rfrontforward: ",robot.Rfrontforward);
        telemetry.addData("Lbackforward: ",robot.Lbackforward);
        telemetry.addData("Rbackforward: ",robot.Rbackforward);

        telemetry.addData("Lfronttmp: ",Lfronttmp);
        telemetry.addData("Rfronttmp: ",Rfronttmp);
        telemetry.addData("Lbacktmp: ",Lbacktmp);
        telemetry.addData("Rbacktmp: ",Rbacktmp);
        
        //player2
        //clip
        if (gamepad2.left_trigger != 0) robot.clipM1.setPower(0.2);
//        if (gamepad2.right_trigger != 0) robot.clipM2.setPower(0.15);

        robot.clipM1.setPower(0.5*g2leftStickY);
//        robot.clipM2.setPower(0.5*g2rightStickY);

        /*if (gamepad2.a) {//close
            robot.clipL0.setPosition(robot.clipL0close);
            robot.clipR0.setPosition(robot.clipR0close);
        }
        if (gamepad2.b) {//open
            robot.clipL0.setPosition(robot.clipL0open);
            robot.clipR0.setPosition(robot.clipR0open);
        }*/
        if (gamepad2.x) {//open
            robot.clipL1.setPosition(robot.clipL1open);
            robot.clipR1.setPosition(robot.clipR1open);
        }
        if (gamepad2.y) {//close
            robot.clipL1.setPosition(robot.clipL1close);
            robot.clipR1.setPosition(robot.clipR1close);
        }

        telemetry.addData("Rope: ",robot.rope.getPower());
        telemetry.addData("gamepad2: ",!gamepad2.atRest());
        telemetry.update();
    }

    @Override
    public void stop(){

    }
}
