package br.ufrn.imd.aoc.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class FileReaderController {

private final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
private HashMap <String, CommandLine> lines = new HashMap <String, CommandLine>();	// Armazenará os dados de cada linha, e sua chave é a posição da linha
private HashMap <String, Integer> registerDestOccurency = new HashMap <String, Integer>();	//Armazenará a quantidade de vezes que cada registrador aparece como destino, sua chave é o registrador


	public void readFile(String filepath) throws FileNotFoundException, IOException 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		    	 //		Primeiro iremos quebrar a linha em colunas que referem a	 //
		    	//		0 => id, 1 => instrucao, 2 => destino, 3 => op1, 4 => op2	//
		        String[] values = line.split(COMMA_DELIMITER);

		        //		Cada linha será armazenada em lines, e iremos adicionar a	 //
		        //		ocorrencia do registrador destino em registerDestOccurency	// 
		        
		    	CommandLine CL = new CommandLine(values[0],values[1],values[2],values[3],values[4]);	//cria objeto linha
		    	this.lines.put(CL.getLinePosition(), CL);	//armazena em lines
		    	
		    	if(this.registerDestOccurency.get(CL.getDest()) != null) 	//caso já exista essa chave, adicionamos +1 na qntd ocorrências
		    		{	this.registerDestOccurency.put(CL.getDest(), this.registerDestOccurency.get(CL.getDest())+1);	}
		    	else this.registerDestOccurency.put(CL.getDest(), 1);	// caso não, registramos a nova chave
		    	
		    }
		    
		}
	}
	
	public HashMap <String, CommandLine> getLines()
	{
		return this.lines;
	}
	public void printLines() 
	{
		Set<String> keys = this.lines.keySet();

		System.out.println("Linhas");
		for (String key : keys) {
			System.out.println("id: "+this.lines.get(key).getLinePosition());
			System.out.println("ti: "+this.lines.get(key).getTipoInstrucao());
			System.out.println("de: "+this.lines.get(key).getDest());
			System.out.println("o1: "+this.lines.get(key).getOp1());
			System.out.println("o2: "+this.lines.get(key).getOp2());
		}
	}
	
	public void printRegisterOccurency() 
	{
		Set<String> keys = this.registerDestOccurency.keySet();
		System.out.println("Ocorrencia dos registradores");
		for (String key : keys) {
			System.out.println(key+" :"+this.registerDestOccurency.get(key));
		}
		
	}
}
