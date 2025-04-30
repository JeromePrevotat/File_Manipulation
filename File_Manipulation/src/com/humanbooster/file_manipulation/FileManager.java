package com.humanbooster.file_manipulation;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FileManager {
    // - `createFile(String path)` : Crée un nouveau fichier
    // - `deleteFile(String path)` : Supprime un fichier existant
    // - `copyFile(String source, String destination)` : Copie un fichier vers une nouvelle destination
    // - `moveFile(String source, String destination)` : Déplace un fichier vers une nouvelle destination
    // - `listFiles(String directory)` : Liste tous les fichiers d’un répertoire
    // - `getFileInfo(String path)` : Récupère les informations d’un fichier (taille, date de création, etc.)
    public static boolean createFile(String path){
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");

        File f = new File(path);
        int i = 1;
        String fileNb;

        // File Duplicate
        while (f.exists()){
            fileNb = "(" + i +")";
            f = new File(path + fileNb);
            i++;
        }
        // File Creation
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean deleteFile(String path){
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        File f = new File(path);
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        // File deletion
        try {
            f.delete();
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean copyFile(String source, String destination){
        File s = new File(source);
        // Error Handling
        if (source == null) throw new NullPointerException("Error: Argument <source> is null");
        if (source.equals("")) throw new IllegalArgumentException("Error: Argument <source> is Empty");
        if (!s.exists()) throw new IllegalArgumentException("Error: Source File doesn't exists");
        // File Copy
        return createFile(destination);
    }

    public static boolean moveFile(String source, String destination){
        // Error handling
        if (source == null) throw new NullPointerException("Error: Argument <source> is null");
        if (source.equals("")) throw new IllegalArgumentException("Error: Argument <source> is Empty");
        File s = new File(source);
        if (!s.exists()) throw new IllegalArgumentException("Error: Source File doesn't exists");
        // Copy File
        if (source.equals(destination)) return true;
        if (copyFile(source, destination))
            return deleteFile(source);
        return false;
    }

    public static void listFiles(String directory){
        // Error Handling
        if (directory == null) throw new NullPointerException("Error: Argument <directory> is null");
        File f = new File(directory);
        if (directory.equals("")) throw new IllegalArgumentException("Error: Argument <directory> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: Directory doesn't exists");
        try {            
            for (File file : f.listFiles()){
                System.out.println(file.toString());
            }
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
        }
        
    }

    public static void getFileInfo(String path){
        String rights = "";
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        File f = new File(path);
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        // Get File Infos
        StringBuilder b = new StringBuilder();
        b.append(f.getName()).append("\n");
        b.append(f.getAbsolutePath()).append("\n");
        // Get Unix Date in ms - Trying to convert to Date
        Date unixDate = new Date(f.lastModified()*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String java_date = jdf.format(unixDate);
        String date = java_date;
        b.append(date).append("\n"); // long from UNIX time
        // Used Space
        b.append(f.getUsableSpace()).append("\n");
        b.append(f.getTotalSpace()).append("\n");
        // File Rights
        rights += (f.canRead()) ? "R" : "_";
        rights += (f.canWrite()) ? "W" : "_";
        rights += (f.canExecute()) ? "X" : "_";
        b.append(rights).append("\n");
        System.out.println(b.toString());
    }


}
