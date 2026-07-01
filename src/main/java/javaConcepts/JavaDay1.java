package javaConcepts;

/**
 *What is OOP?
 * it is just a programming style that helps you to organize code using objects
 *
 * what is polymorphsim ? one name with many behaviors?
 ******* Overloading vs overriding **********
 * Overloading (compile time polumorphsim)
 *  same method name, but different parameters:
 *      1: different number of params
 *      2: differernt types of params
 *      3: different order of params
 *    class Calculator{
 *
 *        int add(int a , int b){
 *            // implementation
 *        }
 *        int add(double a, double b){
 *            //
 *        }
 *        int add(int a, int b, int c){
 *            //
 *        }
 *    }
 *
 *   Overriding:(runtime): a child class gives a new version of a parent's method
 *
 *   class Animal{
 *       void makeSound(){
 *           //
 *       }
 *   }
 *   class cat extends Animal{
 * @Override
 *  void makeSound(){
 *      // a different implementation here
 *  }
 *   }
 *
 *   what is encapsulation ?
 *   means an object protects its own data
 *   private
 *
 *   Abstraction
 *   hiding details
 *   abstraction vs interface
 *   abstract class in java: represents what something is (is-a relation) and
 *                          is used to share code and state
 *   interface: represents what something can do (can-do relation) and is sued
 *              to define contracts and capabilities.
 *           abstract               vs               interface
 *  keyword  abstract                               interface
 *  inheritance: extends                            implements
 *  Constructor     allowed                         not allowed
 *
 *  Inheritance
 *
 *
 *  primitive type      vs          wrapper type
 *
 *  what is primitive type
 *  int, long, float, double, short, byte, 1 boolean, 1 char
 *  what is wrapper type?
 *  Integer, Long, Float, Double , Short, Byte, Boolean, Character
 *
 *  when you try to compare value of wrapper type in java
 *  try to avoid to use "=="
 *  in Java, Byte, short, Integer, Long default range is [-128,127]
 *
 *
 *  String vs StringBuilder vs StringBuffer
 *  String in java immutable
 *  String s = "Pengfei"
 *  String s2 = "Yao"
 *  String s3 = s + s2 ->
 *  first step: java will change the type of s and s2 from String to String builder
 *  and do "+"
 *  then convert String builder to String
 *  and assign to s3
 *
 *  String builder is mutable
 *  String buffer? -> it is mutable, it is thread safe
 *
 *  String pool in java
 *  it is special memory area where java stores shared string literals
 *  to save memory (heap) and improve performance.
 *  "hello"
 *  "hello"
 *  "hello"
 *                      ==          vs          equal()
 *  primitive type     compare value             not used
 *  objects            compare value of address     compare value
 *
 * Does java use pass-by-value or pass-by-reference?
 * memory address: 0x123456
 * Java use pass by value to arguments to a method
 *
 * for primitive type, the actual value is passed (for example:
 * int i =1 -> pass to a method)
 * for an object: like wrapper type, string, user defined object,
 * the value of the parameter contains a reference to an object
 * this value is passed to a method.
 * for String s = "Matthew" -> memory address: 0x123456
 * value of this reference is 0x123456 -> Java will take this value
 * and pass to a method.
 *
 * public static void printMyName(String myName : 0x123456){
 *     sout(myName)
 * }
 *
 *
 * different to copy an object
 * 1: shallow copy
 *  both original and the copied object pointing to the same address
 *
 * 2: Deep copy
 *      the copied object is completedly new object
 *      which means the original object and copied object are pointing to
 *      different address
 *
 * you need to implement : Cloneable
 * and override clone();
 *
 *      static   vs   non static
 * static in java: it means that some variable / function belongs to
 * class itself. you donot need to use "new" keyword, it just allows you
 * to call function or get value of variable.
 *
 *
 * Throwable -> this is parent class for exceptions and errors
 *      under this parent class:
 *          Exception class and Error class
 *          Error: out of memory Error, stack overflow error
 *          Exception :
 *              Checked exception: happening in compile time
 *              if we donot use try-catch and throw, we will
 *              receive unhandled exception.
 *              unchecked exception:
 *                  it happens during runtime. all Unchecked exceptions
 *                  are object.
 *
 *
 *    three ways to catch an exceptions
 *    throw
 *    throw one exception manually
 *    where used?-> method body
 *
 *    throws
 *    declare possible exception, where used-> method signature
 *
 *    try - catch - finally
 *    handle expcetion(s) where used? code block
 *
 *    final
 *    is used to restrict modification of variable, methods, or classes
 *    while finally is a block in try catch.
 *    final used with class level, it cannot be inherited(extended) by any other calss
 *    if a method declared with final, it cannot be overridden by subclass
 *    if a variable with final, it is immutable.
 *
 *    Generic
 *    T: any type
 *    E: element
 *    K: key
 *    N: number
 *    V: value
 *
 *    class, method, interface
 *
 *    "this" vs "Super"
 *  for example in python
 *  class User
 *          __init__(self, firstName,lastName):
 *              self.firstName = firstName
 *              self.lastName = lastName
 *
 *  class User
 *  *          __init__(anything, firstName,lastName):
 *  *              anything.firstName = firstName
 *  *              anything.lastName = lastName
 * class User
 *  *          __init__(this, firstName,lastName):
 *  *              this.firstName = firstName
 *  *              this.lastName = lastName
 *
 *  In java, we do have this keyword to represent current object
 *  "this" : refer to current object's field
 *          call current class method: this.printMyFirstName()
 *          call another constructor in the same class this(firsName)
 *          can return "this" : chianing function
 *          pass current object as a parameter
 *
 *    "Super": in java
 *    we can sue "super" for
 *    1: points to parent object
 *    2: call parent's functions
 *    3: call parent's constructor
 *
 *
 *
 *
 *
 */

public class JavaDay1 {
    public static void main(String[] args) {
//        Integer i1 = 12;
//        Integer i2 = 12;
//
//        // this output is true
//        // i2 and i1 are shared same memory address
//        System.out.println(i1 == i2);
//        Integer i3 = 222;
//        Integer i4 = 222;
//        //false: i3 and i4 does not share same memory address
//        System.out.println(i3 == i4);
//        //this is true;
//        System.out.println(i3.equals(i4));
//        String s1 = "Java";
//        String s2 = "Java";
//        //true
//        System.out.println(s1 == s2);
//        String  s3 = new String("java");
//        String s4 = new String("java");
//        // false
//        System.out.println(s3 == s4);

    }
}

//abstract class AbcClass{
//    // your subclass wants to use "extends" keyword,
//    String myName = "";
//    public AbcClass(String myName){
//        this.myName = myName;
//    }
//
//    //we are allow to have method with implementation
//    void thisIsMethod(){
//        System.out.println("this is my implementation");
//    }
//    // we also allowed to have method without method body
//    // but you need to add keyword "abstract" to your method
//    abstract  void thisIsabs();
//}
//
//interface interfaceClass{
//    // your subclass wants to use "implement" keyword
//    //private static final  String myName = "Matt"
////    public interfaceClass(String myName){
////
////    }
//    void thisIsAnotherMethod();
//    default  void thisIsMethod(){
//        System.out.println("this is also work");
//    }
//
//}

