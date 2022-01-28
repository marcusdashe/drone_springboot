package io.marcus.robot.controller;


// import java.util.Vector;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.marcus.robot.service.DroneService;
import io.marcus.robot.interfaces.Constants;
import io.marcus.robot.model.Drone;
import io.marcus.robot.model.Medication;


 
@RestController
public class DroneController {
	
	// @Autowired
	// DroneService droneservice;

	// Controller for registering a Drone
	@RequestMapping(value="/drone/register", method=RequestMethod.POST)
	public ResponseEntity<Object> registerDrone(@RequestBody Drone drone){
		DroneService.returnDrones().add(drone);
		return new ResponseEntity<>("Drone is registered successfully ["+ drone.getSerialNo()+ "]", HttpStatus.CREATED);
	}

	// Controller for registering a Medication
	@RequestMapping(value="/medication/register", method=RequestMethod.POST)
	public ResponseEntity<Object> registerDrone(@RequestBody Medication med){
		DroneService.getMeds().add(med);
		return new ResponseEntity<>("Medication is registered successfully ["+ med.getCode()+ "]", HttpStatus.CREATED);
	}

	// Controller for loading Drone with Medication
	@GetMapping(value="/drone/{droneSN}/{medcode}")
	public ResponseEntity<Object> loadDroneWithMedication(@PathVariable("droneSN") short droneSN, @PathVariable("medcode") String code){
		if ( DroneService.checkForExistenceOfDrone()){
			return new ResponseEntity<>("There is no registered drone", HttpStatus.NOT_FOUND);
		}
		for(Drone d: DroneService.returnDrones()){
			if(d.getSerialNo() == droneSN){
				
							Medication med = DroneService.getMedFromCode(code);
							DroneService.loadDroneWithMedication(med, d);
						// }
					}
					d.setState(Constants.state.LOADED);
				}	
		return new ResponseEntity<>("Drone ["+ droneSN + "] is successfully loaded with  ["+ code +"]", HttpStatus.ACCEPTED);
	}

	// Controller for Checking loaded medication items for a given Drone
	@GetMapping(value="/drone/med/{droneSN}")
	public ResponseEntity<Object> checkMedsForGivenDrone(@PathVariable("droneSN") short droneSN){
		if ( DroneService.checkForExistenceOfDrone()){
			return new ResponseEntity<>("There is no registered drone", HttpStatus.NOT_FOUND);
		}
		StringBuffer stringbuffer = new StringBuffer();
		for(Drone d: DroneService.returnDrones()){
			if(d.getSerialNo() == droneSN){
				Map<Short, ArrayList<String>> dSNToMCode = DroneService.returnMeds();
				Short droneSerialNumber = Short.valueOf(droneSN);
				ArrayList<String> medCode = dSNToMCode.get(droneSerialNumber);
				stringbuffer = new StringBuffer();
				for(String medCodeStr : medCode){
					stringbuffer.append(medCodeStr);
				}
				
			}
			
		}
		return new ResponseEntity<>(stringbuffer.toString(), HttpStatus.OK);
		
	}

	// Controller for checking available drone for loading
	@GetMapping(value="/drone/available")
	public ResponseEntity<Object> checkAvailableDroneForLoading(){
		if ( DroneService.checkForExistenceOfDrone()){
			return new ResponseEntity<>("There is no registered drone", HttpStatus.NOT_FOUND);
		}
		for(Drone d: DroneService.returnDrones()){
			if(d.getState() == Constants.state.IDLE);
			return new ResponseEntity<>(d.getSerialNo() + " Is free", HttpStatus.FOUND);
		}
		return null;
	}
	
	// Controller for checking drone battery level for a given drone
	@GetMapping(value="/drone/battery/{droneSN}")
	public ResponseEntity<Object> checkBatteryLevel(@PathVariable("droneSN") short droneSN){
		if ( DroneService.checkForExistenceOfDrone()){
			return new ResponseEntity<>("There is no registered drone", HttpStatus.NOT_FOUND);
		}

		for(Drone d: DroneService.returnDrones()){
			if(d.getSerialNo() == droneSN);
			return new ResponseEntity<>(d.getBatteryCapacity(), HttpStatus.OK);
		}
		return null;
	}	
}


