package G

class Y {

    class X{
        void run(){
            println "ok";
        }

        X test(){
            return foo();
        }
    }

    X foo(){
        return new X()
    }

    static X createX(Y y){
        return new X(y);
    }
}
