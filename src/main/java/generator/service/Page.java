package generator.service;

import generator.util.ColumnConfig;
import generator.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private static final String DELIMITER = "~";
    private Settings settings;
    private Row row = new Row();

    public Page(Settings settings) {
        this.settings = settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public List<String> generate(List<String[]> inputData) {
        List<String> result = new ArrayList<>();

        row.setSettings(settings);
        row.generateHead(settings.getColumns());

        row.generateRowDelimiter();
        result.add(row.getHeadRow());
        result.add(row.getDelimiterRow());
        for (String[] dataRow : inputData) {

             //Count row's height
            int i = 0;
            int height = 1;
            for (ColumnConfig column : settings.getColumns()) {
                if(dataRow[i].length() / column.getWidth() >= 1){
                    if(height < dataRow[i].length() / column.getWidth()) {
                        height = dataRow[i].length() / column.getWidth();
                    }
                    if(dataRow[i].length() % column.getWidth() > 0){
                        height++;
                    }
                }
                i++;
            }

            for (int j = 0; j < height; j++) {
                String[] inner = new String[dataRow.length];
                for (int k = 0; k < dataRow.length; k++) {
                    if(dataRow[k].length() < settings.getColumns().get(k).getWidth()) {
                        if(j == 0) {
                            inner[k] = dataRow[k];
                        } else {
                            inner[k] = "";
                        }
                    } else {
                        int rowMultiplier = settings.getColumns().get(k).getWidth()*j;
                        if (rowMultiplier >= dataRow[k].length()) {
                            inner[k] = "";
                        } else if (rowMultiplier + settings.getColumns().get(k).getWidth()-1 >= dataRow[k].length()){
                            inner[k] = dataRow[k].substring(rowMultiplier);
                        } else {
                            inner[k] = dataRow[k].substring(rowMultiplier, rowMultiplier + settings.getColumns().get(k).getWidth());
                        }
                    }
                }
                result.add(row.generate(inner));
                if (result.size() % settings.getPage().getHeight() == 0) {
                    result.add(DELIMITER);
                    result.add(row.getHeadRow());
                    result.add(row.getDelimiterRow());
                } else if(j == height-1) {
                    result.add(row.getDelimiterRow());
                }
            }
        }
        result.forEach(System.out::println);
        return result;
    }
}
