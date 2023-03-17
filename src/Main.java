import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(Function.identity(), value -> 1, Integer::sum));

        map.entrySet().stream()
                .sorted((el, el1) -> el.getValue()< el1.getValue() ? 1 :(el.getValue()> el1.getValue() ? -1 : el.getKey().compareTo(el1.getKey())))
                .limit(10)
                .forEach( element -> System.out.println( element.getValue() + " - " + element.getKey()));

        scanner.close();
    }
}