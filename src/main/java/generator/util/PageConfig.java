package generator.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "page")
public class PageConfig {

    @XmlElement(name = "width")
    private int width;
    @XmlElement(name = "height")
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
