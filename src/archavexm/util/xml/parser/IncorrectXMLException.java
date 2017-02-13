package archavexm.util.xml.parser;

/**
 * Runtime exception that should be thrown whenever incorrect xml syntax is typed or handled.
 * */
public class IncorrectXMLException extends RuntimeException {
    public IncorrectXMLException(){
        super("The format of the xml document is not correct.");
    }

    public IncorrectXMLException(String message){
        super(message);
    }
}
