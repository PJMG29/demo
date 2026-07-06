package javaConcepts;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * lambda expression:
 *  it is an anonymous function -> function without name
 *  syntax: (parameters) -> expression
 *  Comparator
 *  pre java 8
 *  Comparator<String> len = new Comparator<String>(){
 * @Override
 * public int compare(String a, String b){
 *     return a.length() - b.length();
 * }
 *
 *  }
 *
 *  after java 8
 *  Compartor<String> len = (String a, String b) ->{
 *      return a.length() - b.length()
 *  }
 *
 *
 *  2: functional interface:
 *   you have ONLY ONE abstract method
 *   you also @FunctionalInterface annotation
 *   you are allowed to have some methods with default or static keyword
 *
 *    some built-in functional interfaces:
 *    1; Function class
 *    2: Consumer
 *    3: Supplier
 *    4: Predicate
 *    5: Runnable
 *    6: callable
 *
 *    3: Method Reference
 *
 *    syntax: use "::"
 *    it is a shorter version of lambda function
 *
 *    we can use method reference to call static methods
 *    1: Integer::parseInt -> in lambda : (args) -> Integer.parseInt(args)
 *    you can use method reference to call methods of a particular object
 *    1: instance :: method
 *          System.out::println();
 *     you can do : ClassName :: instanceMethod -> String::toUpperCase
 *    you can call constructor
 *    ClassName :: new
 *    ArrayList :: New
 *
 *    #####Stream api##########
 *    it is unique way to process your data that comes from data strcuture
 *    and doesnot change original data
 *      two functions;
 *      1: intermediate functions
 *          1:filter -> keep elements matching your condition
 *          2:map -> transform each element
 *          3: sorted -> sorting elements
 *          4:distinct -> remove duplicates
 *          5: limit -> keep only the first n elements
 *          6: skip() ->skip the first n elements
 *          7:flatmap -> list of list of list string -> list of string-> flatten nested structures into one stream
 *          8: peek() -> inspect element without changing stream.
 *          ...
 *
 *
 *
 *      2: terminal function
 *
 *          forEach -> like for loop
 *          collect -> return your data into a collection
 *          count -> count elements
 *          allMatch -> true if all element match your condition(s)
 *          anyMatch - >true if any one of element match your conditions
 *          ...
 *
 *
 *
 *      in one case : you have a very large data set -> Parallel stream
 *          it will splits your task into smaller tasks -> forkJoinPool
 *
 *
 *
 *      Optional : help you reduce number of handling null pointer exception
 *
 *
 *      Java 17:
 *      1: Sealed class
 *      it restricts which other classes or interfaces may extend or
 *      implement it
 *      Shape - > parent class
 *      Circle, Square, rectangle -> child class
 *
 *      public sealed interface Shape permits Cirle, Square,Rectangle{}
 *
 *      public final class Circle implements Shape{
 *          // ur implementation
 *      }
 *
 *      2: records
 *      it auto - generates the constructor, equals(), hashCode, toString()
 *      and accessor methods.
 *      all fields in you record class are final and private
 *
 *
 *      public record User(String firstName, String lastName){}
 *
 *      =
 *
 *      public final class User{
 *          private final String firstName;
 *          private final String lastName;
 *
 *          //constructor
 *
 *          //getters and setters
 *
 *          //hashcode
 *          //toString
 *          ...
 *
 *      }
 *
 *
 *
 *
 *
 *
 */

public class Day3 {
    public static void main(String[] args) {
        Calculator add = (a,b) -> a + b;
        System.out.println(add.calculate(1,2));

       List<String> names  = List.of("Matthew", "Tom", "David", "Pengfei");
       long count = names.stream()
               .filter(n -> n.length() > 3 )
               .map(String::toUpperCase)
               .count();

        System.out.println(count);


        List<String> words = List.of("apple", "banana", "fig", "kiwi", "apple", "fig");
        List<String> result = words.stream()
                .filter(w -> w.length() > 3) // banana, apple, apple
                .distinct() // banana , apple
                .map(String::toUpperCase)// BANANA, APPLE
                .sorted()// APPLE, BANANA
                .collect(Collectors.toList());

        System.out.println(result);


        Optional<String> name = Optional.of("Matthew");
        name.ifPresent(System.out::println);
        System.out.println(name.isPresent());
    }
}

@FunctionalInterface
interface Calculator{
    int calculate(int a, int b);
}
