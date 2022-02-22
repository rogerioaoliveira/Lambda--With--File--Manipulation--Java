package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				String[] filds = line.split(",");
				String neme = filds[0];
				String email = filds[1];
				double salary = Double.parseDouble(filds[2]);

				line = br.readLine();

				Employee employ = new Employee(neme, email, salary);
				list.add(employ);
			}

			System.out.print("Enter salary: ");
			double enterSalary = sc.nextDouble();

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			System.out.println("Email of people whose salary is more than " + enterSalary + ":");

			List<String> emails = list.stream().filter(p -> p.getSalary() > enterSalary).map(p -> p.getEmail())
					.sorted(comp).collect(Collectors.toList());

			emails.forEach(System.out::println);

			double salaryM = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", salaryM));

		} catch (IOException e) {
			System.out.println("error: " + e.getMessage());
		}

		sc.close();

	}

}
