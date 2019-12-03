package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RepositionSkystonesRed", group = "LinearOpMode")
public class RepositionSkystonesRed extends Robot {


    @Override
    public void runOpMode() {
        super.runOpMode();

        int red = colorSensor.red();
        int blue = colorSensor.blue();
        int green = colorSensor.green();

        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();

            this.armMotor.setPower(1.0);
            this.whileLoopWait(1000);
            this.openArm();
            ElapsedTime time = new ElapsedTime();
            runtime.reset();
            while (this.opModeIsActive() && time.milliseconds() < 1000) {
                this.armMotor.setPower(0);
                this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

            goForwardsInInches(23.2);
            strafeRightInInches(20);
            turnLeftInMilli(69);
            goForwardsInInches(1.4);
            Log.i("REPOSITION-CLASS", "red: " + this.red);
            strafeLeftUntilDetectSkystone();
            this.stopMotors();
//            runtime.reset();
            goForwardsInInches(5);
            closeArm();
            goBackwardsInInches(38);
            strafeRightInInches(52);
            openArm();
            goForwardsInInches(4);
            goBackwardsInInches(5);
            strafeLeftInInches(52);
            stop();
        }
    }
}