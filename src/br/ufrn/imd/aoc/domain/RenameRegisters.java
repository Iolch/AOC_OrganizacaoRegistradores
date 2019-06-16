package br.ufrn.imd.aoc.domain;

import java.util.HashMap;
import java.util.Set;

public class RenameRegisters {
	private FileReaderController file = new FileReaderController();
	private HashMap <String, String> newRegister = new HashMap<String, String>();
	public RenameRegisters(FileReaderController file) {
		this.file = file;
	}
	
	public void run() 
	{
		Set<String> keys = this.file.getRegisterDestOccurency().keySet();
		for (String key : keys) {
			if(this.file.getRegisterDestOccurency().get(key) > 1) //se a chave aparecer mais de uma vez como destino
			{
				Set<String> linesKeys = this.file.getLines().keySet();

				int keyOccur = 0;
				for(String lineKey : linesKeys) 
				{
					if(this.file.getLines().get(lineKey).getDest().equals(key) && keyOccur == 0) 	//Se o destino desta linha for igual a nossa key repitida
					{
						keyOccur += 1;
						continue;
					}
					if(this.file.getLines().get(lineKey).getDest().equals(key) && keyOccur == 1) 
					{
						newRegister.put(key, "ro");
						this.file.getLines().get(lineKey).setDest(newRegister.get(key));
						keyOccur += 1;
						continue;
					}
					if(keyOccur == 2) 
					{
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
