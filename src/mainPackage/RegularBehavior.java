package mainPackage;

public class RegularBehavior implements UserBehavior{

    //default play limit value for regular user is 5
    public int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner){}

    @Override
    public void playMusic (Music music){

    }

    @Override
    public void buyPremium (User owner, int month){

    }
}
