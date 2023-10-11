import java.time.LocalTime;
public class Carro extends Veiculo{
  
   
   public Carro(String placa, Cliente cliente,LocalTime horaEntrada) {
      super.placa = placa;
      super.cliente = cliente;
      this.valorTarifa = 0.0666;
      this.horaEntrada=horaEntrada;
      
   }
   
   
}