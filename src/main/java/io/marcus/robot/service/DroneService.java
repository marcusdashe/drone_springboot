package io.marcus.robot.service;

import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import io.marcus.robot.model.Drone;
import io.marcus.robot.model.Medication;

import io.marcus.robot.interfaces.Constants;


@Service
public class DroneService {

    // Logger Object
    private final static Logger LOGGER = Logger.getLogger(DroneService.class.getName());
    
    static {
        LOGGER.setLevel(Level.INFO);
    }

    // Container for storing Drones and Meds
	private static Vector<Drone> drones = new Vector<Drone>(10);
    private static ArrayList<Medication> meds = new ArrayList<Medication>();
    private static Map<Short, ArrayList<String>> droneSNToMedsCode = new HashMap<>();

    public static Vector<Drone> returnDrones(){
        return drones;
    }

    public static ArrayList<Medication> getMeds(){
        return meds;
    }
    public static Map<Short, ArrayList<String>> returnMeds(){
        return droneSNToMedsCode;
    }

    public void addToMeds(Medication m){
        meds.add(m);
    }

    public static Medication getMedFromCode(String code){
        
        for(Medication med: meds){
            if (med.getCode() == code){
                return med;
            }
            else {
                break; 
            }
        }
        Medication med = new Medication();
        med.setName("Unknown name");
        med.setWeight((float)0.0);
        med.setCode(code);
        med.setImgPath("/");
        return med;
    }

    public static void loadDroneWithMedication(Medication med, Drone drone){
        if(drone.getBatteryCapacity() > 25 && med.getMedWeight() <= 500){
            // addToMeds(med);
            Medication bindToThisDrone = meds.get(meds.size()-1);
            ArrayList<String> al = new ArrayList<String>();
            al.add(bindToThisDrone.getCode());
            droneSNToMedsCode.put(Short.valueOf(drone.getSerialNo()), al);
            drone.setState(Constants.state.LOADING);
            System.out.println("Medication Loaded Successfully");

        }
        else{
            System.out.println("Ooops!... Unable to load medication");
            System.out.println("Due to either Drone battery capacity less than 25% or medication weight is beyond 500gram");
        }
    }

    public static boolean checkForExistenceOfDrone(){
      boolean result = returnDrones().isEmpty() ? true : false;
      return result;
    }

    @PostConstruct
    @Scheduled(fixedDelay = 5000 )
    public void CheckBatteryLevelAndLogingTask(){
       
        for(Drone d: DroneService.returnDrones()){
            LOGGER.info(d.getSerialNo() + String.valueOf(d.getBatteryCapacity()));

            }
        }
    
    }
