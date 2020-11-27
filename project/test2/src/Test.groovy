class Test {

    static boolean contains(String a,String[] args){
        if (a.contains('java -jar')) return false
        for (int i = 0; i < args.size() ; i++) {
            if(!a.contains(args[i].substring(1))){
                return false
            }
        }
        return true
    }

    static void main(String[] args) {
        if(args.size() == 0){
            println '需要条件'
            return
        }
        def ps = 'ps -ef'.execute()
//        println ps.text
        ps.text.eachLine {
            if(contains(it,args)){
//                println "${it} 符合条件"
                println "${it}"
                def kill = "kill -9 ${it.split('\\s{1,}')[1]}"
                println kill
                kill.execute()
            }
        }
    }
}
