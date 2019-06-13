package br.ufrn.imd.aoc.application;

import java.io.IOException;

import br.ufrn.imd.aoc.domain.FileReaderController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReaderController FR = new FileReaderController();
		
		try {
			FR.readFile("assets/AOC_data.csv");
			FR.printLines();
			FR.printRegisterOccurency();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
