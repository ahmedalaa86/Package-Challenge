package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.pojos.Sample;
import com.mobiquityinc.pojos.Thing;

public class Parser {
	public static Sample parseSample(String line)  throws APIException{
		Sample sample = new Sample();
		
		// Check if the provided line has max weight value as expected
		if (!line.contains(" : ")) {
			throw new APIException("Invalid Input: Has no Max Weight");
		}

		String[] parts = line.split(" : ");

		
		// get max weight value or throw Exception if it is not in the correct format
		try {
			int maxWeight = Integer.parseInt(parts[0]);
			sample.setMaxWeight(maxWeight);
		} catch (NumberFormatException | NullPointerException ex) {
			throw new APIException(ex.getMessage());
		}
		
		// as a business requirement the max weight should be <= 100
		if(sample.getMaxWeight() > 100){
			throw new APIException("Max weight should be less than or equal 100");
		}

		String[] thingsInput = parts[1].split("\\s+");

		
		// as a business requirement number of items should be <= 15
		if(thingsInput.length > 15){
			throw new APIException("Number of items should be less than or equals 15");
		}
		
		// construct Things objects
		for (String thingInput : thingsInput) {
			thingInput = thingInput.trim().replace("(", "").replace(")", "");
			
			Thing thing = constructThing(thingInput);
			
			// Ignore Things with weight greater than max weight as it won't fit
			if (thing.getWeight() <= sample.getMaxWeight()) {
				sample.getThings().add(thing);
			}
		}
		return sample;
	}
	
	private static Thing constructThing(String thingInput) throws APIException {
		
		// Throw Exception if the input is not in the expected format
		if (!thingInput.contains(",") || thingInput.split(",").length != 3) {
			throw new APIException("Invalid Thing Format");
		}

		String[] parts = thingInput.split(",");
		
		Thing thing = null;

		// Construct Thing object and throw Exception if it is not in a numeric format
		try {
			thing = new Thing(Integer.parseInt(parts[0]),
					Double.parseDouble(parts[1]),
					Double.parseDouble(parts[2].substring(1,
							parts[2].length() )));
		} catch (NumberFormatException | NullPointerException ex) {
			throw new APIException("Invalid Number Format");
		}
		return thing;

	}
}
