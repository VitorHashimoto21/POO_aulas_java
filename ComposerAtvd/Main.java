package ComposerAtvd;
import java.util.ArrayList;
import java.util.List;

interface Composer {
    void printName();
}

class File implements Composer {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("       - Arquivo: " + name);
    }
}

class Directory implements Composer {
    private String name;
    private List<Composer> filhos = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("- Pasta: " + name);

        for (Composer n : filhos) {
            n.printName();
        }
    }

    public void add(Composer component) {
        filhos.add(component);
    }
}

public class Main {
    public static void main(String[] args) {

        File file1 = new File("Documento.txt");
        File file2 = new File("planilha.txt");
        File file3 = new File("image.jpg");
        File file4 = new File("atal.doc");
        File file5 = new File("image.png");

        Directory directory1 = new Directory("Documentos");
        Directory directory2 = new Directory("Imagens");

        directory1.add(file1);
        directory1.add(file2);
        directory1.add(file4);

        directory2.add(file3);
        directory2.add(file5);

        Directory rootDirectory = new Directory("Raiz");

        rootDirectory.add(directory1);
        rootDirectory.add(directory2);

        rootDirectory.printName();
    }
}