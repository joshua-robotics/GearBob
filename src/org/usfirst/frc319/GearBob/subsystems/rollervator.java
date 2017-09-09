// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc319.GearBob.subsystems;

import org.usfirst.frc319.GearBob.Robot;
import org.usfirst.frc319.GearBob.RobotMap;
import org.usfirst.frc319.GearBob.commands.*;


import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 *
 */
public class rollervator extends Subsystem {

	
	public final CANTalon climberLead = RobotMap.climberclimberLead;
	public final CANTalon climberFollow = RobotMap.climberclimberFollow;
	
	private final int SHOOT_PROFILE = 0;
	private final int CLIMB_PROFILE = 1;
	
	private double pShoot = 0.2; // was 0.06 for tower v1
	private double fShoot = 0.289;
	private double dShoot = 0.2; // was .2
	private double iShoot = 0.0; // was 0.08 for tower v1
	private int iZoneShoot = 0; // was 500 for tower v1
	
	private double pClimb = 0.06;
	private double fClimb = 0.1;
	private double dClimb = 0.0;
	private double iClimb = 0.0;

	StringBuilder _sb = new StringBuilder(); // used for tuning PID
	int _loops = 0; // used for tuning PID

	public rollervator() {

		
		
		climberLead.changeControlMode(TalonControlMode.PercentVbus);
		climberLead.enableBrakeMode(false);
		climberFollow.enableBrakeMode(false);
		
		climberFollow.changeControlMode(TalonControlMode.Follower);
		climberFollow.set(climberLead.getDeviceID());
		climberFollow.reverseOutput(true);
		climberLead.reverseOutput(false);
		
		climberLead.configNominalOutputVoltage(+0.0f, -0.0f);
		climberLead.configPeakOutputVoltage(12.0, 0.0);
		climberFollow.configPeakOutputVoltage(-12.0, -0.0);
		
		climberLead.setVoltageRampRate(36.0);
		climberFollow.setVoltageRampRate(36.0);
		

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// setDefaultCommand(new MotorTest(this,
		// RobotMap.rollervatorRollervatorLead));
		//setDefaultCommand(new RollervatorStop());
		//setDefaultCommand(new ClimbStart());
		//setDefaultCommand(new RollervatorPIDTestMode());
		// Set the default command for a subsystem here.

	}

	

	// --- could be used as an isfinished to cut the motor off if current is
	// exceeded ---///
	public boolean isExceedingCurrentThreshhold(double threshhold) {
		if (climberLead.getOutputCurrent() > threshhold) {
			return true;
		} else
			return false;
	}
	public double climberCurrent(){
		return climberLead.getOutputCurrent();
	}
	public double climberFollowCurrent(){
		return climberFollow.getOutputCurrent();
	}
	public double climberLeadVoltage(){
		return climberLead.getOutputVoltage();
	}
	public double climberFollowVoltage(){
		return climberFollow.getOutputVoltage();
	}
	public void climberStop(){
		climberLead.changeControlMode(TalonControlMode.PercentVbus);
		climberFollow.changeControlMode(TalonControlMode.PercentVbus);
		
		climberLead.set(0);
	}
	public void setClimber(double setpoint){
		climberLead.set(setpoint);
	}
}