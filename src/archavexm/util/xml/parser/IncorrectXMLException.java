package archavexm.util.xml.parser;

public class IncorrectXMLException extends Exception {
    public IncorrectXMLException(){
        super("The format of the xml document is not correct.");
    }

    public IncorrectXMLException(String message){
        super(message);
    }
}
