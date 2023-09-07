/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.List;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextPane;

/**
 *
 * @author kaior
 */
public class ArvoreAvl {

    Node raiz;

    public ArvoreAvl() {
        raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void inserir(Aluno novo) {
        Node aluno = new Node(novo);
        raiz = inserirRec(raiz, aluno);
    }

    public void remover(int mat) {
        removerRec(this.raiz, mat);
    }

    public Node buscar(int mat) {
        return buscarRec(raiz, mat);
    }

    public void imprimirArvoreMatriz() {
        int altura = altura(raiz);
        int largura = (int) Math.pow(2, altura) - 1;
        Node[][] matriz = new Node[altura][largura];
        preencherMatriz(matriz, raiz, 0, 0, largura);

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                Node node = matriz[i][j];
                if (node != null) {
                    System.out.print(node.getAluno().getMatricula());
                } else {
                    //System.out.print("_");
                }

                // Adicione espaços para alinhar os números
                if (node != null) {
                    for (int k = 0; k < 3 - String.valueOf(node.getAluno().getMatricula()).length(); k++) {
                        System.out.print("_");
                    }
                } else {
                    for (int k = 0; k < 3; k++) {
                        System.out.print("_");
                    }
                }
            }
            System.out.println();
        }
    }

    public void exibirArvore(JTextPane textPane) {
        int altura = altura(raiz);
        int largura = (int) Math.pow(2, altura) - 1;
        Node[][] matriz = new Node[altura][largura];
        preencherMatriz(matriz, raiz, 0, 0, largura);

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                Node node = matriz[i][j];
                if (node != null) {
                    resultado.append(node.getAluno().getMatricula());
                } else {
                    resultado.append("_");
                }

                // Adicione espaços para alinhar os números
                if (node != null) {
                    for (int k = 0; k < 3 - String.valueOf(node.getAluno().getMatricula()).length(); k++) {
                        resultado.append("_");
                    }
                } else {
                    for (int k = 0; k < 3; k++) {
                        resultado.append("_");
                    }
                }
            }
            resultado.append("\n");
        }

        // Defina o resultado no JTextPane
        textPane.setText(resultado.toString());
    }

    public ArrayList<Aluno> emOrdem() {
        ArrayList<Aluno> listaOrdenada = new ArrayList<>();
        emOrdemRec(raiz, listaOrdenada);
        return listaOrdenada;
    }

    private void emOrdemRec(Node ref, ArrayList<Aluno> listaOrdenada) {
        if (ref == null) {
            return;
        }
        emOrdemRec(ref.getEsquerdo(), listaOrdenada);
        listaOrdenada.add(ref.getAluno());
        emOrdemRec(ref.getDireito(), listaOrdenada);
    }

    private void preencherMatriz(Node[][] matriz, Node node, int nivel, int inicio, int fim) {
        if (node == null || nivel >= matriz.length) {
            return;
        }

        int meio = (inicio + fim) / 2;
        matriz[nivel][meio] = node;

        preencherMatriz(matriz, node.getEsquerdo(), nivel + 1, inicio, meio - 1);
        preencherMatriz(matriz, node.getDireito(), nivel + 1, meio + 1, fim);
    }

    private Node removerRec(Node ref, int mat) {
        if (ref == null) {
            return ref;
        }

        if (mat < ref.getAluno().getMatricula()) {
            ref.setEsquerdo(removerRec(ref.getEsquerdo(), mat));
        } else if (mat > ref.getAluno().getMatricula()) {
            ref.setDireito(removerRec(ref.getDireito(), mat));
        } else {
            if (ref.getEsquerdo() == null && ref.getDireito() == null) {
                return null;
            } else if (ref.getEsquerdo() != null && ref.getDireito() != null) {
                Node antecessor = maiorValor(ref.getEsquerdo());

                ref.setAluno(antecessor.getAluno());

                ref.setEsquerdo(removerRec(ref.getEsquerdo(), antecessor.getAluno().getMatricula()));
            } else {
                Node child = (ref.getEsquerdo() != null) ? ref.getEsquerdo() : ref.getDireito();
                ref = child;
            }
        }

        return ref;
    }

    private Node maiorValor(Node ref) {
        while (ref.getDireito() != null) {
            ref = ref.getDireito();
        }

        return ref;
    }

    private Node buscarRec(Node ref, int mat) {
        if (ref.getAluno().getMatricula() == mat) {
            return ref;
        } else if (mat < ref.getAluno().getMatricula() && ref.getEsquerdo() != null) {
            System.out.println("Buscando a esquerda de " + ref.getAluno().getMatricula());
            return buscarRec(ref.getEsquerdo(), mat);
        } else if (mat > ref.getAluno().getMatricula() && ref.getDireito() != null) {
            System.out.println("Buscando a direita de " + ref.getAluno().getMatricula());
            return buscarRec(ref.getDireito(), mat);
        } else {
            return null;
        }
    }

    private Node inserirRec(Node ref, Node novo) {
        if (ref == null) {
            ref = novo;
        } else if (novo.getAluno().getMatricula() < ref.getAluno().getMatricula()) {
            ref.setEsquerdo(inserirRec(ref.getEsquerdo(), novo));
        } else if (novo.getAluno().getMatricula() > ref.getAluno().getMatricula()) {
            ref.setDireito(inserirRec(ref.getDireito(), novo));
        }

        setBalanceamento(ref);
        ref = verificarBalanceamento(ref);

        return ref;
    }

    private Node verificarBalanceamento(Node ref) {
        if (ref.getFatorBalanceamento() == 2) {
            if (ref.getEsquerdo().getFatorBalanceamento() > 0) {
                ref = rotacaoDireita(ref);
            } else {
                ref = rotacaoDireitaDupla(ref);
            }
        } else if (ref.getFatorBalanceamento() == -2) {
            if (ref.getDireito().getFatorBalanceamento() < 0) {
                ref = rotacaoEsquerda(ref);
            } else {
                ref = rotacaoEsquerdaDupla(ref);
            }
        }
        setBalanceamento(ref);
        return ref;
    }

    private Node rotacaoDireita(Node ref) {
        Node novo = ref.getEsquerdo();
        ref.setEsquerdo(novo.getDireito());
        novo.setDireito(ref);
        return novo;
    }

    private Node rotacaoDireitaDupla(Node ref) {
        ref.setEsquerdo(rotacaoEsquerda(ref.getEsquerdo()));
        return rotacaoDireita(ref);
    }

    private Node rotacaoEsquerda(Node ref) {
        Node novo = ref.getDireito();
        ref.setDireito(novo.getEsquerdo());
        novo.setEsquerdo(ref);
        return novo;
    }

    private Node rotacaoEsquerdaDupla(Node ref) {
        ref.setDireito(rotacaoDireita(ref.getDireito()));
        return rotacaoEsquerda(ref);
    }

    private void setBalanceamento(Node ref) {
        ref.setFatorBalanceamento(altura(ref.getEsquerdo()) - altura(ref.getDireito()));
    }

    private int altura(Node ref) {
        if (ref == null) {
            return 0;
        } else if (ref.getEsquerdo() == null && ref.getDireito() == null) {
            return 1;
        } else if (ref.getEsquerdo() == null) {
            return 1 + altura(ref.getDireito());
        } else if (ref.getDireito() == null) {
            return 1 + altura(ref.getEsquerdo());
        } else {
            return 1 + Math.max(altura(ref.getEsquerdo()), altura(ref.getDireito()));
        }
    }
}
