package com.example.demo;

import com.example.demo.Model.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Hello Sunil!!!");
        Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();
        stringIntegerMap.put("Sunil", 10000);
        stringIntegerMap.put("Kiran", 12000);
        stringIntegerMap.put("Suresh", 11000);
        stringIntegerMap.put("Nayudu", 10000);
        stringIntegerMap.put("Ravi", 10500);

        Map.Entry<Integer, List<String>> results=  stringIntegerMap.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey,Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .toList()
                .get(3);
       // System.out.println(results);

        List<Employee> employees = Stream.of(
                new Employee(1,"Sunil","Dev", 10000),
                new Employee(2,"Kiran","Dev", 12000),
                new Employee(3,"Suresh", "QA",11000),
                new Employee(4,"Nayudu", "QA",10000),
                new Employee(5,"Ravi", "Analyst",10500)
        ).toList();


       String input = "ssoftwareengineer";
       Map<String, Long> collect = Arrays.stream(input.split("")).
               collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));
        System.out.println(collect);

        List<String> duplicateList = Arrays.stream(input.split("")).
                collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting())).
                entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(duplicateList);

        String key = Arrays.stream(input.split("")).
                collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())).
                entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .findFirst().get().getKey();
        System.out.println(key);

        Integer[] integers = {5,9,11,3,4,21,6};
        Integer integer = Arrays.stream(integers).sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();

        System.out.println(integer);

        String[] strings = {"java","react","angular","microservices","javascript"};
        String s = Arrays.stream(strings).reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2).get();
        System.out.println(s);

        int[] numbers = {5,9,11,3,4,21,6};
        List<String> collect1 = Arrays.stream(integers).map(s1 -> s1 + "").filter(s2 -> s2.startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(collect1);


    }
}
