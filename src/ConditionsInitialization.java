import java.util.Scanner;

public interface ConditionsInitialization {
    static String directoryName() {
        System.out.println("Введите пожадуйста директорию для поиска: ");
        Scanner directory = new Scanner(System.in);
        return directory.nextLine();
    }
    static String maskOrFullName() {
        System.out.println("Введите пожалуйста полное название файла или его часть, например myfile.jar или myfi:");
        Scanner file = new Scanner(System.in);
        return file.nextLine();
    }
    static String directoryNameForCopy() {
        System.out.println("Введите пожадуйста директорию, куда необходимо скопировать файлы: ");
        Scanner directory = new Scanner(System.in);
        return directory.nextLine();
    }
}
