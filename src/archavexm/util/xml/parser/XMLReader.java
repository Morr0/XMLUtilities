package archavexm.util.xml.parser;

import archavexm.util.xml.document.XMLAttribute;
import archavexm.util.xml.document.XMLDeclaration;
import archavexm.util.xml.document.XMLDocument;
import archavexm.util.xml.document.XMLElement;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for reading xml files and store it in XMLDocument if the document is correct. Make sure the root element's name is unique and no other element has
 * the same name.
 * */
public class XMLReader {
    /**
     * Reads the specified xml file from the parameter provided and returns XMLDocument instance.
     *
     * @param filePath the xml document filePath
     * */
    public static XMLDocument load(String filePath) throws IOException, IncorrectXMLException{
        // Initializes the document object and reads the entire file and stores it in the XMLDocument object
        XMLDocument document = new XMLDocument(filePath, null);
        StringBuilder sb = new StringBuilder();
        try (Scanner reader = new Scanner(new File(filePath))){
            while (reader.hasNextLine())
                sb.append(reader.nextLine());
        }
        String content = sb.toString();

        if (content.charAt(0) != '<')
            throw new IncorrectXMLException("The first token of the file should be the opening tag for the declaration or for the root element");

        // Takes care of the declaration statement. If there is no declaration it will skip this stage
        if (content.contains(XMLTokens.DECLARATION_UTF8) || content.contains(XMLTokens.DECLARATION_UTF16) || content.contains(XMLTokens.DECLARATION_UTF32) ||
            content.contains(XMLTokens.DECLARATION_UTF8_STANDALONE) || content.contains(XMLTokens.DECLARATION_UTF16_STANDALONE) || content.contains(XMLTokens.DECLARATION_UTF32_STANDALONE)){
            XMLDeclaration declaration = getDeclaration(content.substring(0, content.indexOf("?>") + 1));
            if (declaration != null)
                document.setDeclaration(declaration);
        }

        // Makes a smaller string for the elements only
        if (document.hasDeclaration())
            content = content.substring(content.indexOf(">") + 1);

        // If there is an opening tag to check if the document has any tags
        /*
        if (content.matches("<.+")){
            // To check if the number of opening tags is equal to the number of closing ones
            {
                int openNum = 0;
                int closeNum = 0;

                for (char c : content.toCharArray()) {
                    if (c == '<')
                        openNum++;
                    else if (c == '>')
                        closeNum++;
                }

                if (openNum != closeNum)
                    throw new IncorrectXMLException("Some elements are missing from the file.");
            }*/

            // Uses regular expressions to search the entire file for strings starting with < and ending with >
            // This root boolean is for one time use for putting the root element in place
            boolean root = true;
            Pattern pattern = Pattern.compile("<.+?(>)");
            Matcher matcher = pattern.matcher(content);

            // Starts the loop that will be responsible for reading the tokens into the document
            while (matcher.find()){
                // Instance of the matcher.group()
                String string = matcher.group();
                String name = getName(string, false);

                if (string.isEmpty() || name.isEmpty())
                    continue;

                if (string.contains(XMLTokens.ELEMENT_BEGINNING_START)){
                    if (root){
                        if (content.contains(XMLTokens.ELEMENT_ENDING_START + name + XMLTokens.ELEMENT_END))
                            document.setRoot(new XMLElement(name));
                        else if (string.contains(XMLTokens.ELEMENT_ONE_LINE_END))
                            document.setRoot(new XMLElement(name));
                        else
                            throw new IncorrectXMLException("The root element's closing tag is missing.");

                        // Takes care of the attribute
                        if (string.contains(XMLTokens.ATTRIBUTE_BEGINNING))
                            document.getRoot().setAttributes(getAttributes(string));

                        if (string.contains(XMLTokens.ELEMENT_END))
                            document.getRoot().setValue(getValue(content.substring(content.indexOf(string) + string.length() - 2), false));

                        root = false;
                    } else {
                        // If not root element
                        if (string.trim().endsWith(XMLTokens.ELEMENT_ONE_LINE_END))
                            document.getRoot().addElement(new XMLElement(getName(string, false), getAttributes(string)));
                        else if (string.endsWith(XMLTokens.ELEMENT_END) && content.contains(XMLTokens.ELEMENT_ENDING_START + name + XMLTokens.ELEMENT_END))
                            document.getRoot().addElement(getElement(content, string, name, false, true));
                        else
                            throw new IncorrectXMLException("The element " + name + " has no ending tag.");
                    }
                }
            }

        return document;
    }

    // TODO implement this method
    public static boolean isCorrectDocument(String filePath){
        return true;
    }

    // Gets the XMLDeclaration instance from the string passed
    private static XMLDeclaration getDeclaration(String declaration){
        String encoding = "8";
        boolean standalone = false;

        // Checks the encoding of the declaration
        if (declaration.contains("16"))
            encoding = "16";
        else if (declaration.contains("32"))
            encoding = "32";

        // Checks if is standalone
        if (declaration.contains("yes"))
            standalone = true;

        return new XMLDeclaration(encoding, standalone);
    }

    // Returns the element that was provided with the content
    // The boolean fromLoad should only be true when it is called from the load() method because if it true this method will remove some of the content of the content method in the load() method
    private static XMLElement getElement(String content, String elementString, String name, boolean oneLine, boolean fromLoad) throws IncorrectXMLException{
        // Adds onLine which is true if the element working with is only one line otherwise will be false
        String endOfElement = null;
        if (content.contains(XMLTokens.ELEMENT_ENDING_START + name + XMLTokens.ELEMENT_END))
            endOfElement = XMLTokens.ELEMENT_ENDING_START + name + XMLTokens.ELEMENT_END;
        else if (elementString.trim().endsWith(XMLTokens.ELEMENT_ONE_LINE_END))
            oneLine = true;

        if (fromLoad)
            content = content.substring(content.indexOf(elementString));
        // Builds the string that contains this element and removes the elementContents's content in the content string
        String elementContents = content.substring(0, (oneLine? content.indexOf(endOfElement) - (endOfElement.length() - 2): content.indexOf(endOfElement) + (endOfElement.length())));
        //if (fromLoad)
           //content = content.substring(content.indexOf(elementContents));

        // Returns the element with it's attributes if it only contains attributes or will scan the entire elements inside the element's string
        if (oneLine)
            return new XMLElement(name, (elementContents.contains("=")? getAttributes(elementString): new LinkedList<>()));
        else {
            // The extra variable is for when there is no child elements and the element might have a value so if it has it will be stored in extra
            LinkedList<XMLElement> elements = new LinkedList<>();
            Pattern pattern = Pattern.compile("<.+?(>)");
            Matcher matcher = pattern.matcher(content);
            if (matcher.matches()){
                while (matcher.find()){
                    String string = matcher.group();
                    elements.add(getElement(elementContents, string, getName(string, false), (string.contains(XMLTokens.ELEMENT_ONE_LINE_END)), false));
                }
            }

            return new XMLElement(name, getValue(elementContents.substring(elementContents.indexOf(XMLTokens.ELEMENT_END) + 1), false)
                    , (elementContents.contains("=")? getAttributes(elementString): new LinkedList<>()));
        }
    }

    // Makes the attribute object to be added into the element
    private static XMLAttribute getAttribute(String string){
        return new XMLAttribute(getName(string, true), getValue(string, false));
    }

    // Uses regular expressions to loop through the element looking for attribute string and once it finds string it will add an attribute to the list
    private static LinkedList<XMLAttribute> getAttributes(String string){
        LinkedList<XMLAttribute> attributes = new LinkedList<>();
        Pattern pattern = Pattern.compile(" .+?(\")");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()){
            while (matcher.find())
                attributes.add(getAttribute(matcher.group()));
        }

        return attributes;
    }

    // Gets the name of an element or an attribute
    private static String getName(String string, boolean attribute){
        if (attribute)
            return string.substring(0, string.indexOf("="));
        else
            return string.substring(string.indexOf("<") + 1, (string.contains(XMLTokens.SPACE)? string.indexOf(" "): (string.contains("/")? string.indexOf("/"): string.indexOf(">"))));
    }

    // Returns the value of an attribute
    private static String getValue(String node, boolean attribute){
        if (attribute)
            return node.substring(node.indexOf("=") + 1, node.indexOf(node.length() - 1));

        StringBuilder value = new StringBuilder();
        for (char c: node.toCharArray()){
            if (c != '<')
                value.append(c);
            else
                break;
        }
        return value.toString();
    }
}