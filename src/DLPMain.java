
public class DLPMain {

	public static void main(String[] args) {
		/*
		 * Sprawdzenie czy q jest pierwsza
		 */
		final int q = 47353;
		System.out.println("True jesli jest pierwsza; false jesli nie\nQ: " + q + " " + czyJestPierwsza(q));

		/*
		 * Sprawdzenie czy g jest generatorem grupy multiplikatywnej
		 */

		final int g = 5; // deklaracja g = 5
		int N = 267385;
		/*
		 * Wyliczenie r
		 */
		int r = N % g;
		System.out.println("r: " + r);

		/*
		 * Zamiana N
		 */
		if (r == 0 || r == 1) {
			N = (N - r) / q;
		}

		/*
		 * Sprawdzenie czy jestem zwolniony z zadania
		 */
		if (N % q == 0) {
			System.out.println("Jestem zwolniony z zadania");
		} else {
			System.out.println("Nie jestem zwolniony z zadania");
		}
		
		
		/*
		 * Rozklad na czynniki pierwsze
		 */
		
		rozkladNaCzynnikiPierwsze(q-1);
		System.out.println("Male czynniki pierwsze: 2,3\nDuzy czynnik pierwszy: 1973");
		
		int p = 1973;

	}

	public static boolean czyJestPierwsza(long n) {
		if (n > 2 && (n & 1) == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void rozkladNaCzynnikiPierwsze(long x) {
		int i, e;
		i = 2;
		e = (int) (Math.sqrt(x));
		while (i <= e) {
			while ((x % i) == 0) {
				x /= i;
				e = (int) (Math.sqrt(x));
				System.out.print(" " + i);
			}
			i++;
		}
		if (x > 1)
			System.out.print(" " + x);
	}

}
