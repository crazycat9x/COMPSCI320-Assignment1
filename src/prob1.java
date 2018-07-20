import java.util.*;

public class prob1 {
    private static int getDigitLength(int n) {
        if (n >= 100) {
            return 3;
        } else {
            if (n < 10) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    private static String digitTo3SpacesString(int digit) {
        int digitLength = getDigitLength(digit);
        String digitString = Integer.toString(digit);
        switch (digitLength) {
            case 1:
                return "  " + digitString;
            case 2:
                return " " + digitString;
            default:
                return digitString;
        }
    }

    private static String prettyPrint(List<Integer> uglyPrint) {
        String result;
        Collections.sort(uglyPrint);
        int count = 0;
        int prev = uglyPrint.get(0);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i : uglyPrint) {
            if (i == prev) {
                count++;
            } else if (count == 1) {
                resultStringBuilder.append(Integer.toString(prev)).append("*");
                prev = i;
                count = 1;
            } else {
                resultStringBuilder.append(Integer.toString(prev)).append("^").append(Integer.toString(count)).append("*");
                prev = i;
                count = 1;
            }
        }
        if (count == 1) {
            resultStringBuilder.append(Integer.toString(prev));
        } else {
            resultStringBuilder.append(Integer.toString(prev)).append("^").append(Integer.toString(count));
        }
        result = resultStringBuilder.toString();
        return result;
    }

    private static List<Integer> primeGenerator(int n) {
        boolean[] primeNumbers = new boolean[n + 1];
        List<Integer> result = new ArrayList<>();
        Arrays.fill(primeNumbers, true);
        for (int i = 2; i * i <= n; i++) {
            if (!primeNumbers[i]) continue;
            for (int mul = 2, b = i * mul; b <= n; mul++, b = i * mul) primeNumbers[b] = false;
        }
        result.add(1);
        for (int i = 2; i < primeNumbers.length; i++) if (primeNumbers[i]) result.add(i);
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
                int temp = primeNumbers.contains(i) ? primeNumbers.indexOf(i) + 1 : i;
                result.add(temp);
                result.addAll(pfe(n / i, primeNumbers, ComputedValues));
                ComputedValues[n - 1] = result;
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        reader.close();
        StringBuilder output = new StringBuilder();
        List<Integer> primeNumbers = primeGenerator(num);
        List<Integer>[] ComputedValues = new List[num];
        for (int i = ComputedValues.length - 1, b = 0; i >= 0; i--, b++) {
            if (ComputedValues[i] == null) pfe(num - b, primeNumbers, ComputedValues);
        }
        output.append("  k pfe(k)\n");
        for (int i = 0; i < ComputedValues.length; i++) {
            output.append(digitTo3SpacesString(i + 1))
                    .append(" ")
                    .append(prettyPrint(ComputedValues[i]))
                    .append("\n");
        }
        System.out.print(output.toString());
    }
}
