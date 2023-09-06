package teste;

import model.Aluno;
import model.Arvore;

public class TesteArvore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arvore arvore = new Arvore();
		
		for(int i=1;i<=5;i++) {
			arvore.adicionar(new Aluno(i, null, null, null, null, null));
		}
		
		arvore.imprimir();
	}

}
