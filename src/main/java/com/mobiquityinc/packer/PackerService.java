package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.pojos.Sample;
import com.mobiquityinc.pojos.Thing;
import com.mobiquityinc.utils.IOUtils;
import com.mobiquityinc.utils.Parser;

public class PackerService {
	
	public static List<List<Thing>> processPacking(String filePath) throws APIException {
		List<List<Thing>> packingResult = new ArrayList<>();
		
		// Read input file into list of Strings (String for each line)
		List<String> lines = IOUtils.readFile(filePath);

		// For each Line parse it and calculate its best combination
		for (String line : lines) {
			Sample sample = Parser.parseSample(line);
			packingResult.add(processSample(sample));
		}

		return packingResult;
	}

	// process the sample and provide list of best combination things
	private static List<Thing> processSample(Sample sample){

		return getBestCombinations(sample);

	}
	
	// calculate best combination things for given sample
	private static List<Thing> getBestCombinations(Sample sample) {
		List<Thing> bestCombination = new ArrayList<>();
		
		if (sample.getThings().isEmpty())
			return bestCombination;

		double result = 0;
	    int track = 0;
	    double resSum = 0;
	    
	    // Loop over things and check all combinations to get the best combination
	    for(int i = 0; i< (1<<sample.getThings().size()); i++){
	        double sum = 0;
	        double weight = 0;
	        for(int j=0; j < sample.getThings().size(); j++){
	            if(((1<<j)&i) > 0){
	                sum+= sample.getThings().get(j).getPrice();
	                weight+=sample.getThings().get(j).getWeight();
	            }
	        }
	        
	        // if we didn't reach the max weight
	        if(weight <= sample.getMaxWeight()){
	            if(sum > resSum){
	                result = weight;
	                track = i;
	                resSum = sum;
	            }else if(sum == resSum && weight < result){
	                result = weight;
	                track = i;
	            }
	        }
	    }
	    
	    // Fetch list of Things that provide the best combination
	    for(int i = 0; i < sample.getThings().size(); i++){
	        if(((1<<i)&track) > 0){
	           bestCombination.add(sample.getThings().get(i));
	        }
	    }

		return bestCombination;
	}
	
	// Format the result in the requested format
	public static String formatResult(List<Thing> bestCombination){
		StringBuilder sb = new StringBuilder();
		// If we have no Things object use -
		if(bestCombination.isEmpty())
			sb.append("-");
		
		// Get indeces of best combination things separated by ,
		for(int i=0;i<bestCombination.size();i++){
			if(i!=0)
				sb.append(",");
			sb.append(bestCombination.get(i).getIndex());
		}
		return sb.toString();
	}

	
}
