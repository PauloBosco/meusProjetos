import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
// Decimal importado para formatar o faturamento com 2 casas decimais
import java.text.DecimalFormat;



public class Estacionamento
{
   private String nome;
   private int qtidadeVagas;
   ArrayList<Cliente> listaClientes;
   ArrayList<Funcionario> listaFuncionarios;
   ArrayList<Veiculo> listaVeiculos;
   private static double totalFaturamento;
   private DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   private static int totalCarroDia;
   private static double horaTotalnoEstacionamento;
   private static int totalCarrosQueSairam;
   private DecimalFormat df = new DecimalFormat("#.00");
   
   
   public Estacionamento(String nome, int qtidadeVagas){
       this.nome = nome;
       this.qtidadeVagas = qtidadeVagas;
       listaClientes = new ArrayList<>();
       listaFuncionarios = new ArrayList<>();
       listaVeiculos = new ArrayList<>();
   }
   
   public String getNome(){
       return nome;
   }
   
   public void setNome(String nome){
       this.nome = nome;
   }
   
   public int getQtidadeVagas(){
       return qtidadeVagas;
   }
   public int getTotalCarroDia(){
       return this.totalCarroDia;
   }
   public void setTotalCarroDia(int totalCarroDia){
       this.totalCarroDia=totalCarroDia;
   }
   public int getTotalCarrosQueSairam(){
       return this.totalCarrosQueSairam;
   }
   public void setTotalCarrosQueSairam(int totalCarrosQueSairam){
       this.totalCarrosQueSairam=totalCarrosQueSairam;
   }
   public double getHoraTotalnoEstacionamento(){
       return this.horaTotalnoEstacionamento;
   }
   public void setHoraTotalnoEstacionamento(double horaTotalnoEstacionamento){
       this.horaTotalnoEstacionamento=horaTotalnoEstacionamento;
   }
   public void setQtidadeVagas(int qtidadeVagas){
       this.qtidadeVagas = qtidadeVagas;
   }
   
   public ArrayList<Cliente> getListaClientes(){
       return listaClientes;
   }
   
   public void addCliente(Cliente c){
       listaClientes.add(c);
   }
   public void removeCliente( Cliente c){
       listaClientes.remove(c);
   }
   public ArrayList<Funcionario> getListaFuncionario(){
       return listaFuncionarios;
   }
    
   
   public ArrayList<Veiculo> getListaVeiculos(){
       return listaVeiculos;
   }
   
   
   public void addVeiculo(Veiculo v){
       listaVeiculos.add(v);
   }
   public void removeVeiculo(Veiculo v){
       listaVeiculos.remove(v);
   }
   
   public double calculaFaturamento(Veiculo v){
       
           this.totalFaturamento+=v.getTotalPago();
       
       
       return this.totalFaturamento;
   }
   public double calculaMediaHoras(){

       return this.horaTotalnoEstacionamento/this.totalCarrosQueSairam;
   }

   
   public String getRelatorio(Funcionario func){
       LocalDate data = LocalDate.now();
       
       return "--------------\nEstacionamento: "+this.nome
                            +"\nTotal de vagas: "+this.getQtidadeVagas()
                            +"\nVagas Disponíveis Agora: "+func.getVagasDisponiveis()
                            +"\n########\nRelatório dia: "+data.format(DateFormatter)
                            +"\nTotal de carros do dia:"+this.totalCarroDia
                            +"\nFaturamento total: R$"+df.format(this.totalFaturamento)
                            +"\nMédia de permanência em minutos: "+(int)calculaMediaHoras()+"min";
   }
   
}
