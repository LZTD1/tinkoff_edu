package edu.project3;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearcherTest {

    @Test
    void findSingleFile(){
        var path = Paths.get("src","test","resources","project3Test","badLog.log");
        var dirSearcher = new DirectorySearcher(path.toString());

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                path
            )
        );
    }
    @Test
    void findMoreFiles(){
        var dirSearcher = new DirectorySearcher("src/test/resources/project3Test/*.log");

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                Path.of("src","test","resources","project3Test","badLog.log"),
                Path.of("src","test","resources","project3Test","largeLog.log"),
                Path.of("src","test","resources","project3Test","testLog.log")
            )
        );
    }
    @Test
    void findFileByFileWalker(){
        var dirSearcher = new DirectorySearcher("src/test/resources/project3Test/**/finded.txt");

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                Path.of("src","test","resources","project3Test","recursiveWalker","finded.txt"),
                Path.of("src","test","resources","project3Test","recursiveWalker2","finded.txt")
            )
        );
    }
    @Test
    void findFileByFileWalkerInTwo(){
        var dirSearcher = new DirectorySearcher("src/test/**/finded.txt");

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                Path.of("src","test","resources","project3Test","recursiveWalker","finded.txt"),
                Path.of("src","test","resources","project3Test","recursiveWalker2","finded.txt")
            )
        );
    }
    @Test
    void findFileByFileWalkerAndUnknownFile(){
        var dirSearcher = new DirectorySearcher("src/test/**/*.txt");

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                Path.of("src","test","resources","project3Test","recursiveWalker","finded.txt"),
                Path.of("src","test","resources","project3Test","recursiveWalker2","finded.txt")
            )
        );
    }
    @Test
    void findFileNotCompletedName(){
        var dirSearcher = new DirectorySearcher("src/test/resources/project3Test/mock*");

        assertThat(dirSearcher.getFilesFromPath()).isEqualTo(
            List.of(
                Path.of("src","test","resources","project3Test","mockReport.adoc"),
                Path.of("src","test","resources","project3Test","mockReport.md")
            )
        );
    }
}
