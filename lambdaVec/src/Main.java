import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

	/* interfacy pro lambdy*/
	public interface IValidity {
		boolean isValid(int i);
	}

	public interface IAlter {
		int alter(int i);
	}

	public interface IMathOperation {
		int mathOperation(int i, int j);
	}

	public static void main(String[] args) {
		final Scanner sc = new Scanner(System.in);
		final String input = sc.nextLine();
		final List<Integer> inputList = Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		while (sc.hasNext()) {
			final int operation = sc.nextInt();
			switch (operation) {
				case 1 -> System.out.println(filter(inputList, i -> i % 11 == 0));
				case 2 -> System.out.println(filter(inputList, i -> (Math.pow(i, 3)) % 10 == 3));/*Číslo končí na 3, pokud jeho modulo 10 je 3 (13-3,153-3..)*/
				case 3 -> System.out.println(alterEach(inputList, i -> i * 2));
				case 4 -> System.out.println(alterEach(inputList, i -> (int) Math.ceil((double) i / 10) * 8)); /*ceil zakrouhluje jenom na jednotky, proto musíme vydělit 10*/
				case 5 -> System.out.println(reduce(inputList, 0, (i, sum) -> sum += i));
				case 6 -> System.out.println(reduce(inputList, 1, (i, product) -> product *= i)); /*původní činitel musí být při získávání produktu jedna*/
				default -> throw new IllegalStateException("Unexpected value: " + operation);
			}
		}
	}

	/*metody*/

	public static ArrayList<Integer> filter(List<Integer> list, IValidity by) {
		final ArrayList<Integer> result = new ArrayList<>();
		for (Integer i : list) {
			if (by.isValid(i)) result.add(i);
		}
		return result;
	}

	public static ArrayList<Integer> alterEach(List<Integer> list, IAlter by) {
		final ArrayList<Integer> result = new ArrayList<>();
		for (Integer i : list) {
			result.add(by.alter(i));
		}
		return result;
	}

	public static Integer reduce(List<Integer> list, Integer startingVal, IMathOperation by) {
		Integer result = startingVal;
		for (Integer i : list) {
			result = by.mathOperation(i, result);
		}
		return result;
	}

}
