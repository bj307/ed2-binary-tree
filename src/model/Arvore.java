package model;

public class Arvore {
	No raiz;

	private No addRecursivo(No atual, Aluno valor) {

		if (atual == null) {
			return new No(valor);
		}

		if (valor.getMatricula() < atual.getAluno().getMatricula()) {
			
			atual.esquerdo = addRecursivo(atual.esquerdo, valor);
		
		} else if (valor.getMatricula() > atual.getAluno().getMatricula()) {
		
			atual.direito = addRecursivo(atual.direito, valor);
		
		} else {
			return atual; // o valor já existe
		}

		return atual;
	}

	public void adicionar(Aluno valor) {
		raiz = addRecursivo(raiz, valor);
	}
	
	private void imprimirRecursivo(No no){
		if(no != null) {
			System.out.println(no.valor.toString());
			imprimirRecursivo(no.esquerdo);
			imprimirRecursivo(no.direito);
		}
	}
	public void imprimir() {
		imprimirRecursivo(raiz);
	}
}
