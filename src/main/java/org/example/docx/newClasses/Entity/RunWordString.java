package org.example.docx.newClasses.Entity;

public class RunWordString {
    private String text;
    private RunSettings settings;

    public RunWordString(String text, RunSettings settings) {
        this.text = text;
        this.settings = settings;
    }

    public RunSettings getSettings() {
        return settings;
    }

    public void setSettings(RunSettings settings) {
        this.settings = settings;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
