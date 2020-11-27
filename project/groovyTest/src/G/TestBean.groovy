package G

class TestBean {
    String name
    int age

    @Override
    boolean equals(Object obj) {
        return super.equals(obj)
    }

    boolean equals(TestBean obj) {
        return obj.name == this.name && obj.age == this.age
    }

    def void test() {
        def process = 'ls -al'.execute()
        println process.text
    }
}
