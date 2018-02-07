package org.firstinspires.ftc.teamcode.FTC_2018;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FTC_2018.FTC2018_RobotInit_robot2;


@Autonomous(name="FTC2018_robot2_Auto_Red", group="2018")
public class FTC2018_robot2_Auto_Red extends LinearOpMode{

    FTC2018_RobotInit_robot2 robot = new FTC2018_RobotInit_robot2();

    final double mmTOinches = 0.0393701;
    final double diameterOfWheel = 96*mmTOinches; //set the diameter of wheel (in inches), 96mm for matrix, 4inches for tetrix
    final double circumfirence = Math.PI*diameterOfWheel;
    final int encoderValue = 1440; //1440 for tetrix, 1680 for andymark
    final double distancePerSquare = 600*mmTOinches;//set the distance per square (in inches), 600mm or 23.5inches
    final double local_speed = 0.5;
    final double distanceBetweenWheels = 17.02;
    final double distanceTurn90 = 0.25*Math.PI*distanceBetweenWheels;

    double ttmp = 1;
    String Team = "RED";

    public void initial(){
        robot.init(hardwareMap);
        robot.Lfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
        robot.runModeSet("reset");
        robot.runModeSet("position");
    }

    public void walk_forward(double N){
        setDistanceToGo(N*distancePerSquare,N*distancePerSquare,N*distancePerSquare,N*distancePerSquare,local_speed,local_speed*ttmp,local_speed,local_speed);//walk_forward for 1 square
    }

    public void walk_backward(double N){
        setDistanceToGo(-N*distancePerSquare,-N*distancePerSquare,-N*distancePerSquare,-N*distancePerSquare,local_speed,local_speed*ttmp,local_speed,local_speed);//walk_forward for 1 square
    }
    public void walk_left90(double N){
        setDistanceToGo(-N*distancePerSquare,N*distancePerSquare,N*distancePerSquare,-N*distancePerSquare,local_speed,local_speed*ttmp,local_speed,local_speed);;//Turn left 90
    }
    public void walk_right90(double N){
        setDistanceToGo(N*distancePerSquare,-N*distancePerSquare,-N*distancePerSquare,N*distancePerSquare,local_speed,local_speed*ttmp,local_speed,local_speed);//Turn right 90
    }

    public void setDistanceToGo(double Lfront_distance, double Lback_distance, double Rfront_distance, double Rback_distance, double Lfront_power, double Lback_power, double Rfront_power, double Rback_power){
        final double Lfront_rotation = (Lfront_distance/circumfirence);
        final double Lback_rotation = (Lback_distance/circumfirence);
        final double Rfront_rotation = (Rfront_distance/circumfirence);
        final double Rback_rotation = (Rback_distance/circumfirence);
        robot.runModeSet("reset");
        robot.runModeSet("position");
        double Lfront_tmp = Lfront_rotation*encoderValue;
        double Lback_tmp = Lback_rotation*encoderValue;
        double Rfront_tmp = Rfront_rotation*encoderValue;
        double Rback_tmp = Rback_rotation*encoderValue;

        robot.Lfront.setTargetPosition((int) Lfront_tmp);
        robot.Lback.setTargetPosition((int) Lback_tmp);
        robot.Rfront.setTargetPosition((int) Rfront_tmp);
        robot.Rback.setTargetPosition((int) Rback_tmp);

        robot.Lfront.setPower(Lfront_power);
        robot.Lback.setPower(Lback_power);
        robot.Rfront.setPower(Rfront_power);
        robot.Rback.setPower(Rback_power);

        while (opModeIsActive() && robot.Lfront.isBusy() && robot.Lback.isBusy() && robot.Rfront.isBusy() && robot.Rback.isBusy()) {
            telemetry.update();
        }
        robot.Lfront.setPower(0);
        robot.Lback.setPower(0);
        robot.Rfront.setPower(0);
        robot.Rback.setPower(0);
    }//in inches


    public void path(){
        walk_left90(1);
        sleep(1000);
        walk_right90(1);
        sleep(1000);
        walk_forward(1);
        sleep(1000);
        walk_backward(1);
    }

    public void auto_command(){
        path();
        telemetry.addData("Ready to check","");
        telemetry.update();
    }

    @Override
    public void runOpMode() {

        initial();

        waitForStart();

        auto_command();
    }
}