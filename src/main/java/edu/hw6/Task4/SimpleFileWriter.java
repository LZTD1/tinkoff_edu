package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class SimpleFileWriter implements AutoCloseable {

    private final String path;
    private OutputStream outputStream;
    private CheckedOutputStream checkedStream;
    private OutputStreamWriter outputStreamWriter;
    private BufferedOutputStream bufferedStream;

    public SimpleFileWriter(String path) throws IOException {
        this.path = path;
        createStream();
    }

    private void createStream() throws IOException {
        File myFile = Paths.get(this.path).toFile();
        creatingOutputStream(myFile);
    }

    private void creatingOutputStream(File myFile) throws IOException {
        this.outputStream = new FileOutputStream(myFile);
        checkedStreamWriter();
    }

    private void checkedStreamWriter() {
        this.checkedStream = new CheckedOutputStream(this.outputStream, new Adler32());
        bufferedStreamWriter();

    }

    private void bufferedStreamWriter() {
        this.bufferedStream = new BufferedOutputStream(this.checkedStream);
        outputStreamWriter();

    }

    private void outputStreamWriter() {
        this.outputStreamWriter = new OutputStreamWriter(this.bufferedStream, StandardCharsets.UTF_8);
    }

    public void printInFile(String data) {
        try (PrintWriter output = new PrintWriter(this.outputStreamWriter)) {
            output.write(data);
        }
    }

    public long getCheckSum() {
        return this.checkedStream.getChecksum().getValue();
    }

    @Override
    public void close() throws IOException {
        this.outputStream.close();
        this.checkedStream.close();
        this.outputStreamWriter.close();
        this.bufferedStream.close();
    }
}
