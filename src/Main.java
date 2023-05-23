import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Main implements ConditionsInitialization{
    public static CopyOnWriteArrayList<String> resultFileList = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws InterruptedException {

        ExecutorService treadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        treadPool.execute(new BercutFileSearch(new File(ConditionsInitialization.directoryName()),
                ConditionsInitialization.maskOrFullName ()
                , resultFileList, treadPool));
        treadPool.awaitTermination(5, TimeUnit.SECONDS);
        treadPool.shutdown();
        String textOut = resultFileList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
        Path pathOut = Paths.get("output.txt");

        try {
            Files.writeString(pathOut, textOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

