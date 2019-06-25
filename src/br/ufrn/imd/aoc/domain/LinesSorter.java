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
	public void updateDependence() 
	{
		//removemos das dependencias, todo mundo que que nao precise ser movido
		
		DependenceChecker DC = new DependenceChecker(this.file);
		DC.run(true);
		this.dependencies = DC.getDependencies();
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
		System.out.println("TROCA " + key1 + " E " + key2 );
		file.printLines();
		this.updateDependence();
	}
	
	/**
	 * Workflow da função
	 * eu dependo do cara exatamente atras de mim?
	 * 	se sim,
	 * 	o cara depois de mim, depende de mim ou do cara atras de mim?
	 * 	 se sim, pula para o proximo cara
	 * 	 se nao, troca manda o elemento pra tras do que estamos perguntando
	 * 	se nÃ£o tem um proximo cara, vÃª se tem como voltar, se nao tiver, termina
	 * */
	public FileReaderController run() 
	{
		while(!this.dependencies.isEmpty()) 
		{

			Set<String> keys = this.dependencies.keySet();
			String key = keys.iterator().next();
			//O cara que eu dependo estÃ¡ exatamente atrÃ¡s de mim(key)?
			
			int tmp = Integer.parseInt(key)+1;	//a proxima linha

			String dp = this.dependencies.get(key);
			String[] dpParts = dp.split(",");
			for(String part : dpParts) 
			{

				if(Integer.parseInt(part) == (Integer.parseInt(key) - 1)) 
				{

					//Existe alguem, depois de mim(key), que nÃ£o depende de mim(key) ou do cara anterior a mim(key)?
					
					while(this.file.getLines().containsKey(Integer.toString(tmp))) 
					{
						//se nao depende de mim(part) e nÃ£o depende do cara anterior a mim(part)
						
						if(this.dependencies.containsKey(Integer.toString(tmp))) 
						{
							// vamos pegar as dependencias da linha tmp, e checar
							// se ela depende de alguem;
							String[] tmpParts = this.dependencies.get(Integer.toString(tmp)).split(",");
							
							boolean canMove = true;
							for(String tmpPart : tmpParts) 
							{
								if(tmpPart.equals(part) || tmpPart.equals(Integer.toString(Integer.parseInt(part)-1)) || Integer.parseInt(tmpPart) > Integer.parseInt(part) )
								{
									canMove = false;
									break;
								}
							}
							if(canMove) 
							{
								this.replace(Integer.toString(tmp), key);
								break;
							}
						}else 
						{
							this.replace(Integer.toString(tmp), key);
							break;
						}
						tmp += 1;	//passamos para a proxima linha
					}
				}else continue;
			}
			
			
			
		}
		return file;
	}

}
