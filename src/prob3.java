import java.util.*;

public class prob3 {
    private static String prettyPrint(List<Integer> uglyPrint) {
        if (uglyPrint.size() == 0) return "";
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

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] input = reader.nextLine().trim().split("\\s+");
        reader.close();
        List<String> bucket = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        boolean contain1 = false;
        for (String pfe : input) bucket.addAll(Arrays.asList(pfe.split("\\*")));
        for (String i : bucket) {
            if (i.equals("1")) {
                contain1 = true;
                continue;
            }
            if (i.contains("^")) {
                String[] temp = i.split("\\^");
                for (int a = 0; a < Integer.parseInt(temp[1]); a++) {
                    result.add(Integer.parseInt(temp[0]));
                }
            } else {
                result.add(Integer.parseInt(i));
            }
        }
        if (result.size() == 0 && contain1) {
            System.out.println("1");
        } else {
            Collections.sort(result);
            System.out.println(prettyPrint(result));
        }
    }
}
