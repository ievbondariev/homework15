package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Main {
    public static void main(String[] args) {
        Optional<String> optional = Stream.empty().map((s) -> (s) + " - mapped").limit(5).findAny();
        optional.ifPresent(System.out::println);

        List<Integer> list = IntStream.range(1, 10).boxed().collect(toList());
        Set<Integer> set = list.stream().filter(i -> i <= 10).limit(5).collect(toSet());
        set.forEach(System.out::println);

        Set<String> stringSet = new HashSet<>();
        stringSet.add("first");
        stringSet.add("second");
        stringSet.add("second");
        stringSet.add("third");
        List<String> stringList = stringSet.stream().collect(toList());
        stringList.forEach(System.out::println);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i + 1, i);
        }
        List<Integer> list1 = map.keySet().stream().collect(toList());
        list1.forEach(System.out::println);

        List<String> collection = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        collection.stream().skip(1).limit(8).mapToInt(Integer::parseInt)
                .filter((s) -> (s) % 2 == 0).distinct()
                .forEach(System.out::println);

        Stream<Integer> stream = Stream.of(4, 3, 4, 7, 4, 5, 4, 6);
        Optional<Integer> optionalInteger = stream.parallel()
                .sorted()
                .filter((s) -> (s) / 2 == 2).limit(3)
                .reduce((s, s1) -> s * s1)
                .stream().findFirst();
        System.out.println(optionalInteger);

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("test1", 1);
        map1.put("test2", 2);
        map1.put("test3", 3);
        List<String> stringList1 = map1.entrySet().stream()
                .map(String::valueOf)
                .distinct()
                .skip(1)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        stringList1.forEach(System.out::println);
    }
}
