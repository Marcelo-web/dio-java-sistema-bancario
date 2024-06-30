package dominio;

public class Main {

	public static void main(String[] args) {
      
		Cliente maria = new Cliente();
		maria.setNome("Maria");
		
		Cliente joao = new Cliente();
		joao.setNome("João");
		
		
		Conta cc = new ContaCorrente(maria);
		Conta poupanca = new ContaPoupanca(joao);
		
		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		Banco banco = new Banco();
		banco.adicionarConta(cc);
		banco.adicionarConta(poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
		
        banco.removerConta(cc);
        banco.listarContas();     
        banco.listarClientes();
        
	}
}
