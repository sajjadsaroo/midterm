package mainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;

    private static final ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Music title cannot be empty.");
        }
        if (singer == null) {
            throw new InvalidOperationException("Singer cannot be null.");
        }

        this.title = title;
        this.singer = singer;

        allMusics.add(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Music title cannot be empty.");
        }

        this.title = title;
    }

    public User getSinger() {
        return singer;
    }

    public void setSinger(User singer) {
        this.singer = singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public static List<Music> getAllMusics() {
        return Collections.unmodifiableList(allMusics);
    }

    public void play() {
        numberOfStream++;
        System.out.println("Now playing: " + title + " by " + singer.getUsername());
    }

    public static ArrayList<Music> search(String title) {
        ArrayList<Music> results = new ArrayList<>();
        for (Music m : allMusics) {
            if (m.title.equalsIgnoreCase(title)) {
                results.add(m);
            }
        }
        return results;
    }

    public static Music search(String title, String singerUsername) {
        for (Music m : allMusics) {
            if (m.title.equalsIgnoreCase(title) && m.singer.getUsername().equalsIgnoreCase(singerUsername)) {
                return m;
            }
        }
        return null;
    }



}
