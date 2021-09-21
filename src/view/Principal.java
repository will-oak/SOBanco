package view;

import java.util.concurrent.Semaphore;

import controller.Conta;
import controller.TransacaoController;

public class Principal {

	public static void main(String[] args) {
		Conta conta = new Conta(1, 50);
		
		Semaphore deposito = new Semaphore(1);
		Semaphore saque = new Semaphore(1);
		
		for (int transacao = 1; transacao <= 20; transacao++) {
			
			TransacaoController novaTransacao = new TransacaoController(conta, deposito, saque);
			novaTransacao.start();
		}
		
	}

}
