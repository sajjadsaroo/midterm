package mainPackage;

public interface UserBehavior {
    void createPlaylist (String title, User owner);
    void playMusic (Music music);
    void buyPremium (User owner, int month);
}
