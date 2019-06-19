package br.ufrn.imd.aoc.domain;

import java.util.HashMap;

public class LinesSorter {

	private FileReaderController file = new FileReaderController();
	private HashMap<String, String> dependencies = new HashMap<String, String>();

	public LinesSorter(FileReaderController file, HashMap<String,String> dependencies) {
		this.file = file;
		this.dependencies = dependencies;
	}
	
	public void run() 
	{
		System.out.println(this.dependencies);
	}

}
