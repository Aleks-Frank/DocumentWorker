package org.example.docx.newClasses;

import org.example.docx.newClasses.Compare.ParagraphComparator;
import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Parser.ParserDocx;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        var doc1 = ParserDocx.parse("C:/Users/AleksFrank/Desktop/file2.docx");
        var doc2 = ParserDocx.parseFromResources("test.docx");

        boolean docsEqual = ParagraphComparator.areEqualIgnoreSpaces(doc1, doc2);
        System.out.println("Документы идентичны (без пробелов): " + docsEqual);

    }
}
