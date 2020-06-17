package generator.service;

import org.apache.commons.lang3.StringUtils;
import generator.util.ColumnConfig;
import generator.util.Settings;

import java.util.List;

public class Row {

    private static final char WORD_DELIMITER = ' ';
    private static final char COLUMN_DELIMITER = '|';
    private static final char ROW_DELIMITER = '-';

    String row;
    String headRow;
    String delimiterRow;
    private Settings settings;

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getHeadRow() {
        return headRow;
    }

    public void setHeadRow(String headRow) {
        this.headRow = headRow;
    }

    public String getDelimiterRow() {
        return delimiterRow;
    }

    public void setDelimiterRow(String delimiterRow) {
        this.delimiterRow = delimiterRow;
    }

    public String generate(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(COLUMN_DELIMITER);

        for (int i = 0; i < settings.getColumns().size(); i++) {
            stringBuilder.append(generateColumn(data[i], settings.getColumns().get(i).getWidth()));
        }
        return stringBuilder.toString();
    }

    private StringBuilder generateColumn(String s, int columnWidth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WORD_DELIMITER);
        stringBuilder.append(s);
        int diffSize = columnWidth - s.length();
        if (diffSize > 0) {
            stringBuilder.append(StringUtils.repeat(" ", diffSize));
        }
        stringBuilder.append(WORD_DELIMITER).append(COLUMN_DELIMITER);
        return stringBuilder;
    }

    public void generateRowDelimiter() {
        delimiterRow = StringUtils.repeat(ROW_DELIMITER, settings.getPage().getWidth());
    }

    public void generateHead(List<ColumnConfig> columns) {

        String[] columnsTitlesStringArray = new String[columns.size()];
        int i = 0;
        for (ColumnConfig column : columns) {
            columnsTitlesStringArray[i] = column.getTitle();
            i++;
        }
        headRow = generate(columnsTitlesStringArray);

    }
}
