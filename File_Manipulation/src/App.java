import com.humanbooster.file_manipulation.FileManager;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        final String PWD = System.getProperty("user.dir");
        final String SEP = System.getProperty("file.separator");
        System.out.println("PWD: " + PWD + SEP);

        File f = new File(PWD + SEP + "test");
        try {
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
        FileManager.createFile(null);
        FileManager.createFile("");
        FileManager.createFile(f.getAbsolutePath() + "testfile");
        FileManager.createFile(f.getAbsolutePath() + "testfile");
    }

    private static void testCopyFile(File f){
        FileManager.copyFile(null, f.getAbsolutePath() + "testfile");
        FileManager.copyFile("", f.getAbsolutePath() + "testfile");
        FileManager.copyFile(f.getAbsolutePath() + "dirdoesntexists", f.getAbsolutePath() + "testfile");
        FileManager.copyFile(f.getAbsolutePath() + "testfile", f.getAbsolutePath() + "testfilecpy");
        FileManager.copyFile(f.getAbsolutePath() + "testfile", f.getAbsolutePath() + "testfile");
    }

    private static void testMoveFile(File f){
        FileManager.moveFile(null, f.getAbsolutePath() + "testfile");
        FileManager.moveFile("", f.getAbsolutePath() + "testfile");
        FileManager.moveFile(f.getAbsolutePath() + "dirdoesntexists", f.getAbsolutePath() + "testfile");
        FileManager.moveFile(f.getAbsolutePath() + "testfilecpy", f.getAbsolutePath());
        FileManager.moveFile(f.getAbsolutePath() + "testfile", f.getAbsolutePath() + "testfile");
    }

    private static void testDeleteFile(File f){
        FileManager.deleteFile(null);
        FileManager.deleteFile("");
        FileManager.deleteFile(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.deleteFile(f.getAbsolutePath() + "testfilecpy");
        FileManager.deleteFile(f.getAbsolutePath() + "testfile");
    }

    private static void testListFiles(File f){
        FileManager.listFiles(null);
        FileManager.listFiles("");
        FileManager.listFiles(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.listFiles(f.getAbsolutePath());
    }

    private static void testGetFileInfos(File f){
        FileManager.listFiles(null);
        FileManager.listFiles("");
        FileManager.listFiles(f.getAbsolutePath() + "dirdoesntexists");
        FileManager.listFiles(f.getAbsolutePath());
    }
}
