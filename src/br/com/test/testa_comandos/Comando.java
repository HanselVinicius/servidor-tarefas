package br.com.test.testa_comandos;

public class Comando  implements Comparable<Comando>{

    private String tipo;
    private int prioridade;
    private String params;

    //construtor e getters

    public Comando(String tipo, int prioridade, String params) {
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.params = params;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getParams() {
        return params;
    }

    @Override
    public int compareTo(Comando o) {
        return o.prioridade - prioridade;
    }
}