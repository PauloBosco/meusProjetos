import java.time.LocalTime;
public class Moto extends Veiculo{
   
   
   public Moto(String placa, Cliente cliente,LocalTime horaEntrada) {
      this.placa = placa;
      this.cliente = cliente;
      this.valorTarifa = 0.05;
      this.horaEntrada=horaEntrada;
      
   }
}