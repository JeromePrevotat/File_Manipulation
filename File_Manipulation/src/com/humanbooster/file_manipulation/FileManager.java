package com.humanbooster.file_manipulation;
import java.io.File;
import java.io.IOException;

public class FileManager {
    // - `createFile(String path)` : Crée un nouveau fichier
    // - `deleteFile(String path)` : Supprime un fichier existant
    // - `copyFile(String source, String destination)` : Copie un fichier vers une nouvelle destination
    // - `moveFile(String source, String destination)` : Déplace un fichier vers une nouvelle destination
    // - `listFiles(String directory)` : Liste tous les fichiers d’un répertoire
    // - `getFileInfo(String path)` : Récupère les informations d’un fichier (taille, date de création, etc.)
    public static boolean createFile(String path){
        File f = new File(path);
        int i = 1;
        String fileNb;
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
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
        File f = new File(path);
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
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
        try {
            copyFile(source, destination);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return deleteFile(source);
    }

    public static void listFiles(String directory){
        File f = new File(directory);
        File[] dirContent;
        // Error Handling
        if (directory == null) throw new NullPointerException("Error: Argument <directory> is null");
        if (directory.equals("")) throw new IllegalArgumentException("Error: Argument <directory> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: Directory doesn't exists");
        try {
            dirContent = f.listFiles();
            for (File file : dirContent){
                System.out.println(file.toString());
            }
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
        }
        
    }

    public static void getFileInfo(String path){
        File f = new File(path);
        String rights = "";
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        // Get File Infos
        StringBuilder b = new StringBuilder();
        b.append(f.getName()).append("\n");
        b.append(f.getAbsolutePath()).append("\n");
        b.append(f.lastModified()).append("\n");
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
