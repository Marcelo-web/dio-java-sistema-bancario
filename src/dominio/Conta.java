package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dominio.enums.TipoOperacao;
import dominio.exceptions.ErrorOperationException;
import dominio.interfaces.IConta;

public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	private Cliente cliente;
	protected List<Transacao> historicoTransacoes;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.historicoTransacoes = new ArrayList<Transacao>();
	}

	public int getAgencia() {
		return this.agencia;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public void sacar(double valor) {
		if (valor > saldo) {
			System.out.println("Operação de saque inválida! Saldo insuficiente!\n");
			return;
		}
		this.saldo -= valor;
		registrarTransacao(LocalDate.now(), valor, this, TipoOperacao.SAQUE);
	}

	@Override
	public void depositar(double valor) {
		this.saldo += valor;
		registrarTransacao(LocalDate.now(), valor, this, TipoOperacao.DEPÓSITO);
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		Conta contaDestinatario = (Conta) contaDestino;
		if (contaDestinatario.equals(this)) {
			throw new ErrorOperationException(
					"Operação inválida! A transferência deve ser feita entre contas distintas!");
		}
		this.saldo -= valor;
		contaDestinatario.saldo += valor;
		registrarTransacao(LocalDate.now(), valor, this, contaDestino, TipoOperacao.TRANSFERÊNCIA);
	}

	protected void registrarTransacao(LocalDate data, double valor, IConta origem, TipoOperacao operacao) {
		Transacao transacao = new Transacao(data, valor, origem, operacao);
		historicoTransacoes.add(transacao);
	}

	protected void registrarTransacao(LocalDate data, double valor, IConta origem, IConta destino,
			TipoOperacao operacao) {
		Transacao transacao = new Transacao(data, valor, origem, destino, operacao);
		historicoTransacoes.add(transacao);
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agência: %d", this.agencia));
		System.out.println(String.format("Número: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		System.out.println();
	}

	public void exibirHistoricoTransacoes() {
		System.out.println("=== Histórico de Transações ===");

		if (!historicoTransacoes.isEmpty()) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			for (Transacao transacao : historicoTransacoes) {
				String tipoTransacao = transacao.getOperacao().name();
				System.out.println("Titular: " + cliente.getNome());
				System.out.println("Tipo de transação: " + tipoTransacao);
				System.out.println("Data: " + transacao.getData().format(formatter));
				System.out.println("Valor: " + String.format("%.2f", transacao.getValor()));
				System.out.println("Conta origem: " + ((Conta) transacao.getOrigem()).getCliente().getNome());

				if (tipoTransacao.equalsIgnoreCase("transferência")) {
					System.out.println("Conta destino: " + ((Conta) transacao.getDestino()).getCliente().getNome());
				}
				System.out.println();
			}
			System.out.println();
		} else {
			System.out.println("O cliente " + cliente.getNome() + " ainda não realizou transações bancárias");
		}
	}
}
