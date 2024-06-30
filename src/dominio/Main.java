package dominio;

public class Main {

	public static void main(String[] args) {
      
		Cliente maria = new Cliente();
		maria.setNome("Maria");
		
		IConta cc = new ContaCorrente(maria);
		IConta poupanca = new ContaPoupanca(maria);
		
		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
		
	}
}
