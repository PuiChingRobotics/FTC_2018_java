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


        robot.clipL0.setPosition(0.2);//0.2
        robot.clipR0.setPosition(0.8);//0.8

        robot.ColourSensorL.enableLed(true);
        robot.ColourSensorR.enableLed(true);

        //robot.ArmBase.setPosition(robot.ArmBaseCentreRed);
        //robot.ArmTop.setPosition(robot.ArmTopCloseRed);

    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    public void Glyph_DoubleClip(){//not used
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
            robot.clipSpare.setPosition(robot.clipSpareopen);}
        if (gamepad1.y) {//close
            robot.clipL1.setPosition(robot.clipL1close);
            robot.clipSpare.setPosition(robot.clipSpareclose);}

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

    public void Glyph_RobotIn30Hours() {

        if (gamepad2.a) {//open
            robot.clipL0.setPosition(0.6);//1
            robot.clipR0.setPosition(0.4);//0
        }
        else if (gamepad2.b) {//close
            robot.clipL0.setPosition(0);//0.1
            robot.clipR0.setPosition(1);//0.9
        }
        else if (gamepad2.x) {//half-open
            robot.clipL0.setPosition(0.2);//0.2
            robot.clipR0.setPosition(0.8);//0.8
        }
        else if (gamepad2.y) {//fully-close
            robot.clipL0.setPosition(1);
            robot.clipR0.setPosition(0);
        }

        if (gamepad2.dpad_up) {//lifting up
            robot.Lrope.setPower(-0.5);
            robot.Rrope.setPower(-0.5);
        }
        else if (gamepad2.dpad_down) {//lifting down
            robot.Lrope.setPower(0.5);
            robot.Rrope.setPower(0.5);
        }
        else {
            robot.Lrope.setPower(0);
            robot.Rrope.setPower(0);
        }
    }

    public void Glyph_Roll() {
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        if (gamepad2.a) {//open
            robot.clipL0.setPosition(0.6);//1
            robot.clipR0.setPosition(0.4);//0
        }
        else if (gamepad2.b) {//close
            robot.clipL0.setPosition(0);//0.1
            robot.clipR0.setPosition(1);//0.9
        }
        else if (gamepad2.x) {//half-open
            robot.clipL0.setPosition(0.3);//0.2
            robot.clipR0.setPosition(0.7);//0.8
        }
        else if (gamepad2.y) {//fully-close
            robot.clipL0.setPosition(1);
            robot.clipR0.setPosition(0);
        }

        robot.Rroll.setPower(g2rightStickX);
        robot.Lroll.setPower(g2rightStickX);

        robot.Lrope.setPower(-g2leftStickY);
        robot.Rrope.setPower(-g2leftStickY);

        /*if (gamepad2.dpad_right){//In
            robot.Lroll.setPower(1);
            robot.Rroll.setPower(1);
        }
        else if (gamepad2.dpad_left){//Out
            robot.Lroll.setPower(-1);
            robot.Rroll.setPower(-1);
        }
        else {
            robot.Lroll.setPower(0);
            robot.Rroll.setPower(0);
        }

        if (gamepad2.dpad_up) {//lifting up
            robot.Lrope.setPower(-0.5);
            robot.Rrope.setPower(-0.5);
        }
        else if (gamepad2.dpad_down) {//lifting down
            robot.Lrope.setPower(0.5);
            robot.Rrope.setPower(0.5);
        }
        else {
            robot.Lrope.setPower(0);
            robot.Rrope.setPower(0);
        }*/
    }

    public void Relic_Ruler() {//not used
        if (gamepad2.a) {//close
            robot.ArmBase.setPosition(0);
            robot.ArmTop.setPosition(0);
        }
        else if (gamepad2.b) {//open
            robot.ArmBase.setPosition(0);
            robot.ArmTop.setPosition(0);
        }
        else if (gamepad2.x) {//fully open
            robot.ArmBase.setPosition(robot.clipBLfullopen);
            robot.ArmTop.setPosition(robot.clipBRfullopen);
        }

        robot.Lrope.setPower(-gamepad2.left_stick_y);
        robot.Rrope.setPower(-gamepad2.right_stick_y);
    }

    public void Relic_Arm() {
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;
        if (gamepad2.right_bumper) {//open
            robot.clipL1.setPosition(1);
        }
        if (gamepad2.left_bumper) {//close
            robot.clipL1.setPosition(0.6);
        }

        robot.rope.setPower(g2leftStickY);

        if (gamepad2.right_trigger > 0) {
            robot.lifting.setPower(0.1);
        } else if (gamepad2.left_trigger > 0) {
            robot.lifting.setPower(-0.05);
        }
        else {
            robot.lifting.setPower(g2rightStickY*0.35);
        }
    }

    public void Relic_Z() {
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        telemetry.addData("robot.tmp",robot.tmp);
        telemetry.addData("Power",robot.lifting.getPower());

        if (gamepad2.right_bumper) {//close
            robot.clipL1.setPosition(robot.clipL1close);
        }
        if (gamepad2.left_bumper) {//open
            robot.clipL1.setPosition(robot.clipL1open);
        }
        robot.lifting.setPower(g2rightStickY*.8);
        if (gamepad1.dpad_up) {
            robot.rope.setPower(0.3);
        }
        else if (gamepad1.dpad_right) {
            robot.rope.setPower(0.1);
        }
        else if (gamepad1.dpad_down) {
            robot.rope.setPower(-0.3);
        }
        else if (gamepad1.dpad_left) {
            robot.rope.setPower(-0.1);
        }
        else robot.rope.setPower(g2leftStickY*.3);

        if (gamepad1.left_trigger > 0) {

        }
    }

    public double RoundDownDp(double value, double place){
        double result = value / place;
        result = Math.floor(result)*place;
        return result;
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
        double speed_old = 0.8;//

        telemetry.addData("Running", "Robot 1");
        //player1
        //drive

        robot.Lfrontforward = RoundDownDp(robot.Lfrontforward*speed_old+leftStickY*speed_new,0.001);
        robot.Rfrontforward = RoundDownDp(robot.Rfrontforward*speed_old+leftStickY*speed_new,0.001);
        robot.Lbackforward = RoundDownDp(robot.Lbackforward*speed_old+leftStickY*speed_new,0.001);
        robot.Rbackforward = RoundDownDp(robot.Rbackforward*speed_old+leftStickY*speed_new,0.001);

        robot.Lfronttmp = RoundDownDp(robot.Lfrontforward*0.7+leftStickX*1+rightStickX*0.7,0.001);
        robot.Rfronttmp = RoundDownDp(robot.Rfrontforward*0.7-leftStickX*1-rightStickX*0.7,0.001);
        robot.Lbacktmp = RoundDownDp(robot.Lbackforward*0.7-leftStickX*1+rightStickX*0.7,0.001);
        robot.Rbacktmp = RoundDownDp(robot.Rbackforward*0.7+leftStickX*1-rightStickX*0.7,0.001);

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

        telemetry.addData("ArmBase: ",robot.ArmBase.getPosition());
        telemetry.addData("ArmTop: ",robot.ArmTop.getPosition());

        telemetry.addData("LSensor: ",robot.ColourSensorL.getLightDetected());
        telemetry.addData("RSensor: ",robot.ColourSensorR.getLightDetected());

        if (gamepad1.back) {
            robot.ArmBase.setPosition(robot.ArmBaseCentreRed);
            robot.ArmTop.setPosition(robot.ArmTopCloseRed);
        }

        if (gamepad2.back) {
            robot.ArmBase.setPosition(robot.ArmBaseCentreBlue);
            robot.ArmTop.setPosition(robot.ArmTopCloseBlue);
        }

        if (gamepad1.right_trigger > 0) {
            robot.Lfront.setPower(0.3);
            robot.Lback.setPower(0.3);
            robot.Rfront.setPower(0.3);
            robot.Rback.setPower(0.3);
        }
        else if (gamepad1.left_trigger > 0) {
            robot.Lfront.setPower(-0.3);
            robot.Lback.setPower(-0.3);
            robot.Rfront.setPower(-0.3);
            robot.Rback.setPower(-0.3);
        }

        //Player1
        //Glyph_DoubleClip();\
        //Relic_Arm();

        //Player2
        //Glyph_RobotIn30Hours();
        //Relic_Z();
        Glyph_Roll();
        //Relic_Ruler();\
        telemetry.update();
    }

    @Override
    public void stop(){

    }
}
