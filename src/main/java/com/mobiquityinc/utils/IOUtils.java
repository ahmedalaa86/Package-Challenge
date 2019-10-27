package com.mobiquityinc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mobiquityinc.exception.APIException;

public class IOUtils {
	public static List<String> readFile(String filePath) throws APIException{
		List<String> result = new ArrayList<>();
		try {
			try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
				result = lines.collect(Collectors.toList());
			}
		}
		catch(IOException ex){
			throw new APIException("Invalid Input File");
		}
		return result;
	}
}
