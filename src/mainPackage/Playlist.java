package mainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {

    private String title;
    private User owner;
    private final ArrayList<Music> playlist = new ArrayList<>();

    public Playlist(String title, User owner) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Playlist title cannot be empty.");
        }
        if (owner == null) {
            throw new InvalidOperationException("Playlist must have an owner.");
        }

        this.title = title;
        this.owner = owner;
        owner.addPlaylistInternal(this);

    }

    public void editTitle(String newTitle, String password) {
        verifyPassword(password);
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new InvalidOperationException("New title cannot be empty.");
        }
        this.title = newTitle;
    }

    public void addMusic(Music music, String password) {
        verifyPassword(password);
        if (music == null) {
            throw new InvalidOperationException("Music cannot be null.");
        }
        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music already exists in playlist.");
        }
        playlist.add(music);
    }

    public void removeMusic(Music music, String password) {
        verifyPassword(password);
        if (!playlist.contains(music)) {
            throw new InvalidOperationException("Music does not exist in playlist.");
        }
        playlist.remove(music);
    }

    private void verifyPassword(String password) {
        if (password == null || owner.checkPassword(password) == 0) {
            throw new InvalidOperationException("Incorrect password.");
        }
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> result = new ArrayList<>();
        for (Music m : playlist) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                result.add(m);
            }
        }
        return result;
    }

    public Music searchInPlaylist(String title, String singerUsername) {
        for (Music m : playlist) {
            if (m.getTitle().equalsIgnoreCase(title) && m.getSinger().getUsername().equalsIgnoreCase(singerUsername)) {
                return m;
            }
        }
        return null;
    }


    public void playPlaylist() {
        for (Music m : playlist) {
            m.play();
        }
    }

    public void shufflePlay() {
        ArrayList<Music> temp = new ArrayList<>(playlist);
        Collections.shuffle(temp);
        for (Music m : temp) {
            m.play();
        }
    }

    public List<Music> getPlaylist() {
        return Collections.unmodifiableList(playlist);
    }


    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }

}
