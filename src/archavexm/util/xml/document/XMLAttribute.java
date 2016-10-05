package archavexm.util.xml.document;

/**
 * This class encapsulates an xml attribute. This should be used within XMLElement.
 * */
public class XMLAttribute extends XMLNode {
    public XMLAttribute(String name){
        this(name, "");
    }

    public XMLAttribute(String name, String value){
        super(name, value);
    }
}
