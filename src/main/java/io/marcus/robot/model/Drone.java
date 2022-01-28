package io.marcus.robot.model;

import io.marcus.robot.interfaces.Constants;
import java.util.Map;
import java.util.HashMap;

import io.marcus.robot.model.Medication;


public class Drone implements Constants {
    private short serialNo;
    private short weightLimit;
    private short batteryCapacity;

    private Constants.model model;
    private Constants.state state;

   

// public Drone(short serialNo, short weightLimit, short batteryCapacity){
//         this.serialNo = serialNo;
//         this.weightLimit = weightLimit;
//         this.batteryCapacity = batteryCapacity;
//     }

    
    public void setSerialNo(short serialNo){
        this.serialNo = serialNo;
    }

    public short getSerialNo(){
        return this.serialNo;
    }


    public void setWeightLimit(short weightLimit){
        this.weightLimit = weightLimit;
    }

    public void setBatteryCapacity(short batteryCapacity){
        this.batteryCapacity = batteryCapacity;
    }

    public short getBatteryCapacity(){
        return this.batteryCapacity;
    }

    public void setState(Constants.state state){
        this.state = state;
    }

    public void setModel(Constants.model model){
        this.model = model;
    }

    public Constants.state getState(){
        return this.state;
    }
  
   
}
