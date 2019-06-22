package br.ufrn.imd.aoc.domain;

import java.util.HashMap;
import java.util.Set;

public class LinesSorter {

	private FileReaderController file = new FileReaderController();
	private HashMap<String, String> dependencies = new HashMap<String, String>();

	public LinesSorter(FileReaderController file, HashMap<String,String> dependencies) {
		this.file = file;
		this.dependencies = dependencies;
	}
	public void updateDependence(String key1, String key2) 
	{
		//se troquei 5 com 2, por exemplo
		//troco as dependencias de 5 para 2
		
		
		// troco as dependencias de 2 para 3
		// troco as dependencias de 4 para 5
		
		if(this.dependencies.containsKey(key1))	 //a chave key 1, sempre acaba na posição key2, então é a primeira a ser alterada
		{
			//caso essa chave tivesse dependencias, precisaremos referenciar suas dependencias para a nova posição de key1
			
			if(this.dependencies.containsKey(key2)) 
			{
				String temporary = this.dependencies.get(key2);
			}else 
			{
				this.dependencies.put(key2, this.dependencies.get(key1));	//coloque em key2 (2), as dependencias da antiga chave key1(5)
				
			}
			
		}
	}
	public void replace(String key1, String key2) 
	{
		// se vou trocar a linha 4 pela 2, vou precisar fazer 2 swaps
		int diff = Integer.parseInt(key1) - Integer.parseInt(key2);
		for(int i = 0; i < diff; i ++) 
		{
			//No exemplo de troca vou trocar a 4 pela 2
			//depois a nova 4 pela 3
			
			//calcula qual a linha atual a ser swapped
			String currentPosition = Integer.toString(Integer.parseInt(key2)+i);
			
			//dentro do objeto novo e do current, alteramos o linePosition para a nova posição a ser alocado
			file.getLines().get(key1).setLinePosition(currentPosition);
			file.getLines().get(currentPosition).setLinePosition(key1);
			
			//vamos pegar o objeto commandline na posição que iremos alterar
			CommandLine temporary = file.getLines().get(currentPosition);
			
			//agora alteramos o objeto daquela posição para receber o novo valor
			file.getLines().put(currentPosition, file.getLines().get(key1));
			
			// e depois, a linha key1, receberá o valor que antes era armazenado em currentPosition
			file.getLines().put(key1, temporary);
		}
		System.out.println("TROCA");
		file.printLines();
		
	}
	public void run() 
	{
		Set<String> keys = this.dependencies.keySet();
		for (String key : keys) {
			//O cara que eu dependo estÃ¡ exatamente atrÃ¡s de mim(key)?
			if(Integer.parseInt(this.dependencies.get(key)) == (Integer.parseInt(key) - 1)) 
			{
				//Existe alguem, depois de mim(key), que nÃ£o depende de mim(key) ou do cara anterior a mim(key)?
				int tmp = Integer.parseInt(key)+1;	//a proxima linha
				while(this.file.getLines().containsKey(Integer.toString(tmp))) 
				{
					//se nao depende de mim(key) e nÃ£o depende do cara anterior a mim(key)
					
					if(this.dependencies.containsKey(Integer.toString(tmp))) 
					{
						// vamos pegar as dependencias da linha tmp, e checar
						// se ela depende de alguem;
						String dp = this.dependencies.get(Integer.toString(tmp));
						String[] dpParts = dp.split(",");
						boolean canMove = true;
						for(String part : dpParts) 
						{
							if(part.equals(key) || part.equals(Integer.toString(Integer.parseInt(key)-1)) || Integer.parseInt(part) > Integer.parseInt(key) )
							{
								canMove = false;
								break;
							}
						}
						if(canMove) 
						{
							System.out.println("troco "+tmp+ " por "+key);
							this.replace(Integer.toString(tmp), key);
							break;
						}
					}else 
					{
						System.out.println("troco "+tmp+ " por "+key);
						this.replace(Integer.toString(tmp), key);
						break;
					}
					tmp += 1;	//passamos para a proxima linha
				}
				
				//System.out.println(key+" = "+this.dependencies.get(key));
			}else continue;
		}
		/**
		 * eu dependo do cara exatamente atras de mim?
		 * se sim,
		 * o cara depois de mim, depende de mim ou do cara atras de mim?
		 * se sim, pula para o proximo cara
		 * se nao, troca manda o elemento pra tras do que estamos perguntando
		 * se nÃ£o tem um proximo cara, vÃª se tem como voltar, se nao tiver, ai termina
		 * para voltar, ele se pergunta 
		 * */
	}

}
