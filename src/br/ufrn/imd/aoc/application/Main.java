package br.ufrn.imd.aoc.application;

import java.io.IOException;
import java.util.HashMap;

import br.ufrn.imd.aoc.domain.FileReaderController;
import br.ufrn.imd.aoc.domain.RenameRegisters;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReaderController FR = new FileReaderController();
		
		try {
			FR.readFile("assets/AOC_data.csv");
			try 
			{
				RenameRegisters RR = new RenameRegisters(FR);	//Passamos o arquivo lido para a classe que irá renomear os registradores
				FR.printLines();
				
				//Enquanto houver registradores repetidos no destino, iremos chamar a função de renomear
				HashMap <String, Integer> registersOccurency = FR.runRegisterDestOccurency();
				while(!(registersOccurency.isEmpty())) 
				{
					RR.run(registersOccurency);
					registersOccurency = FR.runRegisterDestOccurency();
				}
				
				FR.printLines();
			}catch(Exception e) 
			{
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
