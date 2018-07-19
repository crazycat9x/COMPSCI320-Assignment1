import java.util.*;

public class prob1 {
    private static List<Integer> primeGenerator(int n) {
        boolean[] primeNumbers = new boolean[n + 1];
        List<Integer> result = new ArrayList<>();
        Arrays.fill(primeNumbers, true);
        for (int i = 2; i * i <= n; i++) {
            if (!primeNumbers[i]) continue;
            for (int mul = 2, b = i * mul; b <= n; mul++, b = i * mul) {
                primeNumbers[b] = false;
            }
        }
        result.add(1);
        for (int i = 2; i < primeNumbers.length; i++) {
            if (primeNumbers[i]) result.add(i);
        }
        return result;
    }

    private static List<Integer> pfe(int n, List<Integer> primeNumbers, List<Integer>[] ComputedValues) {
        List<Integer> computedValue = ComputedValues[n - 1];
        if (computedValue != null) return computedValue;
        List<Integer> result = new ArrayList<>();
        if (primeNumbers.contains(n)) {
            result.add(primeNumbers.indexOf(n) + 1);
            ComputedValues[n - 1] = result;
            return result;
        }
        for (int i : primeNumbers) {
            if (i != 1 && n % i == 0) {
                result.add(i);
                result.addAll(pfe(n / i, primeNumbers, ComputedValues));
                ComputedValues[n - 1] = result;
                return result;
            }
        }
        return result;
    }

    private static String prettyPrint(List<Integer> uglyPrint) {
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
        int n = Integer.parseInt(args[0]);
        StringBuilder output = new StringBuilder();
        List<Integer> primeNumbers = primeGenerator(n);
        List<Integer>[] ComputedValues = new ArrayList[n];
        for (int i = ComputedValues.length - 1, b = 0; i >= 0; i--, b++) {
            if (ComputedValues[i] == null) {
                pfe(n - b, primeNumbers, ComputedValues);
            }
        }
        output.append(String.format("%3s  %s", "k", "pfe(k)\n"));
        for (int i = 0; i < ComputedValues.length; i++) {
            output.append(String.format("%3d  %s\n", i + 1, prettyPrint(ComputedValues[i])));
        }
        System.out.print(output.toString());
    }
}
