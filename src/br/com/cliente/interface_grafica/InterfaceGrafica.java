package br.com.cliente.interface_grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class InterfaceGrafica {

    private JFrame janela;
    private JTextArea campoDeResposta;
    private final PrintStream saida;

    public InterfaceGrafica(Socket socket) throws IOException {
        this.saida = new PrintStream(socket.getOutputStream());
    }


    public void montaTela(){
        preparaJanela();
        preparaBotoes();
        preparaCampoDeTexto();
        mostraJanela();

    }

    public void imprime(String texto){
        String novaLinha = System.getProperty("line.separator");
        campoDeResposta.append(texto + novaLinha);
    }

    private void mostraJanela() {
        janela.setLayout(new GridLayout(2,1));
        janela.pack();
        janela.setVisible(true);
    }

    private void preparaCampoDeTexto() {
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Resposta Servidor"));
        BoxLayout layout = new BoxLayout(container,BoxLayout.X_AXIS);
        container.setLayout(layout);
        container.add(criaCampoResposta());
        janela.add(container);
    }

    private void preparaBotoes() {
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Comandos"));

        GridLayout layout = new GridLayout(0,4,20,20);
        container.setLayout(layout);

        List<JButton> botoes = cria4Botoes();
        for (JButton botao : botoes){
            container.add(botao);
        }
        janela.add(container);

    }

    private List<JButton> cria4Botoes() {
        List<JButton> botoes = new ArrayList<>();

        for (int i = 0; i < 3 ; i++){
            final int numeroComando = i + 1;
            final JButton botao = new JButton("Enviar c " +numeroComando );
            final String comando = "c" + numeroComando;

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saida.println(comando);
                }
            });
            botoes.add(botao);
        }
        
        JButton botao = new JButton("Terminar servidor (fim)");
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saida.println("fim");
            }
        });
        botoes.add(botao);
        return botoes;
    }

    private void preparaJanela() {
        janela = new JFrame("Cliente Servidor-Tarefas");
        janela.setMinimumSize(new Dimension(600,400));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    private JScrollPane criaCampoResposta(){
        campoDeResposta = new JTextArea();
        campoDeResposta.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(campoDeResposta);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scroll;
    }

}
