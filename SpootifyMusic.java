import java.util.InputMismatchException;
import java.util.List;

public class SpootifyMusic extends SpootifyContent {

    private static final long serialVersionUID = 1L;

    private List<String> songwriters;
    private List<String> interpreters;
    private String genre;

    public SpootifyMusic(
        String title,
        double duration,
        List<String> songwriters,
        List<String> interpreters,
        String genre
    ){

        super(title, duration);

        if(
            title.isBlank() ||
            songwriters.isEmpty() ||
            interpreters.isEmpty() ||
            genre.isBlank()
        ){
            throw new InputMismatchException("Invalid input");
        }
            
        this.songwriters = songwriters;
        this.interpreters = interpreters;
        this.genre = genre;
    }

    public List<String> getSongwriters() {
        return this.songwriters;
    }
    
    public void setSongwriters(List<String> songwriters) {
        this.songwriters = songwriters;
    }
    
    public List<String> getInterpreters() {
        return this.interpreters;
    }
    
    public void setInterpreters(List<String> interpreters) {
        this.interpreters = interpreters;
    }
    
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String toString(){

        String songwritersString = "";
        String interpretersString = "";

        for(String songwriter : songwriters){
            songwritersString += songwriter + ";";
        }

        for(String interpreter : interpreters){
            interpretersString += interpreter + ";";
        }

        interpretersString = interpretersString.replaceFirst(".$","");
        songwritersString = songwritersString.replaceFirst(".$","");

        return String.format(
            "Music | Title: %s | Duration: %.2f minutes | Interpreters: %s | Composers: %s |",
            title,
            duration,
            interpretersString,
            songwritersString
        );
    }
}