

public class Cliente extends Pessoa
{
    private Veiculo veiculo;
    
    public Cliente(String nome){
        super.nome = nome;
    }
    
   public String getNome(){
       return nome;
   }
   
   public void setNome(String nome){
       this.nome = nome;
   } 
    
}
