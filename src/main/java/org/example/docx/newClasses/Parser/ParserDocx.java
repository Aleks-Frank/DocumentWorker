package org.example.docx.newClasses.Parser;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunSettings;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Reader.ReaderDocxFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class ParserDocx {
    public static List<ParagraphWordClass> parse(String filePath) {
        ReaderDocxFile reader = new ReaderDocxFile(new File(filePath));
        return reader.parseDocument();
    }

    public static List<ParagraphWordClass> parse(File file) {
        ReaderDocxFile reader = new ReaderDocxFile(file);
        return reader.parseDocument();
    }

    public static List<ParagraphWordClass> parseFromResources(String resourcePath) throws Exception {
        ReaderDocxFile reader = new ReaderDocxFile();
        return reader.parseFromResources(resourcePath);
    }

    public static String getPlainText(List<ParagraphWordClass> paragraphs) {
        StringBuilder text = new StringBuilder();

        for (ParagraphWordClass paragraph : paragraphs) {
            for (RunWordString run : paragraph.getParagraph()) {
                text.append(run.getText());
            }
            text.append("\n");
        }

        return text.toString();
    }

    public static Boolean compareDocument(String firstDoc, String secondDoc) throws Exception {
        List<ParagraphWordClass> firstDocParagraphs = parseFromResources(firstDoc);
        List<ParagraphWordClass> secondDocParagraphs = parse(secondDoc);
        if(firstDocParagraphs == secondDocParagraphs) return true;
        if(firstDocParagraphs == null || secondDocParagraphs == null) return false;
        if(firstDocParagraphs.size() != secondDocParagraphs.size()) return false;

        for (int i = 0; i < firstDocParagraphs.size(); i++){
            if(!paragraphEqual(firstDocParagraphs.get(i), secondDocParagraphs.get(i))){
                return false;
            }
        }
        return true;
    }

    private static boolean paragraphEqual(ParagraphWordClass firstParagraph, ParagraphWordClass secondParagraph) {
        if(firstParagraph == secondParagraph) return true;
        if(firstParagraph == null || secondParagraph == null) return false;

        return Objects.equals(firstParagraph.getAlignment(), secondParagraph.getAlignment()) &&
                runListEqual(firstParagraph.getParagraph(), secondParagraph.getParagraph());
    }

    private static boolean runListEqual(List<RunWordString> firstRunList, List<RunWordString> secondRunList) {
        if(firstRunList == secondRunList) return true;
        if(firstRunList == null || secondRunList == null) return false;
        if(firstRunList.size() != secondRunList.size()) return false;

        for (int i = 0; i < firstRunList.size(); i++){
            if(!runEqual(firstRunList.get(i), secondRunList.get(i))){
                return false;
            }
        }
        return true;
    }

    private static boolean runEqual(RunWordString firstRun, RunWordString secondRun) {
        if(firstRun == secondRun) return true;
        if(firstRun == null || secondRun == null) return false;

        return Objects.equals(firstRun.getText(), secondRun.getText()) &&
                settingsEqual(firstRun.getSettings(), secondRun.getSettings());
    }

    private static boolean settingsEqual(RunSettings firstSettings, RunSettings secondSettings) {
        if(firstSettings == secondSettings) return true;
        if(firstSettings == null || secondSettings == null) return false;

        return firstSettings.getSizeFontText() == secondSettings.getSizeFontText() &&
                Objects.equals(firstSettings.getNameFontText(), secondSettings.getNameFontText()) &&
                Objects.equals(firstSettings.getBold(), secondSettings.getBold()) &&
                Objects.equals(firstSettings.getItalic(), secondSettings.getItalic()) &&
                Objects.equals(firstSettings.getColorText(), secondSettings.getColorText()) &&
                Objects.equals(firstSettings.getUnderline(), secondSettings.getUnderline()) &&
                Objects.equals(firstSettings.getStrikethrough(), secondSettings.getStrikethrough());
    }

    public void infoFromFile(String path){
        try {
            List<ParagraphWordClass> paragraphs = ParserDocx.parse(path);

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
                    System.out.println("    Шрифт: " + run.getSettings().getNameFontText());
                    System.out.println("    Размер: " + run.getSettings().getSizeFontText());
                    System.out.println("    Жирный: " + run.getSettings().getBold());
                    System.out.println("    Курсив: " + run.getSettings().getItalic());
                    System.out.println("    Цвет: " + run.getSettings().getColorText());
                    System.out.println("    Подчеркивание: " + run.getSettings().getUnderline());
                    System.out.println("    Зачеркивание: " + run.getSettings().getStrikethrough());
                }
            }

            String plainText = ParserDocx.getPlainText(paragraphs);
            System.out.println("\n=== ВЕСЬ ТЕКСТ ===");
            System.out.println(plainText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void infoFromResources(String path){
        try {
            List<ParagraphWordClass> paragraphs = ParserDocx.parseFromResources(path);

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
                    System.out.println("    Шрифт: " + run.getSettings().getNameFontText());
                    System.out.println("    Размер: " + run.getSettings().getSizeFontText());
                    System.out.println("    Жирный: " + run.getSettings().getBold());
                    System.out.println("    Курсив: " + run.getSettings().getItalic());
                    System.out.println("    Цвет: " + run.getSettings().getColorText());
                    System.out.println("    Подчеркивание: " + run.getSettings().getUnderline());
                    System.out.println("    Зачеркивание: " + run.getSettings().getStrikethrough());
                }
            }

            String plainText = ParserDocx.getPlainText(paragraphs);
            System.out.println("\n=== ВЕСЬ ТЕКСТ ===");
            System.out.println(plainText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
