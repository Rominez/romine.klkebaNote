package J;

public class Test3 {
    static void f1(int i){
        System.out.println("this is f1");
    }

    static void f1(Integer i){
        System.out.println("this is f2");
    }

    public static void main(String[] args) {
        byte a = 1;
        f1(a);
    }
}
