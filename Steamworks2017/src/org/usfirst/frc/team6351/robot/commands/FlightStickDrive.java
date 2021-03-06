package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FlightStickDrive extends Command {

    public FlightStickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double joystickZ = Robot.oi.joystickAxisValue(RobotMap.Joy_Z_Axis);
    	double joystickY = Robot.oi.joystickAxisValue(RobotMap.Joy_Y_Axis);
    	double joystickThottle = Robot.oi.joystickAxisValue(RobotMap.Joy_Throttle);
    	
    	double speedMultiplier;
    	
    	if (joystickThottle < 0) {
    		speedMultiplier = (0.5-(joystickThottle * -0.5));
    	}
    	if (joystickThottle > 0) { 
    		speedMultiplier = (0.5+(joystickThottle * 0.5));
    	}
    	if (joystickThottle == 0) {
    		speedMultiplier = 0.5;
    	}
    	
    	double leftMotors;
    	double rightMotors;
    	
    	if (Robot.cameraDriveInverted == true) {
    		leftMotors = (joystickY - joystickZ)*RobotMap.GTA_Drive_Scaling_Teleop;
        	rightMotors = (joystickY + joystickZ)*RobotMap.GTA_Drive_Scaling_Teleop*(-1);
        	
        	if (Robot.oi.flightstick1.getPOV() == 0) {
    			leftMotors = 0.15;rightMotors = -0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 90) {
    			leftMotors = -0.15;rightMotors = -0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 180) {
    			leftMotors = -0.15;rightMotors = 0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 270) {
    			leftMotors = 0.15;rightMotors = 0.15;
    		}
        	
    	} else {
    		leftMotors = (joystickY + joystickZ)*RobotMap.GTA_Drive_Scaling_Teleop;
    		rightMotors = (joystickY - joystickZ)*RobotMap.GTA_Drive_Scaling_Teleop*(-1);
    		
    		if (Robot.oi.flightstick1.getPOV() == 0) {
    			leftMotors = -0.15;rightMotors = 0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 90) {
    			leftMotors = 0.15;rightMotors = 0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 180) {
    			leftMotors = 0.15;rightMotors = -0.15;
    		}
    		if (Robot.oi.flightstick1.getPOV() == 270) {
    			leftMotors = -0.15;rightMotors = -0.15;
    		}
    		
    	}
    	if (leftMotors > RobotMap.TELEOP_MAX_ROBOT_SPEED) {
 		   
    		leftMotors = RobotMap.TELEOP_MAX_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors > RobotMap.TELEOP_MAX_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.TELEOP_MAX_ROBOT_SPEED;
    		   
    	}
    	   if (leftMotors < RobotMap.TELEOP_MIN_ROBOT_SPEED) {
    		   
    		leftMotors = RobotMap.TELEOP_MIN_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors < RobotMap.TELEOP_MIN_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.TELEOP_MIN_ROBOT_SPEED;
    		   
    	}
    	Robot.driveTrain.setLeft(leftMotors);
    	Robot.driveTrain.setRight(rightMotors);
    	
    	//SmartDashboard.putNumber("FlighStick LEFT", leftMotors);
    	// SmartDashboard.putNumber("FlighStick RIGHT", rightMotors);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
