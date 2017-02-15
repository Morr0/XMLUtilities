package archavexm.util.xml.parser;

public class XMLTokens {
    // Declaration
    public static final String DECLARATION_UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    public static final String DECLARATION_UTF16 = "<?xml version=\"1.0\" encoding=\"UTF-16\"?>";
    public static final String DECLARATION_UTF32 = "<?xml version=\"1.0\" encoding=\"UTF-32\"?>";
    public static final String DECLARATION_UTF8_STANDALONE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    public static final String DECLARATION_UTF16_STANDALONE = "<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>";
    public static final String DECLARATION_UTF32_STANDALONE = "<?xml version=\"1.0\" encoding=\"UTF-32\" standalone=\"yes\"?>";

    // Element
    public static final String ELEMENT_BEGINNING_START = "<";
    public static final String ELEMENT_ONE_LINE_END = "/>";
    public static final String ELEMENT_ENDING_START = "</";
    public static final String ELEMENT_END = ">";

    // Attribute
    public static final String ATTRIBUTE_BEGINNING = "=\"";
    public static final String ATTRIBUTE_END = "\"";

    // MISC
    public static final String SPACE = " ";
    public static final String EQUAL_SIGN = "=";
}
