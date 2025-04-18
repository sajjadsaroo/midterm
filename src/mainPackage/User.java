package mainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final String username;
    private String password;

    private final ArrayList<User> followerList = new ArrayList<>();
    private final ArrayList<User> followingList = new ArrayList<>();

    private UserBehavior behavior = new RegularBehavior();
    private final ArrayList<Playlist> playlists = new ArrayList<>();

    private static final ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        validateUsername(username);
        validatePassword(password);

        this.username = username;
        this.password = password;

        allUsers.add(this);
    }


    // getters and public api

    public void follow(User user) {}

    public void createPlaylist(String title) {
        behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) {
        behavior.playMusic(music);
    }

    public void buyPremium(int month) {
        behavior.buyPremium(this, month);
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public List<User> getFollowers() {
        return Collections.unmodifiableList(followerList);
    }

    public List<User> getFollowing() {
        return Collections.unmodifiableList(followingList);
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    public static List<User> getAllUsers() {
        return Collections.unmodifiableList(allUsers);
    }

    // behavior helper
    UserBehavior getBehavior() {
        return behavior;
    }

    void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    void changePassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    private static void validateUsername(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidOperationException("Username cannot be empty!");
        }

        for (User allUser : allUsers) {
            if (allUser.username.equals(name)) {
                throw new InvalidOperationException("Username already exists!");
            }
        }
    }

    private static void validatePassword(String p) {
        if (p == null || p.length() < 8)
            throw new InvalidOperationException("too Weak password!");
    }


}
