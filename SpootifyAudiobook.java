import java.util.InputMismatchException;
import java.util.List;

public class SpootifyAudiobook extends SpootifyContent{

    private static final long serialVersionUID = 1L;

    private List<String> authors;
    private String publisher;
    private String storyteller;
    private String synopsis;

    public SpootifyAudiobook(
        String title,
        double duration,
        String storyteller,
        String synopsis,
        List<String> authors,
        String publisher
    ){

        super(title, duration);

        if(
            storyteller == null || storyteller.isBlank() ||
            synopsis == null || synopsis.isBlank() ||
            authors == null || authors.isEmpty() ||
            publisher == null || publisher.isBlank()
        ){
            throw new InputMismatchException("Invalid input");
        }

        this.storyteller = storyteller;
        this.synopsis = synopsis;
        this.authors = authors;
        this.publisher = publisher;
    }
    
    public String getPublisher() {
        return this.publisher;
    }
    
    public void setPublisher(String publisher) {

        if(publisher == null || publisher.isBlank())
            throw new InputMismatchException("Invalid input");

        this.publisher = publisher;
    }
    
    public List<String> getAuthors() {
        return this.authors;
    }
    
    public void setAuthors(List<String> authors) {

        if(authors == null || authors.isEmpty())
            throw new InputMismatchException("Invalid input");

        this.authors = authors;
    }
    
    public String getStoryteller() {
        return this.storyteller;
    }

    public void setStoryteller(String storyteller) {

        if(storyteller == null || storyteller.isBlank())
            throw new InputMismatchException("Invalid input");

        this.storyteller = storyteller;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {

        if(synopsis == null || synopsis.isBlank())
            throw new InputMismatchException("Invalid input");

        this.synopsis = synopsis;
    }
    
    public String describe(){

        String authorsString = "";

        for(String author : authors){
            authorsString += author + ";";
        }

        authorsString = authorsString.replaceFirst(".$","");

        return String.format(
            "Audiobook | Title: %s | Duration: %.2f minutes | Narrator: %s | Authors: %s | Publisher: %s | Synopsis: %s |",
            title,
            duration,
            storyteller,
            authorsString,
            publisher,
            synopsis
        );
    }

    public String toString(){

        return describe();
    }
}