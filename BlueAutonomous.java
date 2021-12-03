

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BlueAutonomous", group="Linear Opmode")

public class BlueAutonomous extends LinearOpMode {

    private DcMotor frontright = null;
    private DcMotor backright = null;
    private DcMotor frontleft = null;
    private DcMotor backleft = null;
    // todo: write your code here
    private int leftPos; 
    private int rightPos;
    private DcMotor duckMotor =null;
    
    private double pulses= 537;
    private double diamter = 4.724;
        
    @Override
    public void runOpMode(){
        frontleft  = hardwareMap.get(DcMotor.class,"frontleft");
        backleft = hardwareMap.get(DcMotor.class,"backleft");
        frontright  = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        
        duckMotor = hardwareMap.get(DcMotor.class, "m3");
        
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        frontleft.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.FORWARD);
        frontright.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.REVERSE);
        duckMotor.setDirection(DcMotor.Direction.REVERSE);   
        
        leftPos = 0;
        rightPos =0;
        
        waitForStart();
        //drive(537,537,0.50);
        
    
        
        // Robot 1 Sequence
           // goForward(48);
           // turnRight(90);
           // goForward(-87);
           // turnRight(94);
            //goForward(46);
           // duckMotor.setPower(.5);
           // sleep(1000);
            
            
        // Robot 1 Sequence
            goForward(14);
            turnRight(90);
            goForward(20);
            turnRight(60);
            goForward(13);
            duckMotor.setPower(.3);
            sleep(2400);
            goForward(-18);
            turnRight(113);
            goForward(105);
            
        
    
        
  


    }
    // This loop help the robot to wait.
    
    private void pause(double milliseconds) {
       double t; 
       t=getRuntime();
       while (getRuntime()- t > milliseconds);
      
    }
    
    private void goForward (double distance) {
        
            //This will tell to the driver hub whats the robot is doing.
        telemetry.addData("goForward",distance);
        telemetry.update();
     int d = (int) ((distance-3) *((pulses/Math.PI)/diamter));
     drive(d,d,0.50);
    }
    
    
    private void turnLeft(int degree)
    {
            //This will tell to the driver hub whats the robot is doing.
        telemetry.addData("turningLeft", degree);
        telemetry.update();
       sleep(500);

  //  int distance;    
    int distance =degree * (degree/16);

    drive(-(int)distance,(int)distance,0.25);
        sleep(500);
 
    }    
    
    private void turnRight(int degree)
    {
            //This will tell to the driver hub whats the robot is doing.
        telemetry.addData("turningRight", degree);
        telemetry.update();
       sleep(500);

  //  int distance;    
    int distance =degree * (degree/16);

    drive((int)distance,-(int)distance,0.25);
        sleep(500);
 
    }
    private void drive(int leftTarget, int rightTarget, double speed){
        leftPos += leftTarget;
        rightPos+= rightTarget;
        
        //setting distance target 
        backright.setTargetPosition(rightPos);
        backleft.setTargetPosition(leftPos);
        frontright.setTargetPosition(rightPos);
        frontleft.setTargetPosition(leftPos);
        
        // moving motors to distance target
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.getCurrentPosition();
        
        
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //This will tell to the driver hub whats the robot is doing.
        telemetry.addData("backRight", backright.getCurrentPosition());
        telemetry.addData("Rtarget", rightTarget);
        telemetry.addData("Rposition", rightPos);
        
        telemetry.addData("backLeft", backleft.getCurrentPosition());
        telemetry.addData("Ltarget", leftTarget);
        telemetry.addData("Rposition", leftPos);
              
        telemetry.update();
      
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //setting the speed of the motors
        backleft.setPower(speed);
        backright.setPower(speed);
        frontleft.setPower(speed);
        frontright.setPower(speed);

        while(opModeIsActive()&& frontleft.isBusy() && frontright.isBusy()){
            idle(); }
            
    }        
}
