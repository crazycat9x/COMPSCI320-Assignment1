import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class prob1 {
    private static List<Integer>[] computedValues;
    private static List<Integer> primeNumbers;
    private static int computedValuesCompletionCount = 1;

    private static int getDigitLength(int n) {
        return n >= 100 ? 3 : n < 10 ? 1 : 2;
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

    private static void addToComputedValue(List<Integer> value, int index) {
        computedValues[index - 1] = value;
        computedValuesCompletionCount++;
    }

    private static String prettyPrint(List<Integer> uglyPrint) {
        String result;
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

    private static List<Integer> pfe(int n) {
        List<Integer> computedValue = computedValues[n - 1];
        if (computedValue != null) return computedValue;
        List<Integer> result = new ArrayList<>();
        if (primeNumbers.contains(n)) {
            result.add(primeNumbers.indexOf(n) + 1);
            addToComputedValue(result, n);
            return result;
        }
        for (int i : primeNumbers) {
            if (i * i > n) break;
            if (i != 1 && n % i == 0) {
                result.add(primeNumbers.indexOf(i) + 1);
                result.addAll(pfe(n / i));
                addToComputedValue(result, n);
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        reader.close();
        @SuppressWarnings("unchecked")
        List<Integer>[] tempComputedValues = (ArrayList<Integer>[]) new ArrayList[num];
        computedValues = tempComputedValues;
        computedValues[0] = new ArrayList<Integer>() {{
            add(1);
        }};
        primeNumbers = primeGenerator(num);
        StringBuilder output = new StringBuilder();
        for (int i = computedValues.length - 1, b = 0; i >= 0; i--, b++) {
            if (computedValues.length == computedValuesCompletionCount) break;
            pfe(num - b);
        }
        output.append("  k pfe(k)\n");
        for (int i = 0; i < computedValues.length; i++) {
            output.append(digitTo3SpacesString(i + 1))
                    .append(" ")
                    .append(prettyPrint(computedValues[i]))
                    .append("\n");
        }
        System.out.print(output.toString());
    }
}
