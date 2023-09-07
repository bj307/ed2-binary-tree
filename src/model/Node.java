/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kaior
 */
public class Node {
    Aluno aluno;
    Node esquerdo, direito, pai;
    int fatorBalanceamento;

    public Node() {
    }

    public Node(Aluno aluno) {
        this.aluno = aluno;
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
        this.fatorBalanceamento = 0;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Node getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(Node esquerdo) {
        this.esquerdo = esquerdo;
    }

    public Node getDireito() {
        return direito;
    }

    public void setDireito(Node direito) {
        this.direito = direito;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(int fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }
	
    
}
