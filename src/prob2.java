import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class prob2 {

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

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        reader.close();
        List<Integer> primeNumbers = primeGenerator(num);
        StringBuilder output = new StringBuilder();
        output.append("  k pfe(k)\n");
        for (int i = 0; i < primeNumbers.size(); i++) {
            int primeNumber = primeNumbers.get(i);
            int primePosition = i + 1;
            if (getDigitLength(primeNumber) <= getDigitLength(primePosition)) continue;
            output.append(digitTo3SpacesString(primeNumber))
                    .append(" ")
                    .append(Integer.toString(primePosition))
                    .append("\n");
        }
        System.out.println(output);
    }
}
