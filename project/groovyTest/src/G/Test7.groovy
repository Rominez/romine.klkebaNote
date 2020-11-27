package G

import groovy.io.FileType
import groovy.io.FileVisitResult

class Test7 {

    public static void main(String[] args) {
        new File("C:\\Users\\Romine\\Desktop\\svn\\project\\groovyTest")
        .traverse (
                preDir :{
                    if(it.name == "out"){
                        FileVisitResult.SKIP_SUBTREE
                    }else{
                        FileVisitResult.CONTINUE
                    }
                },
                file -> {
                    println file.name
                }
        )
//        .traverse({ file ->
//            FileVisitResult.SKIP_SUBTREE
//            println file.name
//            if(file.parentFile.isDirectory() and file.parentFile.name == 'src'){
//                println file.name
//                FileVisitResult.SKIP_SUBTREE
//            }else{
//                println file.name
//            }

//            if(file.isDirectory() and file.name == 'out'){
//                println file.name
//                FileVisitResult.SKIP_SUBTREE
//            }else{
//                println file.name
//            }

//            println file.name

//            if(file.isDirectory() and file.name == 'src'){
//                FileVisitResult.SKIP_SUBTREE
//            }else{
//                println file.name
//            }

//            if(file.isFile() and file.name == 'Test3.groovy'){
//                FileVisitResult.SKIP_SIBLINGS
//            }else{
//                println file.name
//            }
//        })
//        .eachFileRecurse (FileType.DIRECTORIES){ file ->
//            println file.name
//        }
//        .eachFileMatch(~/.{0,7}\.md/,{ file ->
//            println file.name
//        })
    }
}
