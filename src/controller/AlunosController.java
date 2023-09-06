/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import model.Aluno;

/**
 *
 * @author kaior
 */
public class AlunosController {
    
    public ArrayList<Aluno> lerTxt(FileInputStream arquivoTxt) {
        Scanner lerArquivo = new Scanner(arquivoTxt, "UTF-8");
        ArrayList<Aluno> alunos = new ArrayList<>();
        while (lerArquivo.hasNext()) {            
            String linha = lerArquivo.nextLine();
            if(linha != null && !linha.isEmpty()) {
                String dados[] = linha.split("\\|");
                Aluno aluno = new Aluno();
                aluno.setMatricula(Integer.parseInt(dados[0]));
                aluno.setNome(dados[2]);
                aluno.setNota1(Double.valueOf(dados[2]));
                aluno.setNota2(Double.valueOf(dados[3]));
                aluno.setNota3(Double.valueOf(dados[4]));
                aluno.setMedia(Double.valueOf(dados[2]), Double.valueOf(dados[3]), Double.valueOf(dados[4]));
                alunos.add(aluno);
            }
        }
        return alunos;
    }
}
