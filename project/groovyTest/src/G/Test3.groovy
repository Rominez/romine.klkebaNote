package G

class Test3 {

    static void main(String[] args){
        new Thread(()->{
            println "Test"
        }).start();
    }
}
