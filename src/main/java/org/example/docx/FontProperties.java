package org.example.docx;

public class FontProperties {
    private int sizeFont;
    private String nameFont;
    private boolean isBold;

    public FontProperties(int sizeFont, String nameFont, boolean isBold) {
        this.sizeFont = sizeFont;
        this.nameFont = nameFont;
        this.isBold = isBold;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public String getNameFont() {
        return nameFont;
    }

    public boolean isBold() {
        return isBold;
    }
}
