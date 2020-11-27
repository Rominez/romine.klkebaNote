package G

class Test6 {

    static void main(String[] args) {
        def a = 10
        def b = 10
        println a == b
        println a === b
        def c = new TestBean()
        c.age = 10
        c.name = 'Tom'
        def d = new TestBean()
        d.age = 10
        d.name = 'Tom'
        println c == d
        println c === d
    }
}
