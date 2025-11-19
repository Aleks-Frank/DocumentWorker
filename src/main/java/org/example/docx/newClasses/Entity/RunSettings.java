package org.example.docx.newClasses.Entity;

import java.util.Objects;

public class RunSettings {
    private String nameFontText;
    private int sizeFontText;
    private Boolean isBold;
    private Boolean isItalic;
    private String colorText;
    private String underline;
    private Boolean strikethrough;

    public RunSettings(String nameFontText, int sizeFontText, Boolean isBold, Boolean isItalic, String colorText, String underline, Boolean strikethrough) {
        this.nameFontText = nameFontText;
        this.sizeFontText = sizeFontText;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.colorText = colorText;
        this.underline = underline;
        this.strikethrough = strikethrough;
    }

    public String getNameFontText() {
        return nameFontText;
    }

    public void setNameFontText(String nameFontText) {
        this.nameFontText = nameFontText;
    }

    public int getSizeFontText() {
        return sizeFontText;
    }

    public void setSizeFontText(int sizeFontText) {
        this.sizeFontText = sizeFontText;
    }

    public Boolean getBold() {
        return isBold;
    }

    public void setBold(Boolean bold) {
        isBold = bold;
    }

    public Boolean getItalic() {
        return isItalic;
    }

    public void setItalic(Boolean italic) {
        isItalic = italic;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public String getUnderline() {
        return underline;
    }

    public void setUnderline(String underline) {
        this.underline = underline;
    }

    public Boolean getStrikethrough() {
        return strikethrough;
    }

    public void setStrikethrough(Boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RunSettings that = (RunSettings) o;
        return sizeFontText == that.sizeFontText
                && Objects.equals(nameFontText, that.nameFontText)
                && Objects.equals(isBold, that.isBold)
                && Objects.equals(isItalic, that.isItalic)
                && Objects.equals(colorText, that.colorText)
                && Objects.equals(underline, that.underline)
                && Objects.equals(strikethrough, that.strikethrough);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameFontText, sizeFontText, isBold, isItalic, colorText, underline, strikethrough);
    }
}
