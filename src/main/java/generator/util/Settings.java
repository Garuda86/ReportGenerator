package generator.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "settings")
public class Settings {

    @XmlElement(name = "page")
    private PageConfig page;

    @XmlElementWrapper(name="columns")
    @XmlElement(name = "column")
    private List<ColumnConfig> columns;

    public PageConfig getPage() {
        return page;
    }

    public List<ColumnConfig> getColumns() {
        return columns;
    }
}
