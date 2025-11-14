package org.example.docx;

// Сущность строки
public class DocumentString {
    private int sizeFont;
    private String nameFont;
    private boolean isBold;
    private String text;

    public DocumentString() {
    }

    public DocumentString(int sizeFont, String nameFont, boolean isBold, String text) {
        this.sizeFont = sizeFont;
        this.nameFont = nameFont;
        this.isBold = isBold;
        this.text = text;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    public String getNameFont() {
        return nameFont;
    }

    public void setNameFont(String nameFont) {
        this.nameFont = nameFont;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DocumentString{" +
                "sizeFont=" + sizeFont +
                ", nameFont='" + nameFont + '\'' +
                ", isBold=" + isBold +
                ", text='" + text + '\'' +
                '}';
    }
}
