package org.example;

import org.example.docx.DocumentString;
import org.example.docx.DocxParser;

import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String resourcePath = "test.docx";

            List<DocumentString> elements = DocxParser.parseFromResources(resourcePath);

            System.out.println("Найдено " + elements.size() + " элементов:");
            for (DocumentString element : elements) {
                System.out.println(element);
            }

            String plainText = DocxParser.parsePlainTextFromResources(resourcePath);
            System.out.println("\nВесь текст:");
            System.out.println(plainText);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();

            System.out.println("\n=== Отладочная информация ===");
        }
    }
}