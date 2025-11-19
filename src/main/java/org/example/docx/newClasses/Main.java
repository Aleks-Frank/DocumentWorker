package org.example.docx.newClasses;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Parser.ParserDocx;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ParserDocx parserDocx = new ParserDocx();
        parserDocx.infoFromResources("test.docx");
//        parserDocx.infoFromFile("C:/Users/AleksFrank/Desktop/file.docx");
//        Boolean com = ParserDocx.compareDocument("test.docx", "C:/Users/AleksFrank/Desktop/file.docx");
//        System.out.print(com);
    }
}
