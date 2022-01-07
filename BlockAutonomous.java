

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "placeblocks", group="Linear Opmode")

public class BlockAutonomous extends LinearOpMode {

    private DcMotor frontright = null;
    private DcMotor backright = null;
    private DcMotor frontleft = null;
    private DcMotor backleft = null;
    // todo: write your code here
    private int leftPos; 
    private int rightPos;
    private DcMotor duckMotor =null;
    private DcMotor armMotor = null;
    private DcMotor extenderMotor = null;
    
    private double pulses= 537;
    private double diamter = 4.724;
    
    private int armPulses = 5281;
    private int bigSprocket = 42;
    private int smallSprocket = 8;

    
    @Override
    public void runOpMode(){
        frontleft  = hardwareMap.get(DcMotor.class,"frontleft");
        backleft = hardwareMap.get(DcMotor.class,"backleft");
        frontright  = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        
        duckMotor = hardwareMap.get(DcMotor.class, "m3");
        
        armMotor = hardwareMap.get(DcMotor.class, "m0");
        
        extenderMotor = hardwareMap.get(DcMotor.class, "m1");
        
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extenderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.FORWARD);
        frontright.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.REVERSE);
        duckMotor.setDirection(DcMotor.Direction.REVERSE);   
        
        leftPos = 0;
        rightPos =0;
        
        waitForStart();
        //level1();
        //level2();
        level3();
        
         /* Start the robot in the second cube near the carousel,
         spin the carousel, go to the shipping hup in an diagonal way,
         place the freight then go foward in a straight line to park in
         the warehouse.*/
            
            
                                                                                                                                                                                                                                            
        
    
        
  


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
        telemetry.addData("armMotor", armMotor.getCurrentPosition());
        
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
    private void level1(){
      //extendArm(0.3);
      moveArm(15);
      
      //moveArm(-0.5);
      //placeDuck
      
        
        
    }
    
    private void level2(){
      //extendArm(0.3);
      moveArm(35);
      //moveArm(-0.5);
      //placeDuck
      
        
        
    }
    
    private void level3(){
      //moveArm(55);
      //moveArm(90);
      extendArm(4);
      //moveArm(-0.5);
      //placeDuck
      
    }
    private void extendArm(double distance){
        int d = (int) ( 67.2 * (distance * 25.4));
        
        int currentPos = extenderMotor.getCurrentPosition();
        //This will tell to the driver hub whats the robot is doing.
        telemetry.addData("extender position", currentPos);
        telemetry.addData("d", d);
        telemetry.update();
        
        extenderMotor.setTargetPosition(currentPos+d);
        extenderMotor.setPower(-.7); // THis is negative to reverse the direction
        extenderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //setting speed
        
        
        while(opModeIsActive()&& extenderMotor.isBusy()){
            idle(); }

        
    
        
    }
    
    
    /* The method moveArm help us know how many degrees we need to move (rotate)
    the arm by doing some math */
    
        
    private void moveArm(int degrees){

        
        int ratio = (bigSprocket / smallSprocket);
        
        int total = ((ratio * armPulses) / 360);
      
        int pulses = (ratio * total); 
        degrees = 44 * degrees;
    
    
        
        
        
        //int d = (int) ((distance-3) *((armPulses/Math.PI)/diamter));
        //drive(d,d,0.50);
        //int d = (int) (distance );
        
        //setting target position
        armMotor.setTargetPosition(degrees);
        //goign to position

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //setting speed
        armMotor.setPower(.9);
        
        while(opModeIsActive()&& armMotor.isBusy()){
            idle(); }

        
    }
    
    
 
}
