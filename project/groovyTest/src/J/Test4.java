package J;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test4 {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("C:\\Users\\Romine\\Desktop\\svn\\project\\groovyTest"),new SimpleFileVisitor<Path>(){
//            @Override
//            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println(dir.toFile().getName());
//                if(dir.toFile().getName() == "out"){
//                    return FileVisitResult.SKIP_SUBTREE;
//                }else{
//                    return super.preVisitDirectory(dir, attrs);
//                }
//            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println(dir.toFile().getName());
                if(dir.toFile().getName() == "out"){
                    return FileVisitResult.SKIP_SUBTREE;
                }else {
                    return super.postVisitDirectory(dir, exc);
                }
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.toFile().getName());
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return super.visitFileFailed(file, exc);
            }
        });
    }
}
