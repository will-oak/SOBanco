package controller;

public class Conta {
	
	public int codigoConta;
	private long saldoConta;

	public Conta(int codigoConta, long saldoConta) {
		this.codigoConta = codigoConta;
		this.saldoConta = saldoConta;
	}

	
	public long getSaldo() {
		return saldoConta;
	}
	
	public void setSaldo(long quantia) {
		saldoConta = quantia;
	}
}
