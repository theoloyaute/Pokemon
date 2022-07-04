package net.enovea.pokemon;

import liquibase.repackaged.org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
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

    @Test
    void testFlatMap() {
        List<Pair<Integer, String>> generationsPokemons = List.of(
                Pair.of(1, "bulbi"),
                Pair.of(1, "sala"),
                Pair.of(2, "germinion"));

        var result = generationsPokemons.stream()
                .collect(Collectors.toMap(
                        Pair::getKey,
                        pair -> List.of(pair.getValue()),
                        (list1, list2) -> Stream.of(list1, list2)
                                .flatMap(Collection::stream)
                                .toList()));

        System.out.println(result);
    }


    @Test
    void testReduce() {
        var elements = Stream.of(1,2,2,4);
        System.out.println(elements.reduce(Integer::sum));
    }
}
