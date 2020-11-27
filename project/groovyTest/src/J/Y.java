package J;

public class Y {

    public class X{

    }

    public X foo(){
        return new X();
    }

    public static X createX(Y y){
        return y.new X();
    }
}
