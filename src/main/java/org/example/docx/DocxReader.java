package org.example.docx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class DocxReader {

    private File docxFile;

    public DocxReader(File docxFile) {
        this.docxFile = docxFile;
    }

    public DocxReader(String docxFile) {
        this.docxFile = new File(docxFile);
    }

    public DocxReader() {
    }

    public List<DocumentString> parseDocument() throws IOException {
        List<DocumentString> deletions = new ArrayList<>();

        try(ZipFile zipFile = new ZipFile(docxFile)){
            ZipEntry documentEntry = zipFile.getEntry("word/document.xml");
            if(documentEntry == null){
                throw new IOException("Document not found");
            }

            try(InputStream inputStream = zipFile.getInputStream(documentEntry)){
                deletions = parseDocumentXml(inputStream);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        }

        return deletions;
    }

    private List<DocumentString> parseDocumentXml(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<DocumentString> documentEntry = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        NodeList paragraphs = doc.getElementsByTagName("w:p");

        for(int i = 0; i< paragraphs.getLength(); i++){
            Element paragraph = (Element) paragraphs.item(i);
            processParagraph(paragraph, documentEntry);
        }

        return documentEntry;
    }

    private void processParagraph(Element paragraph, List<DocumentString> documentEntry) {
        FontProperties fontProperties = extractFontProperties(paragraph);

        String text = extractText(paragraph);

        if(text != null && !text.trim().isEmpty()){
            DocumentString deletion = new DocumentString(
                    fontProperties.getSizeFont(),
                    fontProperties.getNameFont(),
                    fontProperties.isBold(),
                    text
            );
            documentEntry.add(deletion);
        }
    }

    private String extractText(Element paragraph) {
        StringBuilder text = new StringBuilder();

        NodeList textNodes = paragraph.getElementsByTagName("w:t");

        for (int i =0; i < textNodes.getLength(); i++){
            Element textElem = (Element) textNodes.item(i);
            String content = textElem.getTextContent();
            if(content != null){
                text.append(content);
            }
        }

        return text.toString();
    }

    private FontProperties extractFontProperties(Element paragraph) {
        int defaultSize = 0;
        String defaultFont = "";
        boolean isBold = false;

        NodeList runProperties = paragraph.getElementsByTagName("w:rPr");
        if(runProperties.getLength() > 0){
            Element rPr = (Element) runProperties.item(0);

            NodeList boldElements = rPr.getElementsByTagName("w:b");
            if(boldElements.getLength() > 0){
                isBold = true;
            }

            NodeList sizeElement = rPr.getElementsByTagName("w:sz");
            if(sizeElement.getLength() > 0){
                Element sizeElem = (Element) sizeElement.item(0);
                String sizeValue = sizeElem.getAttribute("w:val");
                if(!sizeValue.isEmpty()){
                    defaultSize = Integer.parseInt(sizeValue) / 2;
                }
            }

            NodeList fontElements = rPr.getElementsByTagName("w:rFonts");
            if(fontElements.getLength() > 0){
                Element fontElem = (Element) fontElements.item(0);
                String fontName = fontElem.getAttribute("w:ascii");
                if(!fontName.isEmpty()){
                    defaultFont = fontName;
                }
            }

        }

        return new FontProperties(defaultSize, defaultFont, isBold);
    }

    public List<DocumentString> parseDocumentFromResources(String resourcePath) throws Exception {
        InputStream inputStream = getResourceAsStream(resourcePath);
        return parseDocument(inputStream);
    }

    public List<DocumentString> parseDocument(InputStream inputStream) throws Exception {
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

    private List<DocumentString> parseZipInputStream(InputStream inputStream) throws Exception {
        List<DocumentString> deletions = new ArrayList<>();

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
