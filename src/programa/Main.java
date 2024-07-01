package programa;

import dominio.Cliente;
import dominio.Conta;
import dominio.ContaCorrente;
import dominio.ContaPoupanca;
import dominio.exceptions.ErrorOperationException;

public class Main {

	public static void main(String[] args) {

		try {

			Cliente maria = new Cliente();
			maria.setNome("Maria");

			Cliente joao = new Cliente();
			joao.setNome("João");

			Conta cc_maria = new ContaCorrente(maria);
			Conta cp_joao = new ContaPoupanca(joao);

			System.out.println("Depósito no valor de 1000,00 na conta de Maria.");
			System.out.println("Depósito no valor de 850,00 na conta de João.\n");
			cc_maria.depositar(1000);
			cp_joao.depositar(850);

			cc_maria.imprimirExtrato();
			cp_joao.imprimirExtrato();

			System.out.println("Transferência no valor de 50,00 da conta de Maria para a conta do João.\n");
			cc_maria.transferir(50, cp_joao);
			cc_maria.imprimirExtrato();
			cp_joao.imprimirExtrato();

			System.out.println("Exibindo histórico das transações das contas de Maria e João.\n");
			cc_maria.exibirHistoricoTransacoes();
			cp_joao.exibirHistoricoTransacoes();

			System.out.println("Tentativa de saque de valor maior que o saldo na conta do João:");
			System.out.println();
			cp_joao.sacar(1000);
			cp_joao.imprimirExtrato();

			System.out.println("Saque de valor de 300,00 da conta de João.\n");
			cp_joao.sacar(300);
			cp_joao.exibirHistoricoTransacoes();
			cp_joao.imprimirExtrato();

			System.out.println("Transferência de 100,00 da conta de João para a conta de Maria.\n");
			cp_joao.transferir(100, cc_maria);
			cp_joao.imprimirExtrato();
			cc_maria.imprimirExtrato();

			cp_joao.exibirHistoricoTransacoes();

			System.out.println("Tentativa de transferência de valor para a mesma conta com lançamento e captura de exceção:\n");
			cp_joao.transferir(2000, cp_joao);
			
		} catch (ErrorOperationException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado: " + ex.getMessage());
		}

	}
}
