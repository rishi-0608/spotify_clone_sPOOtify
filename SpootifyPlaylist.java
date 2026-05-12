import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class SpootifyPlaylist extends SpootifyContent {

    private List<SpootifyContent> contentList;
    
    public SpootifyPlaylist(String title){

        super(title, 0);

        contentList = new LinkedList<SpootifyContent>();
    }

    public List<SpootifyContent> getContentList() {
        return this.contentList;
    }

    public void setContentList(List<SpootifyContent> contentList) {
        this.contentList = contentList;
    }

    public String getDurationFormated(){

        return String.format("%.2f minutes", this.getDuration());
    }

    public void addContent(SpootifyContent content){

        contentList.add(content);

        this.setDuration(
            this.getDuration() + content.getDuration()
        );
    }

    public void removeContent(SpootifyContent content){

        if(contentList.contains(content)){

            contentList.remove(content);

            this.setDuration(
                this.getDuration() - content.getDuration()
            );
        }
    }

    public List<SpootifyContent> filterBy(
        boolean music,
        boolean podcast,
        boolean audiobook
    ){

        List<SpootifyContent> filteredList =
            new ArrayList<SpootifyContent>();
        
        for(SpootifyContent content : this.contentList){

            if(content.getClass() == SpootifyMusic.class && music)
                filteredList.add(content);

            if(content.getClass() == SpootifyPodcast.class && podcast)
                filteredList.add(content);

            if(content.getClass() == SpootifyAudiobook.class && audiobook)
                filteredList.add(content);
        }

        return filteredList;
    }

    public String getDescription(){

        String titulo = this.getTitle();
        
        if(titulo.equals("library"))
            titulo = "Library";
            
        return String.format(
            "Playlist Title: %s\nDuration: %s\nContents: %d ",
            titulo,
            getDurationFormated(),
            contentList.size()
        );
    }
}