package org.example.docx.newClasses.Compare;

import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;

import java.util.List;

public class ParagraphComparator {

    /**
     * Удаляет ВСЕ пробельные символы из текста
     */
    public static String removeAllSpaces(String text) {
        if (text == null) return "";
        // Удаляем все пробелы, табы, переносы строк и другие whitespace characters
        return text.replaceAll("\\s+", "");
    }

    /**
     * Получает текст параграфа без ВСЕХ пробелов
     */
    public static String getTextWithoutSpaces(ParagraphWordClass paragraph) {
        if (paragraph == null || paragraph.getParagraph() == null) return "";

        StringBuilder text = new StringBuilder();
        for (RunWordString run : paragraph.getParagraph()) {
            if (run != null && run.getText() != null) {
                text.append(run.getText());
            }
        }
        // Удаляем ВСЕ пробелы из собранного текста
        return removeAllSpaces(text.toString());
    }

    /**
     * Сравнивает два объекта ParagraphWordClass без учета ВСЕХ пробелов
     */
    public static boolean areParagraphsEqualIgnoreSpaces(ParagraphWordClass p1, ParagraphWordClass p2) {
        if (p1 == p2) return true;
        if (p1 == null || p2 == null) return false;

        String text1 = getTextWithoutSpaces(p1);
        String text2 = getTextWithoutSpaces(p2);

        return text1.equals(text2);
    }

    /**
     * Детальное сравнение с выводом ВСЕЙ информации
     */
    public static void compareDetailed(ParagraphWordClass p1, ParagraphWordClass p2, String prefix) {
        System.out.println(prefix + "=== ДЕТАЛЬНОЕ СРАВНЕНИЕ ===");

        if (p1 == null && p2 == null) {
            System.out.println(prefix + "Оба параграфа null");
            return;
        }
        if (p1 == null) {
            System.out.println(prefix + "Первый параграф null");
            return;
        }
        if (p2 == null) {
            System.out.println(prefix + "Второй параграф null");
            return;
        }

        // Полные тексты
        String fullText1 = getFullText(p1);
        String fullText2 = getFullText(p2);

        // Тексты без пробелов
        String noSpaces1 = getTextWithoutSpaces(p1);
        String noSpaces2 = getTextWithoutSpaces(p2);

        System.out.println(prefix + "ПОЛНЫЙ ТЕКСТ 1: '" + fullText1 + "'");
        System.out.println(prefix + "ПОЛНЫЙ ТЕКСТ 2: '" + fullText2 + "'");
        System.out.println(prefix + "БЕЗ ПРОБЕЛОВ 1: '" + noSpaces1 + "'");
        System.out.println(prefix + "БЕЗ ПРОБЕЛОВ 2: '" + noSpaces2 + "'");
        System.out.println(prefix + "Длина без пробелов 1: " + noSpaces1.length());
        System.out.println(prefix + "Длина без пробелов 2: " + noSpaces2.length());

        // Сравнение без пробелов
        if (noSpaces1.equals(noSpaces2)) {
            System.out.println(prefix + "✅ Тексты БЕЗ ПРОБЕЛОВ ИДЕНТИЧНЫ");
        } else {
            System.out.println(prefix + "❌ Тексты БЕЗ ПРОБЕЛОВ РАЗЛИЧНЫ");

            // Показываем различия
            showDetailedDifferences(noSpaces1, noSpaces2, prefix);
        }
    }

    /**
     * Показывает подробные различия между текстами
     */
    private static void showDetailedDifferences(String text1, String text2, String prefix) {
        int minLength = Math.min(text1.length(), text2.length());
        int maxLength = Math.max(text1.length(), text2.length());

        // Ищем первое отличие
        int firstDifference = -1;
        for (int i = 0; i < minLength; i++) {
            if (text1.charAt(i) != text2.charAt(i)) {
                firstDifference = i;
                break;
            }
        }

        if (firstDifference != -1) {
            System.out.println(prefix + "Первое отличие на позиции " + (firstDifference + 1) + ":");

            // Показываем контекст вокруг отличия
            int start = Math.max(0, firstDifference - 5);
            int end1 = Math.min(text1.length(), firstDifference + 6);
            int end2 = Math.min(text2.length(), firstDifference + 6);

            String context1 = text1.substring(start, end1);
            String context2 = text2.substring(start, end2);

            System.out.println(prefix + "  Контекст 1: ..." + context1 + "...");
            System.out.println(prefix + "  Контекст 2: ..." + context2 + "...");
            System.out.println(prefix + "  Символ 1: '" + text1.charAt(firstDifference) + "' (код: " + (int) text1.charAt(firstDifference) + ")");
            System.out.println(prefix + "  Символ 2: '" + text2.charAt(firstDifference) + "' (код: " + (int) text2.charAt(firstDifference) + ")");
        }

        // Показываем разницу в длине
        if (text1.length() != text2.length()) {
            System.out.println(prefix + "Разная длина текста:");
            System.out.println(prefix + "  Длина 1: " + text1.length() + " символов");
            System.out.println(prefix + "  Длина 2: " + text2.length() + " символов");

            if (text1.length() > text2.length()) {
                String extra = text1.substring(minLength);
                System.out.println(prefix + "  В первом тексте лишнее: '" + extra + "'");
            } else {
                String extra = text2.substring(minLength);
                System.out.println(prefix + "  Во втором тексте лишнее: '" + extra + "'");
            }
        }
    }

    /**
     * Получает полный текст параграфа
     */
    public static String getFullText(ParagraphWordClass paragraph) {
        if (paragraph == null || paragraph.getParagraph() == null) return "";

        StringBuilder text = new StringBuilder();
        for (RunWordString run : paragraph.getParagraph()) {
            if (run != null && run.getText() != null) {
                text.append(run.getText());
            }
        }
        return text.toString();
    }

    /**
     * Сравнивает два списка ParagraphWordClass без учета пробелов
     */
    public static boolean areEqualIgnoreSpaces(List<ParagraphWordClass> list1, List<ParagraphWordClass> list2) {
        if (list1 == list2) return true;
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            if (!areParagraphsEqualIgnoreSpaces(list1.get(i), list2.get(i))) {
                System.out.println("Различие в параграфе " + (i + 1) + ":");
                compareDetailed(list1.get(i), list2.get(i), "  ");
                return false;
            }
        }
        return true;
    }
}