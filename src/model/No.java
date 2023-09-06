package model;

public class No{
	Aluno valor;
	No esquerdo;
	No direito;
	
	No(Aluno valor){
		this.valor = valor;
		this.esquerdo = this.direito = null;
	}
	
	public Aluno getAluno() {
		return this.valor;
	}
	
}
