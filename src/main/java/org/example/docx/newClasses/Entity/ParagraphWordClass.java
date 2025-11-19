package org.example.docx.newClasses.Entity;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParagraphWordClass that = (ParagraphWordClass) o;
        return Objects.equals(paragraph, that.paragraph) && Objects.equals(alignment, that.alignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paragraph, alignment);
    }
}
