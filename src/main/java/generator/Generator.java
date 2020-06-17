package generator;


import generator.service.TxtReport;
import generator.util.Parser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;


public class Generator {

    public static void main(String[] args) throws JAXBException, XMLStreamException, FileNotFoundException, UnsupportedEncodingException {

        //Parse input data
        Parser parser = new Parser();
        parser.parseXml(args[0]);
        parser.parseTsv(args[1]);

        //Creating a report
        TxtReport txtReportGenerator = new TxtReport();
        txtReportGenerator.setSettings(parser.getParsedSettings());
        txtReportGenerator.setInputData(parser.getTsvResult());
        txtReportGenerator.generate();
    }
}
