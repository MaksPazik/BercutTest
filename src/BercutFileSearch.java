import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class BercutFileSearch implements Runnable{
    private File rootDirectory ;
    private String mask ;
    private CopyOnWriteArrayList<String> resultFileList;
    private ExecutorService treadPool ;

    public BercutFileSearch(File rootDirectory, String mask,
                            CopyOnWriteArrayList<String> resultFileList, ExecutorService treadPool) {
        this.rootDirectory = rootDirectory;
        this.mask = mask;
        this.resultFileList = resultFileList;
        this.treadPool = treadPool;
    }

    @Override
    public void run() {
        File[] directoryFiles = rootDirectory.listFiles();
        if (rootDirectory.isDirectory() && directoryFiles != null ) {

            for (File directoryFile : directoryFiles) {
                if (directoryFile.isDirectory() && !treadPool.isShutdown()) {
                    treadPool.execute(new BercutFileSearch(directoryFile, mask, resultFileList, treadPool));
                }
                if (directoryFile.getName().contains(mask) || directoryFile.getName().equals(mask)) {
                    resultFileList.add(directoryFile.getAbsolutePath());
                    System.out.println(directoryFile + " - " + Thread.currentThread().getName());
                }
            }
        }
    }


}