package controller;

import java.util.concurrent.Semaphore;

public class TransacaoController extends Thread {
	private Conta conta;
	private Semaphore saque;
	private Semaphore deposito;
	private static int transacoes = 0;

	public TransacaoController(Conta conta, Semaphore saque, Semaphore deposito) {
		this.conta = conta;
		this.saque = saque;
		this.deposito = deposito;
	}

	@Override
	public void run() {

		int tipoTransacao = (int) ((Math.random() * 2) + 1);
		long novoSaldo = 0;

		if (tipoTransacao == 1) {
			try {
				deposito.acquire();
				
				long saldo = conta.getSaldo();
				long quantiaTransacao = (int) ((Math.random() * 5000) + 0.10);
				
				novoSaldo = transacao(saldo, quantiaTransacao);
				
				System.out.println("Depósito de " + quantiaTransacao + " efetuado. Saldo da conta R$ " + novoSaldo + ",00");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				deposito.release();
			}

		} else {
			try {
				saque.acquire();
				
				long saldo = conta.getSaldo();
				long quantiaTransacao = (int) ((Math.random() * 5000) + 0.10) * -1;
				
				novoSaldo = transacao(saldo, quantiaTransacao);
				
				System.out.println("Saque de " + quantiaTransacao + " efetuado. Saldo da conta R$ " + novoSaldo + ",00");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				saque.release();
			}
		}

		conta.setSaldo(novoSaldo);
		
		transacoes++;
		
		if (transacoes >= 20) {
			System.out.println("Saldo final da conta: R$ " + conta.getSaldo() + ",00");
		}
	}

	public long transacao(long saldo, long quantiaTransacao) {
		return saldo + quantiaTransacao;
	}

}
