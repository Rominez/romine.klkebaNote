package J;

public class TestJ1 {

    public static void main(String[] args) {
        Y.createX(new Y());

        new Thread(()->{
            System.out.println("test");
        }).start();

    }
}
