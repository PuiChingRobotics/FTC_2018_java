package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

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

        robot.Lrope.setPower(0);
        robot.Rrope.setPower(0);

        // robot.clipL0.setPosition(0.6);
        // robot.clipR0.setPosition(0.4);

        // robot.clipBL.setPosition(robot.clipBLopen);
        // robot.clipBR.setPosition(robot.clipBRopen);

    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    public void Glyph_DoubleClip(){
        //Glyph Clip
        if (gamepad1.a) {//open
            robot.clipL0.setPosition(robot.clipL0open);
            robot.clipR0.setPosition(robot.clipR0open);
        }
        if (gamepad1.b) {//close
            robot.clipL0.setPosition(robot.clipL0close);
            robot.clipR0.setPosition(robot.clipR0close);
        }

        if (gamepad1.x) {//open
            robot.clipL1.setPosition(robot.clipL1open);
            robot.clipR1.setPosition(robot.clipR1open);
        }
        if (gamepad1.y) {//close
            robot.clipL1.setPosition(robot.clipL1close);
            robot.clipR1.setPosition(robot.clipR1close);
        }

        if (gamepad1.dpad_up == true) {
            robot.Lrope.setPower(1);
            robot.Rrope.setPower(-0.2);
        }
        else if (gamepad1.dpad_down == true) {
            robot.Lrope.setPower(-1);
            robot.Rrope.setPower(0.8);
        }
        else {
            if (gamepad1.left_trigger != 0) robot.Lrope.setPower(-0.7);              //down  //back motor
            else if (gamepad1.right_trigger != 0) robot.Lrope.setPower(0.7);         //up     //back motor
            else robot.Lrope.setPower(0);

            if (gamepad1.left_bumper == true) robot.Rrope.setPower(-0.7);          //down  //front motor
            else if (gamepad1.right_bumper == true) robot.Rrope.setPower(0.7);   //up    //front motor
            else robot.Rrope.setPower(0);
        }
    }

    public void Glyph_Roll() {

        if (gamepad2.a) {//close
            robot.clipL0.setPosition(0.8);//1
            robot.clipR0.setPosition(0.2);//0
        }
        else if (gamepad2.b) {//open
            robot.clipL0.setPosition(0.1);//0.1
            robot.clipR0.setPosition(0.9);//0.9
        }
        else if (gamepad2.x) {//half-open
            robot.clipL0.setPosition(0.3);//0.2
            robot.clipR0.setPosition(0.7);//0.8
        }

        robot.lifting.setPower(gamepad2.left_stick_y);
        robot.rope.setPower(-gamepad2.left_stick_y);

        robot.Lrope.setPower(-gamepad2.right_stick_y);
        robot.Rrope.setPower(-gamepad2.right_stick_y);
    }

    public void Relic_Ruler() {//not used
        if (gamepad2.a) {//close
            robot.clipBL.setPosition(robot.clipBLclose);
            robot.clipBR.setPosition(robot.clipBRclose);
        }
        else if (gamepad2.b) {//open
            robot.clipBL.setPosition(robot.clipBLopen);
            robot.clipBR.setPosition(robot.clipBRopen);
        }
        else if (gamepad2.x) {//fully open
            robot.clipBL.setPosition(robot.clipBLfullopen);
            robot.clipBR.setPosition(robot.clipBRfullopen);
        }

        robot.Lrope.setPower(-gamepad2.left_stick_y);
        robot.Rrope.setPower(-gamepad2.right_stick_y);
    }

    public void Relic_Dennis() {
        if (gamepad2.x) {//open
            robot.clipL0.setPosition(robot.clipL1open);
        }
        if (gamepad2.y) {//close
            robot.clipL0.setPosition(robot.clipL1close);
        }
        robot.Lrope.setPower(-gamepad2.left_stick_y);
        robot.Rrope.setPower(-gamepad2.right_stick_y);
        if (gamepad2.dpad_up) {
            robot.lifting.setPower(0.3);
        }
        else if (gamepad2.dpad_down) {
            robot.lifting.setPower(-0.3);
        }
        else if (gamepad2.left_trigger > 0) {
            robot.lifting.setPower(-0.1);
        }
        else if (gamepad2.right_trigger > 0) {
            robot.lifting.setPower(0.05);
        }
        else {
            robot.lifting.setPower(0);
        }
    }

    public void Relic_Z() {
        robot.lifting.setPower(-gamepad2.left_stick_y);

        if (gamepad2.x) {//open
            robot.clipBR.setPosition(robot.clipL1open);
        }
        if (gamepad2.y) {//close
            robot.clipBR.setPosition(robot.clipL1close);
        }
        if (gamepad2.right_trigger > 0) {
            robot.clipBL.setPosition(0.6);
        }
        else robot.clipBL.setPosition(Range.clip(-gamepad2.right_stick_y+robot.clipStop,robot.clipDown,robot.clipUp));
    }

    public double RoundDown3dp(double value){
        return Math.round(value*1000)/1000;
    }

    @Override
    public void loop(){

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        double speed_new = 0.2;
        double speed_old = 0.8;

        telemetry.addData("Running", "Robot 1");
        //player1
        //drive

        robot.Lfrontforward = RoundDown3dp(robot.Lfrontforward*speed_old+leftStickY*speed_new);
        robot.Rfrontforward = RoundDown3dp(robot.Rfrontforward*speed_old+leftStickY*speed_new);
        robot.Lbackforward = RoundDown3dp(robot.Lbackforward*speed_old+leftStickY*speed_new);
        robot.Rbackforward = RoundDown3dp(robot.Rbackforward*speed_old+leftStickY*speed_new);

        robot.Lfronttmp = RoundDown3dp(robot.Lfrontforward*0.5+leftStickX*0.8+rightStickX*0.5);
        robot.Rfronttmp = RoundDown3dp(robot.Rfrontforward*0.5-leftStickX*0.8-rightStickX*0.5);
        robot.Lbacktmp = RoundDown3dp(robot.Lbackforward*0.5-leftStickX*0.8+rightStickX*0.5);
        robot.Rbacktmp = RoundDown3dp(robot.Rbackforward*0.5+leftStickX*0.8-rightStickX*0.5);

        robot.Lfront.setPower(robot.Lfronttmp);
        robot.Lback.setPower(robot.Lbacktmp);
        robot.Rfront.setPower(robot.Rfronttmp);
        robot.Rback.setPower(robot.Rbacktmp);

        telemetry.addData("gamepad1: ",!gamepad1.atRest());
        telemetry.addData("leftStickX: ",leftStickX);
        telemetry.addData("leftStickY: ",leftStickY);
        telemetry.addData("rightStickX: ",rightStickX);
        telemetry.addData("rightStickY: ",rightStickY);

        telemetry.addData("Lfrontforward: ",robot.Lfrontforward);
        telemetry.addData("Rfrontforward: ",robot.Rfrontforward);
        telemetry.addData("Lbackforward: ",robot.Lbackforward);
        telemetry.addData("Rbackforward: ",robot.Rbackforward);

        telemetry.addData("Lfronttmp: ",robot.Lfronttmp);
        telemetry.addData("Rfronttmp: ",robot.Rfronttmp);
        telemetry.addData("Lbacktmp: ",robot.Lbacktmp);
        telemetry.addData("Rbacktmp: ",robot.Rbacktmp);

        Glyph_DoubleClip();
        //Glyph_Roll();

        //player2
        Relic_Z();
        //Relic_Dennis();
        //Relic_Ruler();

        telemetry.addData("gamepad2: ",!gamepad2.atRest());
        telemetry.update();
    }

    @Override
    public void stop(){

    }
}
