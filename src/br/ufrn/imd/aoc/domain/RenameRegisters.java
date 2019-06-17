package br.ufrn.imd.aoc.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RenameRegisters {
	private FileReaderController file = new FileReaderController();
	private HashMap <String, String> newRegister = new HashMap<String, String>();
	private ArrayList <String> availableRegisters = new ArrayList <String>();
	public RenameRegisters(FileReaderController file) {
		this.file = file;
		this.availableRegisters.add("r0");
		this.availableRegisters.add("r1");
		this.availableRegisters.add("r2");
		this.availableRegisters.add("r3");
		this.availableRegisters.add("r4");
		this.availableRegisters.add("r5");
		this.availableRegisters.add("r6");
	}
	
	public void run(HashMap <String, Integer> registersOccurency) 
	{
		//Iremos receber um hashmap com todas as vezes que os registradores aparecem como destino
		
		Set<String> keys = registersOccurency.keySet();	
		for (String key : keys) {	//iremos percorrer o array de ocorrencia dos registradores
			if(registersOccurency.get(key) > 1) //se a chave aparecer mais de uma vez como destino
			{
				Set<String> linesKeys = this.file.getLines().keySet();

				int keyOccur = 0;
				for(String lineKey : linesKeys) 
				{
					//Na primeira fase, quando keyOccyr é 0, mantemos o registrador com o mesmo nome, já que é sua primeira aparição
					if(this.file.getLines().get(lineKey).getDest().equals(key) && keyOccur == 0) 	//Se o destino desta linha for igual a nossa key repetida
					{
						keyOccur += 1;	//Aumentamos a ocorrencia em um, e não alteramos o registrador, já que é o primeiro
						continue;
					}
					//Na segunda fase, quando keyOccyr é 1, alteramos somente o nome do registrador destino, já que os valores de op1 e op2 serão referentes ao antigo registrador
					if(this.file.getLines().get(lineKey).getDest().equals(key) && keyOccur == 1) 
					{
						if(!availableRegisters.isEmpty()) 
						{
							newRegister.put(key, availableRegisters.get(0));	//como o registrador já apareceu antes, alteramos somente o destino para um novo registrador
							this.file.getLines().get(lineKey).setDest(newRegister.get(key));	
							keyOccur += 1;
							availableRegisters.remove(0);
							continue;
						}else 
						{
							System.out.println("SEM REGISTRADORES DISPONÍVEIS PARA A RENOMEAÇÃO");
						}
					
					}
					//Na terceira fase, quando keyOccyr é 2, alteramos qualquer registrador que se referia ao original pelo novo registrador.
					if(keyOccur == 2) 
					{
						//Como já apareceu antes e já foi renomeado, alteramos todo mundo que tenha o nome do antigo registrador para o novo
						if(this.file.getLines().get(lineKey).getDest().equals(key))
						{
							this.file.getLines().get(lineKey).setDest(newRegister.get(key));
						}
						if(this.file.getLines().get(lineKey).getOp1().equals(key)) 
						{
							this.file.getLines().get(lineKey).setOp1(newRegister.get(key));
						}
						if(this.file.getLines().get(lineKey).getOp2().equals(key)) 
						{
							this.file.getLines().get(lineKey).setOp2(newRegister.get(key));
						}
					}
					
				}
			}
		}
	}

}
