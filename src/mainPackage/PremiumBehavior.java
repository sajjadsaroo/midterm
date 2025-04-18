package mainPackage;

public class PremiumBehavior implements UserBehavior {

    private int month;

    public PremiumBehavior(int month) {
        if (month <= 0) {
            throw new InvalidOperationException("Month must be greater than 0!");
        }
        this.month = month;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        Playlist p = new Playlist(title, owner);
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int extraMonth) {
        if (extraMonth <= 0) {
            throw new InvalidOperationException("Month must be greater than 0!");
        }
        this.month += extraMonth;
    }

    public int getMonth() {
        return month;
    }
}
