package org.example.docx.newClasses;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.example.docx.newClasses.Parser.ParserDocx;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParserDocx parserDocx = new ParserDocx();
        //parserDocx.infoFromResources("ShriftAndColor.docx");
        parserDocx.infoFromFile("C:/Users/AleksFrank/Desktop/Shrift.docx");
    }
}
