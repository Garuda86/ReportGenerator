package generator.service;

import generator.util.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A generator for txt documents;
 */
public class TxtReport {

    private static final String NEW_LINE = "\r\n";

    private Settings settings;
    private List<String[]> inputData;
    private List<String> result = new ArrayList<>();


    public void generate() {
        Page pageGenerator = new Page(settings);
        result = pageGenerator.generate(inputData);

        try {

            File fileDir = new File("report.txt");

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF-16"));

            for (String line : result) {
                out.append(line).append(NEW_LINE);
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setInputData(List<String[]> inputData) {
        this.inputData = inputData;
    }

    public List<String[]> getInputData() {
        return inputData;
    }
}
