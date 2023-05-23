import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyAndGroupingFiles  {
    public static void main(String[] args) throws IOException {
        String dirToCopy = ConditionsInitialization.directoryNameForCopy();

        Files.lines(Paths.get("output.txt"))
                .map(File::new)
                .forEach(file ->
                {
                    try {
                        Files.copy(file.toPath(), new File(dirToCopy+"\\"+file.getName()).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}

