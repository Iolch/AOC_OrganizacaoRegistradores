package br.ufrn.imd.aoc.domain;

public class CommandLine {
	private String linePosition;
	private String tipoInstrucao;
	private String op1;
	private String op2;
	private String dest;
	
	public CommandLine(String lp, String ti, String dest, String op1, String op2) 
	{
		this.linePosition = lp;
		this.tipoInstrucao = ti;
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
	}

	public String getLinePosition() {
		return linePosition;
	}

	public String getTipoInstrucao() {
		return tipoInstrucao;
	}

	public String getOp1() {
		return op1;
	}

	public String getOp2() {
		return op2;
	}

	public String getDest() {
		return dest;
	}
}
