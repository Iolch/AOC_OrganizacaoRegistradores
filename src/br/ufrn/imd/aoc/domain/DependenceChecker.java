package br.ufrn.imd.aoc.domain;

import java.util.HashMap;
import java.util.Set;

public class DependenceChecker {
	private HashMap<String, String> dependencies = new HashMap<String,String>();
	private FileReaderController file = new FileReaderController();
	
	/**
	 * 1 s3 s2 s1
	 * 2 s3 s1 s1
	 * 3 s4 s3 s1
	 * 
	 * linha tres depende da linha 2
	 * pra cada operando, ele pergunta se o operando apareceu como registrador numa linha anterior
	 * */
	public DependenceChecker(FileReaderController file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}
	public HashMap<String, String> getDependencies()
	{
		return this.dependencies;
	}
	public void run() 
	{
		HashMap <String, String> fileDests = this.file.getFileDests();
		
		//AGORA IREMOS PERCORRER AS LINHAS 
		Set<String> keys = this.file.getLines().keySet();
		for (String key : keys) {
			
			//SE O OPERANDO APARECER COMO DESTINO ANTES DA LINHA ATUAL, TEMOS UMA
			//DEPENDENCIA DE LINHAS
			
			String temporaryOp1Key = this.file.getLines().get(key).getOp1();
			if(fileDests.containsKey(temporaryOp1Key))	//caso o operando apareca como destino
			{
				if(Integer.parseInt(fileDests.get(temporaryOp1Key)) < Integer.parseInt(key))	//caso a linha do operando como destino seja anterior a atual 
				{
					System.out.println("Linha "+key+" depende de "+ fileDests.get(temporaryOp1Key));
					
					
					//Caso uma dependencia já tenha sido relacionada a linha atual, adicionamos ela separada por ',' em uma string
					if(this.dependencies.containsKey(key)) 
					{
						String temporary = this.dependencies.get(key)+","+fileDests.get(temporaryOp1Key);
						this.dependencies.put(key, temporary);
					}else this.dependencies.put(key, fileDests.get(temporaryOp1Key));
				}	
			}
			
			String temporaryOp2Key = this.file.getLines().get(key).getOp2();
			if(fileDests.containsKey(temporaryOp2Key))	//caso o operando apareca como destino
			{
				if(Integer.parseInt(fileDests.get(temporaryOp2Key)) < Integer.parseInt(key))	//caso a linha do operando como destino seja anterior a atual 
				{
					System.out.println("Linha "+key+" depende de "+ fileDests.get(temporaryOp2Key));
					
					//Caso uma dependencia já tenha sido relacionada a linha atual, adicionamos ela separada por ',' em uma string
					if(this.dependencies.containsKey(key)) 
					{
						String temporary = this.dependencies.get(key)+","+fileDests.get(temporaryOp2Key);
						this.dependencies.put(key, temporary);
					}else this.dependencies.put(key, fileDests.get(temporaryOp2Key));
				}	
			}
			
			//OBS CHECAR SE ELE VAI PRINTAR A MESMA DEPENDENCIA MAIS DE UMA VEZ!!!!!!!
			
			
		}
		
	}

}
