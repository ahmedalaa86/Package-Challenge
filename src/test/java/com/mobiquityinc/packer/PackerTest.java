package com.mobiquityinc.packer;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mobiquityinc.exception.APIException;

public class PackerTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void validInputtest() throws APIException, URISyntaxException {
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("validInput.txt").toURI()).toString();
		String result = Packer.pack(inputPath);
		String newLine = System.getProperty("line.separator");
		Assert.assertEquals(result,"4".concat(newLine).concat("-").concat(newLine).concat("2,7").concat(newLine).concat("8,9"));
	}
	
	@Test
	public void noMaxWeightTest() throws APIException, URISyntaxException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Invalid Input: Has no Max Weight");
	    
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("noMaxWeight.txt").toURI()).toString();
		Packer.pack(inputPath);
	}
	
	@Test
	public void invalidThingTest() throws APIException, URISyntaxException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Invalid Thing Format");
	    
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("invalidThing.txt").toURI()).toString();
		Packer.pack(inputPath);
	}
	
	@Test
	public void invalidNumberTest() throws APIException, URISyntaxException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Invalid Number Format");
	    
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("invalidNumber.txt").toURI()).toString();
		Packer.pack(inputPath);
	}
	
	@Test
	public void invalidFilePathTest() throws APIException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Invalid Input File");
	    
		Packer.pack("fakePath");
	}
	
	@Test
	public void bigMaxWeightTest() throws APIException,URISyntaxException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Max weight should be less than or equal 100");
	    
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("bigMaxWeight.txt").toURI()).toString();
		Packer.pack(inputPath);
	}
	
	@Test
	public void bigNumberofThingsTest() throws APIException,URISyntaxException {
		expectedEx.expect(APIException.class);
	    expectedEx.expectMessage("Number of items should be less than or equals 15");
	    
		String inputPath = Paths.get(this.getClass().getClassLoader().getResource("bigNumofThings.txt").toURI()).toString();
		Packer.pack(inputPath);
	}

}
