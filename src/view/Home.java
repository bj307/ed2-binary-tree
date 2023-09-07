/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.formdev.flatlaf.IntelliJTheme;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.Aluno;
import model.ArvoreAvl;
import model.Node;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author kaior
 */
public class Home extends javax.swing.JFrame {

    ArrayList<Aluno> alunos;
    ArvoreAvl avl = new ArvoreAvl();
    InserirAluno ia = new InserirAluno(this);

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Binary Tree - by Farley and Kaio");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int escolha = JOptionPane.showOptionDialog(
                        null,
                        "As informações dos alunos foram salvas em Saida.txt",
                        "Confirmação de Saída",
                        JOptionPane.OK_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"OK"},
                        "OK"
                );

                if (escolha == 0) {
                    salvarTxt();
                    System.exit(0);
                }
            }
        });

        StyledDocument doc = arvore.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.alunos = lerTxt();
        popularArvore(this.alunos);
        mostrarArvore();
    }

    private ArrayList<Aluno> lerTxt() {
        FileInputStream arquivoTxt = null;
        try {
            // Abra o arquivo chamado "entrada.txt" localizado na raiz do projeto
            arquivoTxt = new FileInputStream("entrada.txt");
            Scanner lerArquivo = new Scanner(arquivoTxt, "UTF-8");
            ArrayList<Aluno> alunos = new ArrayList<>();
            while (lerArquivo.hasNext()) {
                String linha = lerArquivo.nextLine();
                if (linha != null && !linha.isEmpty()) {
                    String dados[] = linha.split("\\|");
                    Aluno aluno = new Aluno();
                    aluno.setMatricula(Integer.parseInt(dados[0]));
                    aluno.setNome(dados[1]);
                    double nota1 = Double.parseDouble(dados[2].replace(",", "."));
                    double nota2 = Double.parseDouble(dados[3].replace(",", "."));
                    double nota3 = Double.parseDouble(dados[4].replace(",", "."));

                    aluno.setNota1(nota1);
                    aluno.setNota2(nota2);
                    aluno.setNota3(nota3);
                    aluno.setMedia(nota1, nota2, nota3);
                    alunos.add(aluno);
                }
            }
            return alunos;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (arquivoTxt != null) {
                    arquivoTxt.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void popularArvore(ArrayList<Aluno> array) {
        for (Aluno aluno : array) {
            avl.inserir(aluno);
        }
    }

    private void salvarTxt() {
        ArrayList<Aluno> alunos = avl.emOrdem();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saida.txt"))) {
            for (Aluno aluno : alunos) {
                // Escreva os dados no formato desejado no arquivo
                writer.write(aluno.getMatricula() + "|" + aluno.getNome() + "|" + aluno.getNota1() + "|" + aluno.getNota2() + "|" + aluno.getNota3() + "|" + aluno.getMedia());
                writer.newLine(); // Adicione uma quebra de linha
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarArvore() {
        avl.exibirArvore(arvore);
    }

    public void imprimir() {
        ArrayList<Aluno> alunos = avl.emOrdem();
        StringBuilder resultado = new StringBuilder();
        for (Aluno aluno : alunos) {
            resultado.append(aluno.toString() + "\n");
        }
        arvore.setText(resultado.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarBtn = new javax.swing.JButton();
        inserirBtn = new javax.swing.JButton();
        removerBtn = new javax.swing.JButton();
        inputMat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        alunoBuscado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        arvore = new javax.swing.JTextPane();
        btnImprimir = new javax.swing.JButton();
        btnArvore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(800, 450));

        buscarBtn.setText("Buscar Aluno");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        inserirBtn.setText("Inserir Aluno");
        inserirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirBtnActionPerformed(evt);
            }
        });

        removerBtn.setText("Remover Aluno");
        removerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerBtnActionPerformed(evt);
            }
        });

        inputMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMatActionPerformed(evt);
            }
        });

        jLabel1.setText("Número de matrícula");

        alunoBuscado.setEditable(false);

        jLabel2.setText("Aluno buscado");

        arvore.setEditable(false);
        jScrollPane2.setViewportView(arvore);

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnArvore.setText("Árvore");
        btnArvore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArvoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnArvore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir))
                    .addComponent(inputMat)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(inserirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(removerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(alunoBuscado)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inserirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alunoBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImprimir)
                        .addComponent(btnArvore)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        // TODO add your handling code here:
        int mat = Integer.parseInt(inputMat.getText());
        Node no = avl.buscar(mat);
        if (no != null) {
            alunoBuscado.setText(no.getAluno().toString());
        } else {
            alunoBuscado.setText("Aluno não encontrado.");
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void inputMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMatActionPerformed

    private void removerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerBtnActionPerformed
        // TODO add your handling code here:
        int mat = Integer.parseInt(inputMat.getText());
        avl.remover(mat);
        mostrarArvore();
    }//GEN-LAST:event_removerBtnActionPerformed

    private void inserirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirBtnActionPerformed
        // TODO add your handling code here:
        ia.setVisible(true);
    }//GEN-LAST:event_inserirBtnActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnArvoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArvoreActionPerformed
        // TODO add your handling code here:
        mostrarArvore();
    }//GEN-LAST:event_btnArvoreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        IntelliJTheme.setup(Home.class.getResourceAsStream("/Gradianto_deep_ocean.theme.json"));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alunoBuscado;
    private javax.swing.JTextPane arvore;
    private javax.swing.JButton btnArvore;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField inputMat;
    private javax.swing.JButton inserirBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removerBtn;
    // End of variables declaration//GEN-END:variables
}
