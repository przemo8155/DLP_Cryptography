import java.math.BigInteger;
import static java.util.Arrays.stream;

public class DLPMain {

	
	public static void main(String[] args) {
		BigIntegerMath bim = new BigIntegerMath();
		
		
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
		
		System.out.println("Obecnie N wynosi: " + N);

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
		System.out.println("\nMale czynniki pierwsze: 2, 3\nDuzy czynnik pierwszy: 1973\n");
		
		int p = 1973;
		
		int grupa1 = 4;
		int grupa2 = 9;
		
		int duzaGrupa = p*p;
		
		System.out.println("Mamy wiec 3 grupy na ktorych musze znalezc logarytm dyskretny:\n" + grupa1 + " " + grupa2 + " " + duzaGrupa);
		
		System.out.println("Stosuje metode malych i duzych krokow Shanksa (baby step giant step)");
		System.out.println("Zaczynamy od grupy pierwszej, czyli 4");
		BigInteger baza1 = new BigInteger("5");
		BigInteger mod1 = new BigInteger("47353");
		BigInteger reszta1 = new BigInteger("4");
		BigInteger wynik1 = bim.logBabyStepGiantStep(baza1, reszta1, mod1);
		System.out.println("x: " + wynik1.toString());
		
		System.out.println("Zaczynamy od grupy drugiej, czyli 9");
		BigInteger baza2 = new BigInteger("5");
		BigInteger mod2 = new BigInteger("47353");
		BigInteger reszta2 = new BigInteger("9");
		BigInteger wynik2 = bim.logBabyStepGiantStep(baza2, reszta2, mod2);
		System.out.println("x: " + wynik2.toString());
		
		System.out.println("Zaczynamy od grupy drugiej, czyli 3892729");
		BigInteger baza3 = new BigInteger("5");
		BigInteger mod3 = new BigInteger("47353");
		BigInteger reszta3 = new BigInteger("3892729");
		BigInteger wynik3 = bim.logBabyStepGiantStep(baza3, reszta3, mod3);
		System.out.println("x: " + wynik3.toString());
		
		System.out.println("\nWychodzi na to ze mamy:");
		System.out.println("x przystaje do 37363 (mod 4)");
		System.out.println("x przystaje do 34504 (mod 9)");
		System.out.println("x przystaje do 41996 (mod 3892729)");
		
		int[] n_tab = {4,9,3892729};
		int[] a_tab = {37363, 34504, 41996};
		
		System.out.println(chineseRemainder(n_tab, a_tab));
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
	
	
	 public static int chineseRemainder(int[] n, int[] a) {
		 
	        int prod = stream(n).reduce(1, (i, j) -> i * j);
	 
	        int p, sm = 0;
	        for (int i = 0; i < n.length; i++) {
	            p = prod / n[i];
	            sm += a[i] * mulInv(p, n[i]) * p;
	        }
	        return sm % prod;
	    }
	 
	    private static int mulInv(int a, int b) {
	        int b0 = b;
	        int x0 = 0;
	        int x1 = 1;
	 
	        if (b == 1)
	            return 1;
	 
	        while (a > 1) {
	            int q = a / b;
	            int amb = a % b;
	            a = b;
	            b = amb;
	            int xqx = x1 - q * x0;
	            x1 = x0;
	            x0 = xqx;
	        }
	 
	        if (x1 < 0)
	            x1 += b0;
	 
	        return x1;
	    }

}
