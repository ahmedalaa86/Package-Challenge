package com.mobiquityinc.utils;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

public class IOUtilsTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void validFileTest() throws APIException, URISyntaxException{
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("validInput.txt").toURI()).toString();
		List<String> result = IOUtils.readFile(inputPath);
		List<String> fileContents = new ArrayList<>();
		fileContents.add("81 : (1,53.38,�45) (2,88.62,�98) (3,78.48,�3) (4,72.30,�76) (5,30.18,�9) (6,46.34,�48)");
		fileContents.add("8 : (1,15.3,�34)");
		fileContents.add("75 : (1,85.31,�29) (2,14.55,�74) (3,3.98,�16) (4,26.24,�55) (5,63.69,�52) (6,76.25,�75) (7,60.02,�74) (8,93.18,�35) (9,89.95,�78)");
		fileContents.add("56 : (1,90.72,�13) (2,33.80,�40) (3,43.15,�10) (4,37.97,�16) (5,46.81,�36) (6,48.77,�79) (7,81.80,�45) (8,19.36,�79) (9,6.76,�64)");
		
		Assert.assertEquals(result,fileContents);
	}
	
	@Test
	public void invalidFilePathTest() throws APIException{
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Invalid Input File");
	    
		IOUtils.readFile("fake path");
	}
}
