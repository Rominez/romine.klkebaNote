package G

class Test1 {

    static void test(String t1){
        System.out.println(t1 + " at test1");new File().to
    }

    static void test(Object t2){
        System.out.println(t2 + " at test2");
    }

    static void main(String[] args) {
//        Object t = "123";
//        test(t);
//
//        def arr = new int[]{1,2,3};
//        for(int i=0;i<arr.length;i++){
//            println arr[i];
//        }
//
//        def testBean = new G.TestBean();
//        testBean.setAge(100)
//        testBean.setName("test")
//        testBean.age = 12
//        println testBean.getAge()

        new File('/tools/test.txt').withReader('UTF-8'){
            reader ->{
                reader.eachLine {
                    println it
                }
            }
        }
    }
}
