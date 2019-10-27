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
		List<String> lines = IOUtils.readFile(filePath);

		for (String line : lines) {
			Sample sample = Parser.parseSample(line);
			packingResult.add(processSample(sample));
		}

		return packingResult;
	}

	private static List<Thing> processSample(Sample sample){

		return getBestCombinations(sample);

	}
	
	private static List<Thing> getBestCombinations(Sample sample) {
		List<Thing> bestCombination = new ArrayList<>();
		
		if (sample.getThings().isEmpty())
			return bestCombination;

		double result = 0;
	    int track = 0;
	    double resSum = 0;
	    
	    for(int i = 0; i< (1<<sample.getThings().size()); i++){
	        double sum = 0;
	        double weight = 0;
	        for(int j=0; j < sample.getThings().size(); j++){
	            if(((1<<j)&i) > 0){
	                sum+= sample.getThings().get(j).getPrice();
	                weight+=sample.getThings().get(j).getWeight();
	            }
	        }
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
	    
	    for(int i = 0; i < sample.getThings().size(); i++){
	        if(((1<<i)&track) > 0){
	           bestCombination.add(sample.getThings().get(i));
	        }
	    }

		return bestCombination;
	}
	
	public static String formatResult(List<Thing> bestCombination){
		StringBuilder sb = new StringBuilder();
		if(bestCombination.isEmpty())
			sb.append("-");
		for(int i=0;i<bestCombination.size();i++){
			if(i!=0)
				sb.append(",");
			sb.append(bestCombination.get(i).getIndex());
		}
		return sb.toString();
	}

	
}
