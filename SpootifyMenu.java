import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class SpootifyMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, SpootifyPlaylist> playlists;

    public Map<String, SpootifyPlaylist> getPlaylists(){
        return playlists;
    }
    
    public SpootifyMenu(){
        playlists = new HashMap<String, SpootifyPlaylist>();
        addPlaylist("library");
    }

    public void addPlaylist(String playlistTitle){
        if(playlistTitle != null && !playlistTitle.isBlank() && !playlistExists(playlistTitle))
            playlists.put(playlistTitle, new SpootifyPlaylist(playlistTitle));
    }

    public SpootifyPlaylist getPlaylist(String playlistTitle){
        return playlists.get(playlistTitle);
    }

    public void removePlaylist(String playlistTitle){
        if(playlistExists(playlistTitle) && !playlistTitle.equals("library"))
            playlists.remove(playlistTitle);
    }

    public boolean playlistExists(String playlistTitle){
        return playlists.containsKey(playlistTitle);
    }

    public void addContent(String playlistTitle, SpootifyContent content){

        if(playlistTitle.equals("library")){
            getPlaylist(playlistTitle).addContent(content);
            return;
        }

        if(getPlaylist("library").getContentList().contains(content))
            getPlaylist(playlistTitle).addContent(content);
    }

    public void removeContent(String playlistTitle, SpootifyContent content){
        getPlaylist(playlistTitle).removeContent(content);
    }

    public String getContentDescription(SpootifyContent content){
        String contentClass = "";
        
        if(content.getClass() == SpootifyMusic.class)
            contentClass = "Music";

        if(content.getClass() == SpootifyPodcast.class)
            contentClass = "Podcast";

        if(content.getClass() == SpootifyAudiobook.class)
            contentClass = "Audiobook";

        return String.format("%s - %s", contentClass, content.toString());
    }
}
