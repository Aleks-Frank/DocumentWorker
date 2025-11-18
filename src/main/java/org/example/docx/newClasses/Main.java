package org.example.docx.newClasses;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Parser.ParserDocx;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Парсим из ресурсов
            List<ParagraphWordClass> paragraphs = ParserDocx.parseFromResources("test.docx");

            System.out.println("Найдено " + paragraphs.size() + " параграфов:");

            for (int i = 0; i < paragraphs.size(); i++) {
                ParagraphWordClass paragraph = paragraphs.get(i);
                System.out.println("\n=== Параграф " + (i + 1) + " ===");
                System.out.println("Выравнивание: " + paragraph.getAlignment());

                List<RunWordString> runs = paragraph.getParagraph();
                System.out.println("Прогонов: " + runs.size());

                for (int j = 0; j < runs.size(); j++) {
                    RunWordString run = runs.get(j);
                    System.out.println("  Прогон " + (j + 1) + ":");
                    System.out.println("    Текст: '" + run.getText() + "'");
                    System.out.println("    Шрифт: " + run.getNameFontText());
                    System.out.println("    Размер: " + run.getSizeFontText());
                    System.out.println("    Жирный: " + run.getBold());
                    System.out.println("    Курсив: " + run.getItalic());
                    System.out.println("    Цвет: " + run.getColorText());
                    System.out.println("    Подчеркивание: " + run.getUnderline());
                    System.out.println("    Зачеркивание: " + run.getStrikethrough());
                }
            }

            // Получаем plain text
            String plainText = ParserDocx.getPlainText(paragraphs);
            System.out.println("\n=== ВЕСЬ ТЕКСТ ===");
            System.out.println(plainText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
