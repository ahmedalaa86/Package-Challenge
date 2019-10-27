package com.mobiquityinc.packer;

import java.util.List;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.pojos.Thing;

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		List<List<Thing>> packingResult = PackerService.processPacking(filePath);
		String result = "";
		String newLine = System.getProperty("line.separator");
		
		boolean first = true;
		for(List<Thing> sampleResult:packingResult){
			if(!first){
				result = result.concat(newLine);
			} else {
				first = false;
			}
			
			result = result.concat(PackerService.formatResult(sampleResult));
		}
		return result;
	}

}
