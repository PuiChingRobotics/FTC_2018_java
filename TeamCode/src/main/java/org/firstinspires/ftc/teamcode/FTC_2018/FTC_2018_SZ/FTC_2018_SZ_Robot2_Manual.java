package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_SZ;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FTC_2018_SZ_Robot2_Manual", group="FTC 2018")
public class FTC_2018_SZ_Robot2_Manual extends OpMode{

    FTC_2018_SZ_Robot2_Init robot = new FTC_2018_SZ_Robot2_Init();

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);

        /*robot.Lrope.setPower(0);
        robot.Rrope.setPower(0);

        robot.clipL.setPosition(0.2);
        robot.clipR.setPosition(0.8);*/

        //Blue L
        robot.ColourSensor0.enableLed(true);
        robot.ArmBase.setPosition(robot.ArmBaseCentre);
        robot.ArmTop.setPosition(robot.ArmTopClose);
    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    /*public void Glyph_Roll() {//Current using
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

        robot.Lfronttmp = RoundDownDp(leftStickY*0.6+rightStickX*0.3-leftStickX*0.6, 0.001);
        robot.Lbacktmp = RoundDownDp(leftStickY*0.6+rightStickX*0.3+leftStickX*0.6, 0.001);
        robot.Rfronttmp = RoundDownDp(leftStickY*0.6-rightStickX*0.3+leftStickX*0.6, 0.001);
        robot.Rbacktmp = RoundDownDp(leftStickY*0.6-rightStickX*0.3-leftStickX*0.6, 0.001);

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

        telemetry.addData("SensorArgb", robot.ColourSensor0.argb());
        telemetry.addData("SensorBlue", robot.ColourSensor0.blue());
        telemetry.addData("SensorRed", robot.ColourSensor0.red());
        telemetry.addData("ArmBaseRed", robot.ArmBase.getPosition());
        telemetry.addData("ArmTopRed", robot.ArmTop.getPosition());

        if (gamepad1.a) {
            robot.ArmBase.setPosition(robot.ArmBaseCentre);
            robot.ArmTop.setPosition(robot.ArmTopClose);
        }
        else if (gamepad1.b) {
            robot.ArmBase.setPosition(robot.ArmBaseForward);
            robot.ArmTop.setPosition(robot.ArmTopOpenUpper);
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
        //Glyph_Roll();
        //Glyph_RobotIn30Hours();
        //Relic_Z();
        //Relic_Ruler();
        telemetry.update();
    }
    @Override
    public void stop(){

    }
}