import java.util.*;

public class prob3 {
    private static String prettyPrint(Map<Integer, Integer> uglyPrint) {
        StringBuilder result = new StringBuilder();
        SortedSet<Integer> keys = new TreeSet<>(uglyPrint.keySet());
        Iterator<Integer> keysIter = keys.iterator();
        while (keysIter.hasNext()) {
            Integer key = keysIter.next();
            Integer value = uglyPrint.get(key);
            if (value == 1) {
                result.append(key);
            } else {
                result.append(key).append("^").append(value);
            }
            if (keysIter.hasNext()) result.append("*");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] input = reader.nextLine().trim().split("\\s+");
        reader.close();
        List<String> bucket = new ArrayList<>();
        Map<Integer, Integer> result = new TreeMap<>();
        boolean contain1 = false;
        for (String pfe : input) bucket.addAll(Arrays.asList(pfe.split("\\*")));
        for (String i : bucket) {
            Integer key;
            Integer oldValue;
            Integer newValue;
            if (i.equals("1")) {
                contain1 = true;
                continue;
            }
            if (i.contains("^")) {
                String[] temp = i.split("\\^");
                key = Integer.parseInt(temp[0]);
                oldValue = result.get(key);
                newValue = Integer.parseInt(temp[1]);
                if (oldValue == null) {
                    result.put(key, newValue);
                } else {
                    result.replace(key, oldValue + newValue);
                }

            } else {
                key = Integer.parseInt(i);
                oldValue = result.get(key);
                if (result.get(key) == null) {
                    result.put(key, 1);
                } else {
                    result.replace(key, oldValue + 1);
                }
            }
        }
        if (result.size() == 0 && contain1) {
            System.out.println("1");
        } else {
            System.out.println(prettyPrint(result));
        }
    }
}
