package dominio;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private static final String BANCO = "ABC";

	private String nome;
	private List<Conta> contas;
	
	public Banco() {
		this.contas = new ArrayList<Conta>();
		this.nome = BANCO;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public void removerConta(Conta conta) {
		if(!contas.remove(conta)) {
			throw new ErrorOperationException("Operação inválida - Conta não encontrada!");
		}
	}
	
	public void listarContas() {
		System.out.println("=== Lista de Contas Bancárias ===");
		System.out.println("Banco: " + this.nome);
		System.out.println();
		if(!contas.isEmpty()) {
			for(Conta conta : contas) {
				String tipoConta = conta.getClass()
						.getSimpleName()
						.equalsIgnoreCase("contacorrente") ? "Corrente" : "Poupança";
				
				System.out.println("Agência: " + conta.agencia);
				System.out.println("Número: " + conta.numero);
				System.out.println("Conta: " + tipoConta);
				System.out.println();
			}
		} else {
			System.out.println("Não existem contas cadastradas!");
		}
	}
}
