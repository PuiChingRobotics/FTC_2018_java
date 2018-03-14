package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_HK;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FTC_2018_HK_FinalBot_Manual", group="FTC 2018")
public class FTC_2018_HK_FinalBot_Manual extends OpMode{

    FTC_2018_HK_FinalBot_Init robot = new FTC_2018_HK_FinalBot_Init();

    double tmpp = 0;

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);

        robot.Lrope.setPower(0);
        robot.Rrope.setPower(0);


        robot.clipL.setPosition(0.2);
        robot.clipR.setPosition(0.8);

        //Blue L
        robot.ColourSensorBlue.enableLed(true);
        robot.ArmBaseBlue.setPosition(robot.ArmBaseCentreBlue);
        robot.ArmTopBlue.setPosition(robot.ArmTopCloseBlue);

        //Red R
        robot.ColourSensorRed.enableLed(true);
        robot.ArmBaseRed.setPosition(robot.ArmBaseCentreRed);
        robot.ArmTopRed.setPosition(robot.ArmTopCloseRed);

    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    /*public void Glyph_DoubleClip(){//not used
        //Glyph Clip
        if (gamepad1.a) {//open
            robot.unusedL.setPosition(0.5);
            robot.unusedR.setPosition(0.5);
        }
        if (gamepad1.b) {//close
            robot.unusedL.setPosition(0);
            robot.unusedR.setPosition(1);
        }

        if (gamepad1.x) {//open
            robot.unusedL.setPosition(0.2);
            robot.unusedR.setPosition(0.4);

        }
        if (gamepad1.y) {//close
            robot.unusedL.setPosition(0.8);
            robot.unusedR.setPosition(1);

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
    }*/

    /*public void Glyph_RobotIn30Hours() {//not used

        if (gamepad2.a) {//open
            robot.unusedL.setPosition(0.6);//1
            robot.unusedR.setPosition(0.4);//0
        }
        else if (gamepad2.b) {//close
            robot.unusedL.setPosition(0);//0.1
            robot.unusedR.setPosition(1);//0.9
        }
        else if (gamepad2.x) {//half-open
            robot.unusedL.setPosition(0.2);//0.2
            robot.unusedR.setPosition(0.8);//0.8
        }
        else if (gamepad2.y) {//fully-close
            robot.unusedL.setPosition(1);
            robot.unusedR.setPosition(0);
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
    }*/

    public void Glyph_Roll() {//Current using
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        if (gamepad2.a) {//open
            robot.clipL.setPosition(0.7);//1
            robot.clipR.setPosition(0.3);//0
        }
        else if (gamepad2.b) {//close
            robot.clipL.setPosition(0);//0.1
            robot.clipR.setPosition(1);//0.9
        }
        else if (gamepad2.x) {//half-open
            robot.clipL.setPosition(0.3);//0.2
            robot.clipR.setPosition(0.7);//0.8
        }
        else if (gamepad2.y) {//fully-open
            robot.clipL.setPosition(1);
            robot.clipR.setPosition(0);
        }

        robot.Rolltmp = g2leftStickX;
        if (robot.Rolltmp < 0) {
            robot.Rolltmp = robot.Rolltmp*0.7;
        }

        robot.Rroll.setPower(robot.Rolltmp);
        robot.Lroll.setPower(robot.Rolltmp);

        if (Math.abs(g2rightStickY) < 0.2) {
            if (gamepad1.dpad_up) {
                robot.Lrope.setPower(-1);
                robot.Rrope.setPower(-1);
            } else if (gamepad1.dpad_down) {
                robot.Lrope.setPower(1);
                robot.Rrope.setPower(1);
            }
            else {
                robot.Lrope.setPower(0);
                robot.Rrope.setPower(0);
            }
        }
        else {
            robot.Lrope.setPower(-g2rightStickY);
            robot.Rrope.setPower(-g2rightStickY);
        }
    }

    /*public void Relic_Ruler() {//not used
        if (gamepad2.a) {//close
            robot.unusedL.setPosition(0);
            robot.unusedR.setPosition(0);
        }
        else if (gamepad2.b) {//open
            robot.unusedL.setPosition(0);
            robot.unusedR.setPosition(0);
        }
        else if (gamepad2.x) {//fully open
            robot.unusedL.setPosition(1);
            robot.unusedR.setPosition(0);
        }

        robot.Lrope.setPower(-gamepad2.left_stick_y);
        robot.Rrope.setPower(-gamepad2.right_stick_y);
    }*/

    /*public void Relic_Arm() {//not used
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;
        if (gamepad2.right_bumper) {//open
            robot.unusedClip.setPosition(1);
        }
        if (gamepad2.left_bumper) {//close
            robot.unusedClip.setPosition(0.6);
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
    }*/

    /*public void Relic_Z() {//not used
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        telemetry.addData("Power",robot.lifting.getPower());

        if (gamepad2.right_bumper) {//close
            robot.unusedClip.setPosition(0.8);
        }
        if (gamepad2.left_bumper) {//open
            robot.unusedClip.setPosition(0.2);
        }
        robot.lifting.setPower(g2rightStickY*0.8);
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
        else robot.rope.setPower(g2leftStickY*0.3);
    }*/

    public double RoundDownDp(double value, double place){
        double result = value / place;
        result = Math.floor(result)*place;
        return result;
    }

    @Override
    public void loop() {

        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        double speed_new = 1-0.7;
        double speed_old = 0.7;

        telemetry.addData("Running", "Robot 1");
        //player1

        //drive
        robot.Lfrontforward = RoundDownDp(robot.Lfrontforward*speed_old+leftStickY*speed_new, 0.001);
        robot.Rfrontforward = RoundDownDp(robot.Rfrontforward*speed_old+leftStickY*speed_new, 0.001);
        robot.Lbackforward = RoundDownDp(robot.Lbackforward*speed_old+leftStickY*speed_new, 0.001);
        robot.Rbackforward = RoundDownDp(robot.Rbackforward*speed_old+leftStickY*speed_new, 0.001);

        robot.Lfronttmp = RoundDownDp(leftStickY*1+rightStickX*1, 0.001);
        robot.Rfronttmp = RoundDownDp(leftStickY*1-rightStickX*1, 0.001);
        robot.Lbacktmp = RoundDownDp(leftStickY*1+rightStickX*1, 0.001);
        robot.Rbacktmp = RoundDownDp(leftStickY*1-rightStickX*1, 0.001);

        robot.Lfront.setPower(robot.Lfronttmp);
        robot.Lback.setPower(robot.Lbacktmp);
        robot.Rfront.setPower(robot.Rfronttmp);
        robot.Rback.setPower(robot.Rbacktmp);

        telemetry.addData("gamepad1", !gamepad1.atRest());
        telemetry.addData("leftStickX", leftStickX);
        telemetry.addData("leftStickY", leftStickY);
        telemetry.addData("rightStickX", rightStickX);
        telemetry.addData("rightStickY", rightStickY);

        telemetry.addData("Lfrontforward", robot.Lfrontforward);
        telemetry.addData("Rfrontforward", robot.Rfrontforward);
        telemetry.addData("Lbackforward", robot.Lbackforward);
        telemetry.addData("Rbackforward", robot.Rbackforward);

        telemetry.addData("Lfronttmp", robot.Lfronttmp);
        telemetry.addData("Rfronttmp", robot.Rfronttmp);
        telemetry.addData("Lbacktmp", robot.Lbacktmp);
        telemetry.addData("Rbacktmp", robot.Rbacktmp);

        telemetry.addData("SensorBlue", robot.ColourSensorBlue.getLightDetected());
        telemetry.addData("ArmBaseBlue", robot.ArmBaseBlue.getPosition());
        telemetry.addData("ArmTopBlue", robot.ArmTopBlue.getPosition());

        telemetry.addData("SensorRed", robot.ColourSensorRed.getLightDetected());
        telemetry.addData("ArmBaseRed", robot.ArmBaseRed.getPosition());
        telemetry.addData("ArmTopRed", robot.ArmTopRed.getPosition());

        if (gamepad1.a) {
            //Blue L
            robot.ArmBaseBlue.setPosition(robot.ArmBaseCentreBlue);
            robot.ArmTopBlue.setPosition(robot.ArmTopCloseBlue);

            //Red R
            robot.ArmBaseRed.setPosition(robot.ArmBaseCentreRed);
            robot.ArmTopRed.setPosition(robot.ArmTopCloseRed);
        }
        else if (gamepad1.b) {
            //Blue L
            //robot.ArmBaseBlue.setPosition(robot.ArmBaseForwardBlue);
            robot.ArmTopBlue.setPosition(robot.ArmTopOpen2Blue);

            //Red R
            //robot.ArmBaseRed.setPosition(robot.ArmBaseForwardRed);
            robot.ArmTopRed.setPosition(robot.ArmTopOpen2Red);
        }

        if (gamepad1.right_trigger > 0) {
            robot.Lfront.setPower(0.3);
            robot.Lback.setPower(0.3);
            robot.Rfront.setPower(0.3);
            robot.Rback.setPower(0.3);
        } else if (gamepad1.left_trigger > 0) {
            robot.Lfront.setPower(-0.3);
            robot.Lback.setPower(-0.3);
            robot.Rfront.setPower(-0.3);
            robot.Rback.setPower(-0.3);
        }
        //write something to push
        //Player1
        //Glyph_DoubleClip();
        //Relic_Arm();

        //Player2
        Glyph_Roll();
        //Glyph_RobotIn30Hours();
        //Relic_Z();
        //Relic_Ruler();
        telemetry.update();
    }
    @Override
    public void stop(){

    }
}