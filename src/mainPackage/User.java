package mainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {


    private String name;
    private final String username;
    private String password;

    private final ArrayList<User> followerList = new ArrayList<>();
    private final ArrayList<User> followingList = new ArrayList<>();

    private UserBehavior behavior = new RegularBehavior();
    private final ArrayList<Playlist> playlists = new ArrayList<>();

    private static final ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password, String name) {
        validateUsername(username);
        validatePassword(password);
        validateName(name);

        this.username = username;
        this.password = password;
        this.name = name;

        allUsers.add(this);
    }


    // getters and public api



    public void addPlaylistInternal(Playlist p) {
        playlists.add(p);
    }

    public void follow(User user) {
        if (user == null || user == this) {
            throw new InvalidOperationException("Invalid user to follow.");
        }
        if (!followingList.contains(user)) {
            followingList.add(user);
            user.followerList.add(this);
        }
    }

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

    public int checkPassword(String input){
        if(input.equals(this.password))
            return 1;

        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public void changePassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    private static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidOperationException("Username cannot be empty!");
        }

        for (User allUser : allUsers) {
            if (allUser.username.equals(username)) {
                throw new InvalidOperationException("Username already exists!");
            }
        }
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidOperationException("name cannot be empty!");
        }

    }

    private static void validatePassword(String p) {
        if (p == null || p.length() < 8)
            throw new InvalidOperationException("too Weak password!");
    }


}
