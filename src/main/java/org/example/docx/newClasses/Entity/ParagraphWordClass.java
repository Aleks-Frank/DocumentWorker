package org.example.docx.newClasses.Entity;

import java.util.List;

public class ParagraphWordClass {
    private List<RunWordString> paragraph;
    private String alignment;

    public ParagraphWordClass() {
    }

    public List<RunWordString> getParagraph() {
        return paragraph;
    }

    public void setParagraph(List<RunWordString> paragraph) {
        this.paragraph = paragraph;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
