import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;


public class Main
{
    public static void main(String[] args){
        Scanner le = new Scanner(System.in);
        boolean a = true;
        Estacionamento estacionamento = new Estacionamento("Citipark", 35);
        Funcionario func1 = new Funcionario("Zezinho","Atendente",estacionamento);
        
        //fazer as perguntas com Scanner
        func1.entradaVeiculo("Carro", "XXXX-1919","Andrei");
        func1.entradaVeiculo("Moto", "XXXX-2020","Marcos");
        func1.entradaVeiculo("Caminhao", "XXXX-1717","Joana");
        func1.entradaVeiculo("Moto", "XXXX-2121","David");
        
        
        
        while(a){
            int opcao = Menu(le);
            
                switch (opcao) {
                case 1:
                    cadastraVeiculo(func1);
                    break;
                case 2:
                    System.out.println(saidaVeiculo(func1));
                    break;
                case 3:
                    retornaListaVeiculos(estacionamento);
                    break;
                case 4:
                    geraRelatorio(estacionamento,func1);
                    break;
                case 5:
                    a = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    
    public static int Menu(Scanner a){        
        System.out.println("#### CitiPark Estacionamento #####");
        System.out.println("#### Digite uma das opções abaixo: ");
        System.out.println("#### [1] - Entrada de Veiculos ");
        System.out.println("#### [2] - Saída de Veiculos");
        System.out.println("#### [3] - Lista de Veiculos");
        System.out.println("#### [4] - Gerar Relatório Diário ");
        System.out.println("#### [5] - Sair ");
        return a.nextInt();
        
    }
    
    public static void cadastraVeiculo(Funcionario f){
        Scanner le = new Scanner(System.in);
        System.out.println("###### Cadastrando Veículo ######");
        System.out.println("Digite o tipo do veículo (Carro,Moto,Caminhao): ");
        String tipoVeiculo = le.next();
        System.out.println("Digite a Placa do veículo: ");
        String placaVeiculo = le.next();
        System.out.println("Digite o nome do Cliente: ");
        String nomeCliente = le.next();
        f.entradaVeiculo(tipoVeiculo,placaVeiculo,nomeCliente);
        System.out.println("Deseja continuar cadastrando ? (S/N) ");
        String resp = le.next();
        if( resp.equalsIgnoreCase("S")){
            cadastraVeiculo(f);
        }
    }
    
    public static String saidaVeiculo(Funcionario f){
        Scanner le = new Scanner(System.in);
        System.out.println("###### Saída de Veículo ######");
        System.out.println("Digite a Placa do veículo: ");
        String placaVeiculo = le.next();
        return f.saidaVeiculo(placaVeiculo);
    }
    
    public static void retornaListaVeiculos(Estacionamento e){
        ArrayList<Veiculo> veiculos = e.getListaVeiculos();
        for (Veiculo v : veiculos) {
            System.out.println(v.getPlaca());
        }
    }
    
    public static void geraRelatorio(Estacionamento e, Funcionario f){
        System.out.println(e.getRelatorio(f));
        System.out.println("--------------");
    }
    
}
