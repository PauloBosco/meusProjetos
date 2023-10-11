
import java.time.LocalTime;
public abstract class Veiculo{
   protected String placa;
   protected Cliente cliente;
   protected double valorTarifa;
   protected LocalTime horaEntrada;
   protected LocalTime horaSaida;
   protected double totalPago;
   
   public Veiculo(){
       
       
   }
   
   public String getPlaca(){
      return placa;
   }
   
   public void setPlaca(String placa){
      this.placa = placa;
   }
   
   public Cliente getCliente(){
      return cliente;
   }
   
   public void setCliente(Cliente cliente){
      this.cliente = cliente;
   }
   
   public double getValorTarifa(){
      return valorTarifa;
   }
   
   public void setValorTarifa(double valorTarifa){
      this.valorTarifa = valorTarifa;
   }
   public void setHoraEntrada(LocalTime horaEntrada){
       this.horaEntrada=horaEntrada;
   }
   public LocalTime getHoraEntrada(){
       return this.horaEntrada;
   }
   public void setHoraSaida(LocalTime horaSaida){
       this.horaSaida=horaSaida;
   }
   public LocalTime getHoraSaida(){
       return this.horaSaida;
   }
   public double getTotalPago(){
       return this.totalPago;
   }
   public void setTotalPago(double totalPago){
       this.totalPago=totalPago;
   }
   public String getNomeCliente(){
       return cliente.getNome();
   }
}

