package br.ufrn.imd.aoc.application;

import java.io.IOException;

import br.ufrn.imd.aoc.domain.FileReaderController;
import br.ufrn.imd.aoc.domain.RenameRegisters;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReaderController FR = new FileReaderController();
		
		try {
			FR.readFile("assets/AOC_data.csv");
			//FR.printLines();
			//FR.printRegisterOccurency();
			try 
			{
				RenameRegisters RR = new RenameRegisters(FR);	//Passamos o arquivo lido para a classe que irá renomear os registradores
				RR.run();	//executamos a função que renomeará
				FR.printLines();
			}catch(Exception e) 
			{
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
