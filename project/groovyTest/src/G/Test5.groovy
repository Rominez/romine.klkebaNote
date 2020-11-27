package G

class Test5 {

    static void f1(int i){
        println 'this is f1'
    }

    static void f1(Byte i){
        println 'this is f2'
    }

    static void main(String[] args) {
        byte a = 1
        f1(a)
    }
}
