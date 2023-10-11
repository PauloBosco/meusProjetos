package br.com.lanhouse.model.servicos;

import br.com.lanhouse.model.equipamento.Computador;
import br.com.lanhouse.model.pessoas.Cliente;

import java.time.LocalDateTime;

public class Uso {

    private  int idUso;
    private Cliente idUsuario;
    private Computador computador;
    private Servico servico;
    private double  valorHora;
    private LocalDateTime diaLogin;
    private int qtidadeMinutosTempo;

   public Uso(){
       
   }

    public Uso(int idUso, Cliente idUsuario, Computador computador, double valorHora, int qtidadeMinutosTempo) {
        this.idUso = idUso;
        this.idUsuario = idUsuario;
        this.computador = computador;
        this.servico = null;
        this.valorHora = valorHora;
        this.diaLogin = LocalDateTime.now();
        this.qtidadeMinutosTempo = qtidadeMinutosTempo;
    }

//    public Uso(int idUso, int idUsuario, Computador computador, Servico servico, double valorHora, int qtidadeMinutosTempo) {
//        this.idUso = idUso;
//        this.idUsuario = idUsuario;
//        this.computador = computador;
//        this.servico = servico;
//        this.valorHora = valorHora;
//        this.qtidadeMinutosTempo = qtidadeMinutosTempo;
//        this.diaLogin = LocalDate.now();
//    }

    public int getIdUso() {
        return idUso;
    }

    public void setIdUso(int idUso) {
        this.idUso = idUso;
    }

    public Cliente getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Cliente idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public LocalDateTime getDiaLogin() {
        return diaLogin;
    }

    public void setDiaLogin(LocalDateTime diaLogin) {
        this.diaLogin = diaLogin;
    }

    public int getQtidadeMinutosTempo() {
        return qtidadeMinutosTempo;
    }

    public void setQtidadeMinutosTempo(int qtidadeMinutosTempo) {
        this.qtidadeMinutosTempo = qtidadeMinutosTempo;
    }
}
