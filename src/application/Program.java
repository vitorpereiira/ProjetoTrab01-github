package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.ContratoHora;
import entities.Departamento;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner leia = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Digite o nome do setor do trabalhador: ");
		String departamentoNome = leia.nextLine();
		System.out.println("Insira os dados do trabalhador ");
		System.out.print("Nome: ");
		String workerName = leia.nextLine();
		System.out.print("Level do trabalhador: ");
		String workerLevel = leia.nextLine();
		System.out.print("Salario base: ");
		double baseSalary = leia.nextDouble();
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary,
				new Departamento(departamentoNome));

		System.out.println("Quantos contratos tem esse trabalhador ?");
		int n = leia.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Insira os dados do " + i + " contrato: ");
			System.out.print("Digite a data (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(leia.next());
			System.out.print("Valor por hora: ");
			double valorPorHora = leia.nextDouble();
			System.out.print("Duracao (hora): ");
			int hora = leia.nextInt();
			ContratoHora contrato = new ContratoHora(contractDate, valorPorHora, hora);
			worker.addContrato(contrato);
		}
		
		System.out.println();
		System.out.print("Digite o mes e o ano para calcular o salario do trabalhador (MM/YYYY): ");
		String monthAndYear = leia.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		System.out.println("Nome: " + worker.getNome());
		System.out.println("Departamento: "+worker.getDepartamento().getNome());
		System.out.println("Renda para " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

		leia.close();

	}

}
