package br.ufrn.imd.aoc.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderController {

private final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	public void readFile(String filepath) throws FileNotFoundException, IOException 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        System.out.println(values);
		    }
		}
	}
}
