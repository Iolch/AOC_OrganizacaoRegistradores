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
	public void replace(String key1, String key2) 
	{
		//swapo o valor de 4 e 2
		// swapo o valor de 4 e  4-1
		// se vou trocar a linha 4 pela 2, vou precisar fazer 2 swaps
		int diff = Integer.parseInt(key1) - Integer.parseInt(key2);
		for(int i = 0; i < diff; i ++) 
		{
			//fazer o swap entre as linhas
		}
		
	}
	public void run() 
	{
		Set<String> keys = this.dependencies.keySet();
		for (String key : keys) {
			//O cara que eu dependo está exatamente atrás de mim(key)?
			if(Integer.parseInt(this.dependencies.get(key)) == (Integer.parseInt(key) - 1)) 
			{
				//Existe alguem, depois de mim(key), que não depende de mim(key) ou do cara anterior a mim(key)?
				int tmp = Integer.parseInt(key)+1;	//a proxima linha
				while(this.file.getLines().containsKey(Integer.toString(tmp))) 
				{
					//se nao depende de mim(key) e não depende do cara anterior a mim(key)
					
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
							this.replace(Integer.toString(tmp), key);
							break;
						}
					}else 
					{
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
		 * se não tem um proximo cara, vê se tem como voltar, se nao tiver, ai termina
		 * para voltar, ele se pergunta 
		 * */
	}

}
