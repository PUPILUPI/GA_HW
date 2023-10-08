package ru.belov.hw3.java8Practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamHandler {
    static class Developer {
        private String name;
        private List<String> languages;

        //плюс геттеры и сеттеры
        public Developer(String name, List<String> languages) {
            this.name = name;
            this.languages = languages;
        }
    }

    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3);
        List<Developer> developers = developerStream.toList();
        List<String> uniqueLanguages = developers.stream()
                .flatMap(developer -> developer.languages.stream())
                .toList();
        List<String> filteredLanguages = uniqueLanguages.stream()
                .filter(lang -> uniqueLanguages.stream().filter(c -> c.equals(lang)).count() == 1)
                .toList();
        developers.stream()
                .filter(developer -> developer.languages.stream().anyMatch(lang -> filteredLanguages.contains(lang)))
                .forEach(dev -> System.out.println(dev.name));
    }

}

