package net.enovea.pokemon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    void testStream() {
        // given
        List<String> list = List.of("padawan-A-martin", "padawan-B-doudou", "padawan-C-teo", "jedi-A-C-poumba", "jedi-B-C-chris", "jedi-thibault");

        // when
        list.parallelStream()
                .filter(s -> s.contains("padawan"))
                .map(s -> {
                    String[] splits = s.split("-");
                    return splits[splits.length - 1];
                })
                .forEach(System.out::println);



        // then



    }
}
