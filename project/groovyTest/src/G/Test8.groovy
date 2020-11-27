package G

class Test8 {
    public static void main(String[] args) {
        def process = 'ls -al'.execute()
        println process.text
    }
}
