import java.time.LocalTime;
public class Caminhao extends Veiculo{
   
   
   public Caminhao(String placa, Cliente cliente, LocalTime horaEntrada) {
      this.placa = placa;
      this.cliente = cliente;
      this.valorTarifa = 0.1666;
      this.horaEntrada=horaEntrada;
      
   }
}