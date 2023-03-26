import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;

/**
 * Напишите приложение, которое на входе получает через консоль текст, а в ответ выдает статистику:
 * 1. Количество слов в тексте.
 * 2. Топ-10 самых часто упоминаемых слов, упорядоченных по количеству упоминаний в
 * обратном порядке. В случае одинакового количества упоминаний слова должны быть отсортированы по алфавиту.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Введите текст");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        List<String> list = Stream.of(string)
                .map(s -> s.replaceAll("\\p{Punct}", ""))
                .map(s -> s.replaceAll("  ", " "))
                .flatMap((s) -> Arrays.asList(s.split(" ")).stream())
                .map(s -> s.toLowerCase())
                .collect(Collectors.toList());

        System.out.println("Слов в тексте " + list.size());
        System.out.println("Топ - 10");

        Map<String, Long> map = list.stream()
                .collect(Collectors.groupingBy((String::toString), Collectors.counting()));

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach( element -> System.out.println( element.getValue() + " - " + element.getKey()));

        scanner.close();
    }
}