package org.example.docx.newClasses.SpacesWorker;

import org.example.docx.newClasses.Entity.RunWordString;

import java.util.ArrayList;
import java.util.List;

public class SpaceWorker {

    public static List<RunWordString> trimSpaces(List<RunWordString> runs){
        if(runs == null) return null;

        List<RunWordString> result = new ArrayList<>();

        for (RunWordString run : runs){
            if (run != null && run.getText() != null){
                String trimmedText = run.getText().trim();
                if(!trimmedText.isEmpty()){
                    result.add(new RunWordString(trimmedText, run.getSettings()));
                }
            }
        }
        return result;
    }

    public static List<RunWordString> removeSpaceRuns(List<RunWordString> runs){
        if(runs == null) return null;

        List<RunWordString> result = new ArrayList<>();

        for(RunWordString run : runs){
            if(run != null && run.getText() != null && !isOnlySpaces(run.getText())){
                result.add(run);
            }
        }
        return result;
    }

    public static List<RunWordString> normalizeInternalSpaces(List<RunWordString> runs){
        if(runs == null) return null;

        List<RunWordString> result = new ArrayList<>();

        for(RunWordString run : runs){
            if(run != null && run.getText() != null){
                String normalizedText = run.getText().replaceAll("\\s+", " ");
                result.add(new RunWordString(normalizedText, run.getSettings()));
            }
        }
        return result;
    }

    public static List<RunWordString> processSpaces(List<RunWordString> runs) {
        if (runs == null) return null;

        List<RunWordString> step1 = removeSpaceRuns(runs);

        List<RunWordString> step2 = normalizeInternalSpaces(step1);

        List<RunWordString> step3 = trimSpaces(step2);

        return step3;
    }


    private static boolean isOnlySpaces(String text) {
        return text != null && text.trim().isEmpty();
    }

    public static String getCleanText(List<RunWordString> runs) {
        if (runs == null) return "";

        StringBuilder text = new StringBuilder();
        for (RunWordString run : runs) {
            if (run != null && run.getText() != null) {
                text.append(run.getText());
            }
        }

        return text.toString().trim().replaceAll("\\s+", " ");
    }

}
