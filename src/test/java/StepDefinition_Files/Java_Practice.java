package StepDefinition_Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Java_Practice {

	// This class contains 14 functionalities:
	// 1. Check if a number is Prime
	// 2. Check if a number is a Palindrome
	// 3. Reverse a String
	// 4. Count occurrence of each character in a String
	// 5. Swap two numbers (without third variable)
	// 6. Sum of digits of a number
	// 7. Generate Fibonacci series up to n terms
	// 8. Reverse a number
	// 9. Check if a number is Even or Odd
	// 10. Generate a random number
	// 11. Find duplicates in an array
	// 12. Check Armstrong number
	// 13. Reverse an array of Strings
	// 14. Count occurrences of a specific character in a string

	public static boolean isPrime(int num) {
		if (num < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isPalindrome(int num) {
		int original = num, reversed = 0;
		while (num != 0) {
			reversed = reversed * 10 + num % 10;
			num /= 10;
		}
		return original == reversed;
	}

	public static String reverseString(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	public static void countCharOccurrences(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		System.out.println("Character occurrences: " + map);
	}

	public static void swapNumbers(int a, int b) {
		System.out.println("Before Swap: a = " + a + ", b = " + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("After Swap: a = " + a + ", b = " + b);
	}

	public static int sumOfDigits(int num) {
		int sum = 0;
		while (num != 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	public static void fibonacciSeries(int n) {
		int a = 0, b = 1;
		System.out.print("Fibonacci Series: ");
		for (int i = 0; i < n; i++) {
			System.out.print(a + " ");
			int next = a + b;
			a = b;
			b = next;
		}
		System.out.println();
	}

	public static int reverseNumber(int num) {
		int reversed = 0;
		while (num != 0) {
			reversed = reversed * 10 + num % 10;
			num /= 10;
		}
		return reversed;
	}

	public static String evenOrOdd(int num) {
		return num % 2 == 0 ? "Even" : "Odd";
	}

	public static int generateRandomNumber(int bound) {
		Random rand = new Random();
		return rand.nextInt(bound);
	}

	public static void findDuplicates(int[] Num) {
		Set<Integer> seen = new HashSet<>();
		Set<Integer> duplicates = new HashSet<>();
		for (int num : Num) {
			if (!seen.add(num)) {
				duplicates.add(num);
			}
		}
		System.out.println("Duplicates: " + duplicates);
	}

	public static boolean isArmstrong(int num) {
		int original = num, result = 0, n = String.valueOf(num).length();
		while (num != 0) {
			int digit = num % 10;
			result += Math.pow(digit, n);
			num /= 10;
		}
		return result == original;
	}

	public static void reverseArray(String[] arr) {
		int left = 0, right = arr.length - 1;
		while (left < right) {
			String temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
		System.out.println("Reversed Array: ");
		for (String s : arr) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	public static void countSpecificChar(String input, char target) {
		int count = 0;
		for (char c : input.toCharArray()) {
			if (c == target) count++;
		}
		System.out.println("Character '" + target + "' occurred " + count + " times.");
	}

	public static void main(String[] args) {
		System.out.println("Is 17 Prime? " + isPrime(17));
		System.out.println("Is 121 Palindrome? " + isPalindrome(121));
		System.out.println("Reversed 'hello': " + reverseString("hello"));
		countCharOccurrences("banana");
		swapNumbers(10, 20);
		System.out.println("Sum of digits in 1234: " + sumOfDigits(1234));
		fibonacciSeries(7);
		System.out.println("Reversed number 54321: " + reverseNumber(54321));
		System.out.println("7 is " + evenOrOdd(7));
		System.out.println("Random number (0-99): " + generateRandomNumber(1000));
		findDuplicates(new int[]{1, 2, 3, 2, 4, 5, 1});
		System.out.println("Is 153 Armstrong? " + isArmstrong(153));
		reverseArray(new String[]{"Java", "is", "fun"});
		countSpecificChar("banana", 'l');

		List<String> value = new ArrayList<>();
		value.add("India");
		value.add("Canada");

		Set<String> value1 = new HashSet<>();
		value1.add("Rexdale");
		value1.add("Etobicoke");

		Map<String, String> value2 = new HashMap<>();
		value2.put("Currency", "Dollar");

		Queue<String> value3 = new PriorityQueue<>();
		value3.add("Asia");
		value3.add("America");

		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("Name", "Tarlesh");
		hashmap.put("Last Name", "Parmar");

		HashMap<Character, Integer> ch = new HashMap<>();
		ch.put('T', 123);

		System.out.println(value);
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(hashmap);
		System.out.println(ch);
	}
}
