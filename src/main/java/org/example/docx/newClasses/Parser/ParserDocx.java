package org.example.docx.newClasses.Parser;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Reader.ReaderDocxFile;

import java.io.File;
import java.util.List;

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
            text.append("\n"); // Добавляем перенос между параграфами
        }

        return text.toString();
    }
}
