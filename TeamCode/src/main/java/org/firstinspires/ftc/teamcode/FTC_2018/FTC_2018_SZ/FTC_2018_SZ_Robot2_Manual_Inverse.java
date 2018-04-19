package org.firstinspires.ftc.teamcode.FTC_2018.FTC_2018_SZ;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="FTC_2018_SZ_Robot2_Manual_Inverse", group="FTC 2018")
public class FTC_2018_SZ_Robot2_Manual_Inverse extends OpMode{

    FTC_2018_SZ_Robot2_Init robot = new FTC_2018_SZ_Robot2_Init();

    @Override
    public void init(){
        robot.init(hardwareMap);

        robot.plate.setPower(0);
        robot.lifting.setPower(0);

        robot.Lroll.setPower(0);
        robot.Rroll.setPower(0);

        robot.Lfront.setPower(0);
        robot.Rfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rback.setPower(0);

        robot.Clip1.setPosition(robot.Clip1Open);
        robot.Clip2.setPosition(robot.Clip2Open);

        robot.GolfL.setPosition(robot.GolfLOpen);
        robot.GolfR.setPosition(robot.GolfROpen);

        robot.runModeSet("encoder");

        robot.runModeSet("encoder2");
        robot.runModeSet("reset2");
        robot.runModeSet("position2");

        robot.ColorUpperL.enableLed(true);
        robot.ColorLowerL.enableLed(true);
        robot.ColorUpperR.enableLed(true);
        robot.ColorLowerR.enableLed(true);

        robot.ArmL.setPosition(robot.ArmLClose);
        robot.ArmR.setPosition(robot.ArmRClose);
    }


    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    public void Glyph_Roll() {//Current using
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double g2leftStickX = gamepad2.left_stick_x;
        double g2leftStickY = -gamepad2.left_stick_y;
        double g2rightStickX = gamepad2.right_stick_x;
        double g2rightStickY = -gamepad2.right_stick_y;

        if (g2leftStickY > 0) {
            robot.Clip1.setPosition(robot.Clip1Open);
            robot.Clip2.setPosition(robot.Clip2Open);
            robot.Lroll.setPower(1);
            robot.Rroll.setPower(1);
        }
        else if (g2leftStickY < 0) {
            robot.Clip1.setPosition(robot.Clip1Open);
            robot.Clip2.setPosition(robot.Clip2Open);
            robot.Lroll.setPower(-1);
            robot.Rroll.setPower(-1);
        }
        else if (g2rightStickX > 0) {
            robot.Clip1.setPosition(robot.Clip1Open);
            robot.Clip2.setPosition(robot.Clip2Open);
            robot.Lroll.setPower(0.6);
            robot.Rroll.setPower(-0.6);
        }
        else if (g2rightStickX < 0) {
            robot.Clip1.setPosition(robot.Clip1Open);
            robot.Clip2.setPosition(robot.Clip2Open);
            robot.Lroll.setPower(-0.6);
            robot.Rroll.setPower(0.6);
        }
        else {
            robot.Lroll.setPower(0);
            robot.Rroll.setPower(0);
        }

        if (gamepad2.dpad_up) {
            robot.lifting.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.lifting.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.lifting.setTargetPosition(-robot.lifting_encoder);
            robot.lifting.setPower(1);
        }
        else if (gamepad2.dpad_down) {
            robot.lifting.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.lifting.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.lifting.setTargetPosition(0);
            robot.lifting.setPower(1);
        }

        if (gamepad2.right_bumper) {
            robot.plate_is_flip = true;

            robot.GolfL.setPosition(robot.GolfLClose);
            robot.GolfR.setPosition(robot.GolfRClose);

            robot.Clip1.setPosition(robot.Clip1Close);
            robot.Clip2.setPosition(robot.Clip2Close);

            robot.plate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.plate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.plate.setTargetPosition(-robot.plate_encoder);
            robot.plate.setPower(1);
        }
        else if (gamepad2.left_bumper) {
            robot.plate_is_flip = false;

            robot.Clip1.setPosition(robot.Clip1Close);
            robot.Clip2.setPosition(robot.Clip2Close);

            robot.plate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.plate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.plate.setTargetPosition(0);
            robot.plate.setPower(1);
        }

        telemetry.addData("plate current",robot.plate.getCurrentPosition());
        telemetry.addData("diff",robot.plate_encoder-Math.abs(robot.plate.getCurrentPosition()));
        telemetry.addData("lifting current",robot.lifting.getCurrentPosition());
        telemetry.addData("diff",robot.lifting_encoder-Math.abs(robot.lifting.getCurrentPosition()));
    }

    public void GolfStick (){
        if (robot.toggle_Golf && gamepad1.a) {
            robot.toggle_Golf = false;
            if (robot.state_Golf) {
                robot.state_Golf = false;
                robot.GolfL.setPosition(robot.GolfLClose);
                robot.GolfR.setPosition(robot.GolfRClose);
            }
            else {
                robot.state_Golf = true;
                robot.GolfL.setPosition(robot.GolfLOpen);
                robot.GolfR.setPosition(robot.GolfROpen);
            }
        }
        else if (robot.toggle_Golf && gamepad2.a) {
            robot.toggle_Golf = false;
            if (robot.state_Golf) {
                robot.state_Golf = false;
                robot.GolfL.setPosition(robot.GolfLClose);
                robot.GolfR.setPosition(robot.GolfRClose);
            }
            else {
                robot.state_Golf = true;
                robot.GolfL.setPosition(robot.GolfLOpen);
                robot.GolfR.setPosition(robot.GolfROpen);
            }
        }
        else if (gamepad1.a == false && gamepad2.a == false) {
            robot.toggle_Golf = true;
        }
    }

    public void Clipping (){
        if (robot.toggle_Clip && gamepad1.y) {
            robot.toggle_Clip = false;
            if (robot.state_Clip) {
                robot.state_Clip = false;
                robot.Clip1.setPosition(robot.Clip1Close);
                robot.Clip2.setPosition(robot.Clip2Close);
            }
            else {
                robot.state_Clip = true;
                robot.Clip1.setPosition(robot.Clip1Open);
                robot.Clip2.setPosition(robot.Clip2Open);
            }
        }
        else if (robot.toggle_Clip && gamepad2.y) {
            robot.toggle_Clip = false;
            if (robot.state_Clip) {
                robot.state_Clip = false;
                robot.Clip1.setPosition(robot.Clip1Close);
                robot.Clip2.setPosition(robot.Clip2Close);
            }
            else {
                robot.state_Clip = true;
                robot.Clip1.setPosition(robot.Clip1Open);
                robot.Clip2.setPosition(robot.Clip2Open);
            }
        }
        else if (gamepad1.y == false && gamepad2.y == false) {
            robot.toggle_Clip = true;
        }
    }

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

        robot.Lfronttmp = RoundDownDp(leftStickY*1+rightStickX*0.7+leftStickX*0.7, 0.001);
        robot.Lbacktmp = RoundDownDp(leftStickY*1+rightStickX*0.7-leftStickX*0.7, 0.001);
        robot.Rfronttmp = RoundDownDp(leftStickY*1-rightStickX*0.7-leftStickX*0.7, 0.001);
        robot.Rbacktmp = RoundDownDp(leftStickY*1-rightStickX*0.7+leftStickX*0.7, 0.001);

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
        telemetry.addData("Plate Flipped",robot.plate_is_flip);

        if (gamepad1.right_trigger > 0) {
            robot.Lfront.setPower(0.5);
            robot.Lback.setPower(0.5);
            robot.Rfront.setPower(0.5);
            robot.Rback.setPower(0.5);
        } else if (gamepad1.left_trigger > 0) {
            robot.Lfront.setPower(-0.5);
            robot.Lback.setPower(-0.5);
            robot.Rfront.setPower(-0.5);
            robot.Rback.setPower(-0.5);
        }

        if (gamepad1.dpad_up) {
            robot.ArmL.setPosition(robot.ArmLClose);
            robot.ArmR.setPosition(robot.ArmRClose);
        }
        else if (gamepad1.dpad_down) {
            robot.ArmL.setPosition(robot.ArmLOpen);
            robot.ArmR.setPosition(robot.ArmROpen);
        }

        //Player1

        //Player2
        Glyph_Roll();
        GolfStick();
        Clipping();

        telemetry.update();
    }

    @Override
    public void stop(){

    }
}