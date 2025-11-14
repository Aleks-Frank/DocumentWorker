package org.example.docx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocxParser {

    public static List<DocumentString> parse(String filePath) throws IOException {
        DocxReader reader = new DocxReader(filePath);
        return reader.parseDocument();
    }

    public static List<DocumentString> parse(File filePath) throws IOException {
        DocxReader reader = new DocxReader(filePath);
        return reader.parseDocument();
    }

    public static String parsePlainText(String filePath) throws Exception {
        List<DocumentString> deletions = parse(filePath);
        StringBuilder text = new StringBuilder();

        for (DocumentString deletion : deletions) {
            text.append(deletion.getText());
        }

        return text.toString();
    }

    public static List<DocumentString> parseFromResources(String resourcePath) throws Exception {
        DocxReader reader = new DocxReader();
        return reader.parseDocumentFromResources(resourcePath);
    }

    public static List<DocumentString> parse(InputStream inputStream) throws Exception {
        DocxReader reader = new DocxReader();
        return reader.parseDocument(inputStream);
    }

    public static String parsePlainTextFromResources(String resourcePath) throws Exception {
        List<DocumentString> deletions = parseFromResources(resourcePath);
        StringBuilder text = new StringBuilder();

        for (DocumentString deletion : deletions) {
            text.append(deletion.getText());
        }

        return text.toString();
    }
}
