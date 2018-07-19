import java.util.*;

public class prob1 {
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

    private static String prettyPrint(ArrayList<Integer> uglyPrint) {
        String result;
        int count = 0;
        int prev = uglyPrint.get(0);
        StringBuilder resultBuilder = new StringBuilder();
        for (int i : uglyPrint) {
            if (i == prev) {
                count++;
            } else {
                resultBuilder.append(Integer.toString(prev)).append(count != 1 ? "^" + Integer.toString(count) : "").append("*");
                prev = i;
                count = 1;
            }
        }
        result = resultBuilder.toString();
        result += Integer.toString(prev) + (count != 1 ? "^" + Integer.toString(count) : "");
        return result;
    }

    public static void main(String[] args) {
        Integer n = Integer.parseInt(args[0]);
        ArrayList<Integer> primeNumbers = primeGenerator(n);
        System.out.println(String.format("%3s  %s", "k", "pfe(k)"));
        for (int i = 1; i <= n; i++) {
            System.out.println(String.format("%3d  %s", i, prettyPrint(recursivePfe(i, primeNumbers))));
        }
    }
}
