package org.example.docx.newClasses.Entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RunWordString that = (RunWordString) o;
        return Objects.equals(text, that.text) && Objects.equals(settings, that.settings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, settings);
    }
}
