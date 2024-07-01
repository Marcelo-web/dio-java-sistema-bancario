package dominio;

import java.time.LocalDate;

import dominio.enums.TipoOperacao;
import dominio.interfaces.IConta;

public class Transacao {

	private LocalDate data;
	private double valor;
	private IConta origem;
	private IConta destino;
	private TipoOperacao operacao;

	public Transacao(LocalDate data, double valor, IConta origem, TipoOperacao operacao) {
		this.data = data;
		this.valor = valor;
		this.origem = origem;
		this.operacao = operacao;
	}

	public Transacao(LocalDate data, double valor, IConta origem, IConta destino, TipoOperacao operacao) {
		this.data = data;
		this.valor = valor;
		this.origem = origem;
		this.destino = destino;
		this.operacao = operacao;
	}

	public LocalDate getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	public IConta getOrigem() {
		return origem;
	}

	public IConta getDestino() {
		return destino;
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}
}
