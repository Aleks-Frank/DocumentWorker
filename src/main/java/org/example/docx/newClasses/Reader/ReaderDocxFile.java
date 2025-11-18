package org.example.docx.newClasses.Reader;

import org.example.docx.DocumentString;
import org.example.docx.newClasses.Entity.ParagraphWordClass;
import org.example.docx.newClasses.Entity.RunWordString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ReaderDocxFile {

    private File docxFile;

    public ReaderDocxFile() {
    }

    public ReaderDocxFile(File docxFile) {
        this.docxFile = docxFile;
    }

    public List<ParagraphWordClass> parseDocument(){
        List<ParagraphWordClass> paragraph;

        try(ZipFile zipFile = new ZipFile(docxFile)){
            ZipEntry documentEntry = zipFile.getEntry("word/document.xml");
            if(documentEntry == null){
                throw new IOException("Document not found");
            }
            try(InputStream inputStream = zipFile.getInputStream(documentEntry)){
                paragraph = parseDocumentXml(inputStream);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        } catch (ZipException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return paragraph;
    }

    private List<ParagraphWordClass> parseDocumentXml(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<ParagraphWordClass> paragraphsList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        NodeList paragraphs = doc.getElementsByTagName("w:p");

        for(int i = 0; i < paragraphs.getLength(); i++){
            Element paragraph = (Element) paragraphs.item(i);
            ParagraphWordClass paragraphsToList = parseParagraph(paragraph);
            paragraphsList.add(paragraphsToList);
        }

        return paragraphsList;
    }

    private ParagraphWordClass parseParagraph(Element paragraph) {
        ParagraphWordClass paragraphWord = new ParagraphWordClass();
        List<RunWordString> runList = new ArrayList<>();
        NodeList runs = paragraph.getElementsByTagName("w:r");

        for(int i = 0; i < runs.getLength(); i++){
            Element runElement = (Element) runs.item(i);
            RunWordString run = parseRuns(runElement);
            if(run != null) {
                runList.add(run);
            }
        }
        paragraphWord.setParagraph(runList);
        return  paragraphWord;
    }

    private RunWordString parseRuns(Element runElement) {
        RunWordString runWordString = new RunWordString(extractText(runElement));
        return runWordString;
    }

    private String extractText(Element run) {
        StringBuilder text = new StringBuilder();

        NodeList textNodes = run.getElementsByTagName("w:t");

        for (int i =0; i < textNodes.getLength(); i++){
            Element textElem = (Element) textNodes.item(i);
            String content = textElem.getTextContent();
            if(content != null){
                text.append(content);
            }
        }

        return text.toString();
    }

    public List<ParagraphWordClass> parseFromResources(String resourcePath) throws Exception {
        InputStream inputStream = getResourceAsStream(resourcePath);
        return parseDocument(inputStream);
    }

    public List<ParagraphWordClass> parseDocument(InputStream inputStream) throws Exception {
        return parseZipInputStream(inputStream);
    }

    private InputStream getResourceAsStream(String resourcePath) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resourcePath +
                    ". Make sure the file exists in resources folder");
        }
        return inputStream;
    }

    private List<ParagraphWordClass> parseZipInputStream(InputStream inputStream) throws Exception {
        List<ParagraphWordClass> deletions = new ArrayList<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if ("word/document.xml".equals(entry.getName())) {
                    deletions = parseDocumentXml(zipInputStream);
                    break;
                }
            }
        }

        if (deletions.isEmpty()) {
            throw new IOException("Document not found in DOCX stream");
        }

        return deletions;
    }


}
