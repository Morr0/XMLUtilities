package archavexm.util.xml.parser.document;

import archavexm.util.xml.parser.XMLTokens;

/**
 * Encapsulates the declaration statement in the top of the xml document.
 * */
public class XMLDeclaration {
    private String token;
    private String encoding;
    private boolean isStandalone;

    public XMLDeclaration(){
        setDefaults();
    }

    public XMLDeclaration(String token){
        this.token = token;
        switch (token){
            case XMLTokens.DECLARATION_UTF8:
                encoding = "utf-8";
                isStandalone = false;
                break;
            case XMLTokens.DECLARATION_UTF16:
                encoding = "utf-16";
                isStandalone = false;
                break;
            case XMLTokens.DECLARATION_UTF32:
                encoding = "utf-32";
                isStandalone = false;
                break;
            case XMLTokens.DECLARATION_UTF8_STANDALONE:
                encoding = "utf-8";
                isStandalone = true;
                break;
            case XMLTokens.DECLARATION_UTF16_STANDALONE:
                encoding = "utf-16";
                isStandalone = true;
                break;
            case XMLTokens.DECLARATION_UTF32_STANDALONE:
                encoding = "utf-32";
                isStandalone = true;
                break;
            default:
                setDefaults();
                break;
        }
    }

    public XMLDeclaration(String encoding, boolean isStandalone){
        this.encoding = encoding;
        this.isStandalone = isStandalone;
    }

    private void setDefaults(){
        encoding = "utf-8";
        isStandalone = false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isStandalone() {
        return isStandalone;
    }

    public void setStandalone(boolean standalone) {
        isStandalone = standalone;
    }
}