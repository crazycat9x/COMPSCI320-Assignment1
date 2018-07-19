import java.util.*;

public class Main {
    private static ArrayList<Integer> primeGenerator(int n) {
        boolean[] primeNumbers = new boolean[n + 1];
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.fill(primeNumbers, true);
        for (int i = 2; i * i <= n; i++) {
            if (!primeNumbers[i]) continue;
            for (int mul = 2, b = i * mul; b <= n; mul++, b = i * mul) {
                primeNumbers[b] = false;
            }
        }

        for (int i = 1; i < primeNumbers.length; i++) {
            if (primeNumbers[i]) result.add(i);
        }
        return result;
    }

    private static ArrayList<Integer> pfe(int n, ArrayList<Integer> primeNumbers) {
        ArrayList<Integer> result = new ArrayList<>();
        if (primeNumbers.contains(n)) {
            result.add(primeNumbers.indexOf(n) + 1);
            return result;
        }
        for (Integer i : primeNumbers) {
            if (i != 1 && n % i == 0 && n / i != 1) {
                result.add(i);
                result.addAll(pfe(n / i, primeNumbers));
                return result;
            }
        }
        return result;
    }

    private static ArrayList<Integer> recursivePfe(int n, ArrayList<Integer> primeNumbers) {
        return pfe(n, primeNumbers);
    }

    public static void main(String[] args) {
        Integer n = Integer.parseInt(args[0]);
        ArrayList<Integer> primeNumbers = primeGenerator(n);
        for (int i = 1; i <= n; i++) {
            System.out.println(String.format("%d %s", i, recursivePfe(i, primeNumbers).toString()));
        }
    }
}
