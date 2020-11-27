package G

class Test4 {

    static void main(String[] args) {
        def test1 = '1234'
        def test2 = "1234 $test1"
        def test3 = 1
        def test4 = 1.2
        Double test5 = 0.7
        Double test6 = 0.1
        println test1 instanceof String
        println test2 instanceof GString
        println test3 instanceof Integer
        println test4 instanceof BigDecimal
        println 0.7+0.1
        println test5+test6
        assert "c${10}".getClass() in GString
        assert 'c'.getClass() == String
        char c = "test1" as char
        c = "test1".asType(char as Class<Object>)
        c = (char)"test2"
    }
}
