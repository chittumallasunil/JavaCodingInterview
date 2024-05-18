package com.example.demo;

import com.example.demo.Model.Key;
import com.example.demo.Model.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static java.util.Comparator.comparing;

public class InteviewCoding {
    public static void main(String[] args) {
        //print roman values less than 100
        for(int i=1;i<20;i++){
            System.out.println(i+"="+RomanIntegersData.toRoman(i));
        }
        HashMapWithSameObjectExample();
        System.out.println("----");
        PrintFirstRepeatedChars();
        System.out.println("----");
        PrintFirstRepeatedChars2();
        System.out.println("----");
        HashcodeAndEqualsContract();
        System.out.println("----");
        printFibonacciSeries(9);
        System.out.println("----");
        System.out.println(calculateDigitFrequency(1123433577778L,3));
        System.out.println("----");
        ToggleCaseOfEveryCharOfString("sUnIl");
        vowelCountOfString("testingvowelscountinastring");
        consonentCountofString("testingvowelscountinastring");
        englishLettersReverse("ab-cd");
        reverseString("ab-cd");
    }

    private static void englishLettersReverse(String s) {
        char[] sArray = s.toCharArray();
        int start = 0, end = sArray.length - 1;

        while (start < end) {
            // If both characters at start and end positions are letters, swap them
            if (Character.isLetter(sArray[start]) && Character.isLetter(sArray[end])) {
                char temp = sArray[start];
                sArray[start] = sArray[end];
                sArray[end] = temp;
                start++;
                end--;
            }
            // If the character at the start position is not a letter, move to the right
            else if (!Character.isLetter(sArray[start])) {
                start++;
            }
            // If the character at the end position is not a letter, move to the left
            else if (!Character.isLetter(sArray[end])) {
                end--;
            }
        }

        return new String(sArray);
    }
    //above example using streams
    public static String reverseString(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(StringBuilder::new, (sb, c) -> {
                    if (Character.isLetter(c)) {
                        sb.insert(0, c);
                    } else {
                        sb.append(c);
                    }
                }, StringBuilder::append)
                .toString();
    }

    private static void HashcodeAndEqualsContract() {
        Key key1 = new Key(1,"Value1");
        Key key2 = new Key(2, "Value2");

        Map<Integer,Key> map = new HashMap<>();
        map.put(1,key1);
        map.put(1,key2);
        System.out.println(map.entrySet().stream().count());
        System.out.println(map);

    }

    public static int FibonacciSeries(int n){
        if(n==0) return 0;
        if(n==1) return  1;
        return FibonacciSeries(n-1)+ FibonacciSeries(n-2);
    }

    public static void printFibonacciSeries(int n){
        int firstTerm = 0, secondTerm = 1;

        for (int i = 1; i <= n; ++i) {
            System.out.print(firstTerm + " ");

            // Compute the next term in the series
            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }

    }

    public static int calculateDigitFrequency(Long number, int digitToCalculateFrequency){
        if(number==0 && digitToCalculateFrequency ==0) return 0;
        int counter=0;
        while (number!=0){
            Long digit = number%10;
            if(digit == digitToCalculateFrequency) counter++;
            number = number/10;
        }
        return counter;
    }

    public static void ToggleCaseOfEveryCharOfString(String str){
        StringBuilder res = new StringBuilder("");
        for(int i=0;i<str.length();i++) {
            char ch = str.charAt(i); //current character
            if(ch >='A' && ch <= 'Z') {
                res.append((char)(ch + 32));
            } else if(ch >='a' && ch<='z'){
                res.append((char)(ch - 32));
            } else {
                res.append(ch);
            }
        }
        System.out.println(res.toString());
    }

    public static void vowelCountOfString(String str){
        System.out.println("Char count in String: "+str.length());
        String vowels="aeiou";
        long count = stream(str.split("")).filter(vowels::contains).count();
        System.out.println("Vowel Count:"+count);
    }

    public static void consonentCountofString(String str){
        String vowels="aeiou";
        long count = stream(str.split("")).filter(x->!vowels.contains(x)).count();
        System.out.println("Consonent Count:"+count);
    }

    public static void HashMapWithSameObjectExample()  {
        //Student object overrides hashcode which returns 1. If we add 5 objects to hasmap how many object will be created
        Map<String, Student> stMap = new HashMap<>();
        stMap.put("s1", new Student(1,"Sunil"));
        stMap.put("s2", new Student(2,"Suresh"));
        stMap.put("s3", new Student(3,"Nayudu"));
        stMap.put("s4", new Student(4,"Kiran"));
        stMap.put("s5", new Student(5,"Shekar"));
        List<Map.Entry<String, Student>> collect = stMap.entrySet().stream().toList();
        System.out.println(collect.size());//5 it will create 5 objects
        System.out.println(stMap.get("s4").toString());

    }

    public static void PrintFirstRepeatedChars(){
        String s = "Java Articles are Awesome";
        List<Map.Entry<String, Long>> map = stream(s.replace(" ","").split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1).toList();

        System.out.println(map);
        String key = map.stream().findFirst().get().getKey();
        System.out.println(key);

        //Highest repeated character
        List<Map.Entry<String, Long>> map2 = stream(s.replace(" ","").split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).toList()
                .stream()
                .filter(x -> x.getValue() > 1).toList();
        System.out.println(map2);
        String key2 = map2.stream().findFirst().get().getKey();
        System.out.println(key2);
    }

    public static void PrintFirstRepeatedChars2(){
        String input = "Java Articles are Awesome";

        char firstRepeatedChar = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) != input.lastIndexOf(c))
                .findFirst()
                .orElse('\u0000');
        System.out.println(firstRepeatedChar);
    }

}

class RomanIntegersData{
private final static TreeMap<Integer,String> map = new TreeMap<Integer,String>();
static {
map.put(1000, "M");
map.put(900,"CM");
map.put(500, "D");
map.put(400, "CD");
map.put(100,"C");
map.put(90, "XC");
map.put(50,"L");
map.put(40,"XL");
map.put(10,"X");
map.put(9,"IX");
map.put(5,"V");
map.put(4,"IV");
map.put(1,"I");
}
public static String toRoman(int number){
int l=map.floorKey(number);
if(number==l){
return map.get(number);
}
return map.get(l)+toRoman(number-l);
}
}
