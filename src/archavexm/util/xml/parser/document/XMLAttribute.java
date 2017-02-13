package archavexm.util.xml.parser.document;

/**
 * This class encapsulates an xml attribute.
 * */
public class XMLAttribute extends XMLNode {
    public XMLAttribute(String name){
        this(name, "");
    }

    public XMLAttribute(String name, String value){
        super(name, value);
    }
}