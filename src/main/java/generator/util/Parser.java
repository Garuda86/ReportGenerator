package generator.util;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import generator.util.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.List;

public class Parser {

    public static final String delimeter = "\t";

    private List<String[]> tsvParserResult;

    private Settings parsedSettings;

    public void parseTsv(String fileName) {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            tsvParserResult = parser.parseAll(new InputStreamReader(fis, "UTF-16"));
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        }

    }

    public void parseXml(String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
            XMLStreamReader xss = XMLInputFactory.newFactory().createXMLStreamReader(new FileInputStream(fileName));
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xss.nextTag();
            parsedSettings = (Settings) unmarshaller.unmarshal(xss);
        } catch (XMLStreamException | JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Settings file not found");
        }
    }

    public List<String[]> getTsvResult() {
        return tsvParserResult;
    }

    public void setTsvResult(List<String[]> tsvParserResult) {
        this.tsvParserResult = tsvParserResult;
    }

    public Settings getParsedSettings() {
        return parsedSettings;
    }

    public void setParsedSettings(Settings parsedSettings) {
        this.parsedSettings = parsedSettings;
    }
}
