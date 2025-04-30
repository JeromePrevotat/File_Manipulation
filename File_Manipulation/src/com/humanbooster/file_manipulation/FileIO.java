package com.humanbooster.file_manipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    // - `writeTextFile(String path, String content)` : Écrit du contenu dans un fichier texte
    // - `writeBinaryFile(String path, byte[] data)` : Écrit des données binaires dans un fichier
    // 2. Implémenter des méthodes de traitement avancé :
    // - `writeFileByLines(String path, List<String> lines)` : Écrit un fichier ligne par ligne
    // - `readFileByChunks(String path, int chunkSize)` : Lit un fichier par morceaux
    // - `compressFile(String source, String destination)` : Compresse un fichier
    // - `decompressFile(String source, String destination)` : Décompresse un fichier

    public static String readTextFile(String path) throws AccessDeniedException, FileNotFoundException, IOException{
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        File f = new File(path);
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        if (!f.canRead()) throw new AccessDeniedException("Error: Permission Denied");

        try (FileInputStream fis = new FileInputStream(path)){
                FileChannel channel = fis.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
                while (channel.read(buffer) != -1) { // Read returns -1 when reaching EOF
                    buffer.flip();
                    buffer.clear();
                }
                return StandardCharsets.UTF_8.decode(buffer).toString();
        }
    }

    // Chiant
    public static boolean writeTextFile(String path, String content){
        return false;
    }

    public static boolean appendToFile(String path, String content) throws AccessDeniedException, IOException{
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        File f = new File(path);
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        if (!f.canWrite()) throw new AccessDeniedException("Error: Permission Denied");
        // Write Operation
        try (FileWriter fw = new FileWriter(f,true)){
            fw.append(content);
        } catch (IOException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    // CF ReadTextFile which do both
    public static Byte[] readBinaryFile(String path){
        Byte[] buffer;

        return null;
    }

    // Chiant
    public boolean writeBinaryFile(String path, byte[] data){


        return true;
    }

    // PART II
    public static String[] readFileByLines(String path) throws AccessDeniedException, FileNotFoundException{
        List<String> fileLines = new ArrayList<>();
        // Error Handling
        if (path == null) throw new NullPointerException("Error: Argument <path> is null");
        File f = new File(path);
        if (path.equals("")) throw new IllegalArgumentException("Error: Argument <path> is Empty");
        if (!f.exists()) throw new IllegalArgumentException("Error: File doesn't exists");
        if (!f.canRead()) throw new AccessDeniedException("Error: Permission Denied");
        try (Scanner fileReader = new Scanner(f)) {
            // GetNextLine
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                fileLines.add(line);   
            }
        }
        return fileLines.stream().toArray(String[]::new);
    }


}
