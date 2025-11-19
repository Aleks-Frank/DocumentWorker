package org.example.docx.newClasses.Entity;

public class UnderlineSettings {
    private String nameUnderline;
    private String colorUnderline;

    public UnderlineSettings(String nameUnderline, String colorUnderline) {
        this.nameUnderline = nameUnderline;
        this.colorUnderline = colorUnderline;
    }

    public String getNameUnderline() {
        return nameUnderline;
    }

    public void setNameUnderline(String nameUnderline) {
        this.nameUnderline = nameUnderline;
    }

    public String getColorUnderline() {
        return colorUnderline;
    }

    public void setColorUnderline(String colorUnderline) {
        this.colorUnderline = colorUnderline;
    }
}
