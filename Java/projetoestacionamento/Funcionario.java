import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
// Decimal importado para formatar o faturamento com 2 casas decimais

import java.text.DecimalFormat;

//temporal.ChronoUnit utilizado para fazer contas com Horas
import java.time.temporal.ChronoUnit;




public class Funcionario extends Pessoa
{
    private String cargo;
    private Estacionamento estacionamento;
    private Veiculo veiculo;
    //atributo criado para formatar a hora
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat("#.00");
    
    public Funcionario(String nome, String cargo, Estacionamento estacionamento){
        super.nome = nome;
        this.cargo = cargo;
        this.estacionamento = estacionamento;
        
        
    }
    
    public String getNome(){
       return nome;
    }
   
    public void setNome(String nome){
       this.nome = nome;
    }
    
    public String getCargo(){
       return cargo;
    }
   
    public void seCargo(String cargo){
       this.cargo = cargo;
    }    
   
    
    public int getVagasDisponiveis(){
       
       return estacionamento.getQtidadeVagas() - estacionamento.getListaClientes().size();
   }
   
   public void entradaVeiculo(String tipo,String placa,String nomeCliente){
       Cliente cliente = new Cliente(nomeCliente);
       estacionamento.addCliente(cliente);
       estacionamento.setTotalCarroDia(estacionamento.getTotalCarroDia()+1);
       
       if(tipo.equalsIgnoreCase("Carro")){
           
           Veiculo v = new Carro(placa,cliente,pegaHora());
           estacionamento.addVeiculo(v);
       }
       if(tipo.equalsIgnoreCase("Moto")){
           Veiculo v = new Moto(placa,cliente,pegaHora());
           estacionamento.addVeiculo(v);
       }
       else{
           Veiculo v = new Caminhao(placa,cliente,pegaHora());
           estacionamento.addVeiculo(v);
           
       }
   }
   
   public String  saidaVeiculo(String placa){
       estacionamento.setTotalCarrosQueSairam(estacionamento.getTotalCarrosQueSairam()+1);
       for(Veiculo v:estacionamento.getListaVeiculos()){
           if(v.getPlaca().equalsIgnoreCase(placa)){
               
               estacionamento.removeVeiculo(v);
               estacionamento.removeCliente(v.getCliente());
               v.setHoraSaida(LocalTime.of(15,15,00));
               
               // subtraçao das duas horas, conversao para double para calculo em reais
               double horasNoEstacionamento= (double)v.getHoraEntrada().until(v.getHoraSaida(),ChronoUnit.HOURS)*60+(double)v.getHoraEntrada().until(v.getHoraSaida(),ChronoUnit.MINUTES)%60;
               return calculaTarifa(horasNoEstacionamento,v);
           }
       }
       return "carro nao encontrado";
   }
   
   public String calculaTarifa(double horasNoEstacionamento,Veiculo v){
        
        LocalDate data = LocalDate.now();
       if(v instanceof Carro){
           if(horasNoEstacionamento<=60){
               
               v.setTotalPago(4.0);
           }
           else{
            v.setTotalPago(horasNoEstacionamento*v.getValorTarifa());               
           }

            estacionamento.calculaFaturamento(v);
            estacionamento.setHoraTotalnoEstacionamento(estacionamento.getHoraTotalnoEstacionamento()+horasNoEstacionamento);
           return "-----------\nNome Cliente: "+v.getNomeCliente()
                            +"\nTipo:"+v.getClass()
                            +"\nHora Entrada: "+data.format(DateFormatter)+" "+v.getHoraEntrada().format(formatter)
                            +"\nHora Saida: " +v.getHoraSaida()
                            +"\nTempo estacionado: "+(int)horasNoEstacionamento/60+"h e "+(int)horasNoEstacionamento%60+"min"
                            +"\nPagar: R$"+df.format(v.getTotalPago());
       }
       else if(v instanceof Moto){
           if(horasNoEstacionamento<=60){
               
               v.setTotalPago(3.0);
           }
           else{
            v.setTotalPago(horasNoEstacionamento*v.getValorTarifa());               
           }
            estacionamento.calculaFaturamento(v);
           return "-----------\nNome Cliente: "+v.getNomeCliente()
                            +"\nTipo:"+v.getClass()
                            +"\nHora Entrada: "+data.format(DateFormatter)+" "+v.getHoraEntrada().format(formatter)
                            +"\nHora Saida: " +v.getHoraSaida()
                            +"\nTempo estacionado: "+(int)horasNoEstacionamento/60+"h "+(int)horasNoEstacionamento%60+"min"
                            +"\nPagar: R$"+df.format(v.getTotalPago());
       }
       else{
            if(horasNoEstacionamento<=60){
               
               v.setTotalPago(10.0);
           }
           else{
            v.setTotalPago(horasNoEstacionamento*v.getValorTarifa());               
           }
            estacionamento.calculaFaturamento(v);
            return "-----------\nNome Cliente: "+v.getNomeCliente()
                             +"\nTipo:"+v.getClass()
                             +"\nHora Entrada: "+data.format(DateFormatter)+" "+v.getHoraEntrada().format(formatter)
                             +"\nHora Saida: " +v.getHoraSaida()
                             +"\nTempo estacionado: "+(int)horasNoEstacionamento/60+"h "+(int)horasNoEstacionamento%60+"min"
                             +"\nPagar: R$"+df.format(v.getTotalPago());
       }
       
       
    }
    
   public LocalTime pegaHora(){
       LocalTime horaAtual = LocalTime.now();
    return horaAtual;
    }
    
}
