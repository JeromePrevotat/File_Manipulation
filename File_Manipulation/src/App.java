import com.humanbooster.file_manipulation.FileManager;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        final String PWD = System.getProperty("user.dir");
        final String SEP = System.getProperty("file.separator");
        System.out.println("PWD: " + PWD + SEP);

        File f = new File(PWD + SEP + "test");
        try {
            if (!f.exists())
                f.mkdir();
            testCreateFile(f);
            testCopyFile(f);
            testMoveFile(f);
            testDeleteFile(f);
            testListFiles(f);
            testGetFileInfos(f);

        } catch (SecurityException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void testCreateFile(File f){
        final String SEP = System.getProperty("file.separator");
        // FileManager.createFile(null);
        // FileManager.createFile("");
        FileManager.createFile(f.getAbsolutePath() + SEP + "testfile");
        FileManager.createFile(f.getAbsolutePath() + SEP + "testfile"); //dup
    }

    private static void testCopyFile(File f){
        final String SEP = System.getProperty("file.separator");
        // FileManager.copyFile(null, f.getAbsolutePath() + "testfile");
        // FileManager.copyFile("", f.getAbsolutePath() + "testfile");
        // FileManager.copyFile(f.getAbsolutePath() + "dirdoesntexists", f.getAbsolutePath() + "testfile");
        FileManager.copyFile(f.getAbsolutePath() + SEP + "testfile", f.getAbsolutePath() + SEP + "testfilecpy"); // new
        FileManager.copyFile(f.getAbsolutePath() + SEP + "testfile", f.getAbsolutePath() + SEP + "testfile"); // dup
    }

    private static void testMoveFile(File f){
        final String SEP = System.getProperty("file.separator");

        // FileManager.moveFile(null, f.getAbsolutePath() + "testfile");
        // FileManager.moveFile("", f.getAbsolutePath() + "testfile");
        // FileManager.moveFile(f.getAbsolutePath() + "dirdoesntexists", f.getAbsolutePath() + "testfile");
        FileManager.moveFile(f.getAbsolutePath() + SEP + "testfilecpy", f.getParent() + SEP + "testfilecpy"); // Move to Parent Dir
        FileManager.moveFile(f.getAbsolutePath() + SEP + "testfile", f.getAbsolutePath() + SEP + "testfile"); // Move to same Path, do nothing
    }

    private static void testDeleteFile(File f){
        final String SEP = System.getProperty("file.separator");

        // FileManager.deleteFile(null);
        // FileManager.deleteFile("");
        // FileManager.deleteFile(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.deleteFile(f.getParent() + SEP + "testfilecpy");
        FileManager.deleteFile(f.getAbsolutePath() + SEP + "testfile");
    }

    private static void testListFiles(File f){
        final String SEP = System.getProperty("file.separator");

        // FileManager.listFiles(null);
        // FileManager.listFiles("");
        // FileManager.listFiles(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.listFiles(f.getAbsolutePath());
    }

    private static void testGetFileInfos(File f){
        final String SEP = System.getProperty("file.separator");

        // FileManager.getFileInfo(null);
        // FileManager.getFileInfo("");
        // FileManager.getFileInfo(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.getFileInfo(f.getAbsolutePath() + SEP + "testfile(1)");
    }
}
