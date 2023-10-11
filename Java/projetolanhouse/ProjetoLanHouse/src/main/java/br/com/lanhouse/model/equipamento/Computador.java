package br.com.lanhouse.model.equipamento;

public class Computador {
    private int idComputador;
    private String processador;
    private int memoriaRam;
    private int polegadasMonitor;
    private boolean disponivel;

    public Computador() {
    }

    public Computador(int idComputador, String processador, int memoriaRam, int polegadasMonitor) {
        this.idComputador = idComputador;
        this.processador = processador;
        this.memoriaRam = memoriaRam;
        this.polegadasMonitor = polegadasMonitor;
    }

    public int getId() {
        return idComputador;
    }

    public void setId(int id) {
        this.idComputador = id;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public int getPolegadasMonitor() {
        return polegadasMonitor;
    }

    public void setPolegadasMonitor(int polegadasMonitor) {
        this.polegadasMonitor = polegadasMonitor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
