package com.mobiquityinc.utils;


import org.junit.Assert;
import org.junit.Test;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.pojos.Sample;

public class ParserTest {
	
	@Test
	public void validInputTest() throws APIException{
		String input = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
		Sample sample = Parser.parseSample(input);
		Assert.assertEquals(sample.getMaxWeight(), new Integer(81));
		Assert.assertEquals(new Integer(sample.getThings().size()), new Integer(5));
	}
}
