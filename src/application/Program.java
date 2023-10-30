package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.next();

		System.out.println();

		System.out.println("Total de vendas por vendedor:");

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			Set<String> sellers = new HashSet<>();

			for (Sale sale : list) {
				sellers.add(sale.getSeller());
			}

			System.out.println();
			System.out.println("Total de vendas por vendedor:");

			for (String seller : sellers) {

				double sum = list.stream()
						.filter(x -> x.getSeller().contains(seller))
						.map(x -> x.getTotal())
						.reduce(0.0, (x, y) -> x + y);

				System.out.printf(seller + " - R$ %.2f%n", sum);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}