package mainPackage;

public class RegularBehavior implements UserBehavior{

    //default play limit value for regular user is 5
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner){
        throw new InvalidOperationException("you dont have permission to create a playlist, please upgrade your profile to premium!");
    }

    @Override
    public void playMusic (Music music){
        if(playingLimit <= 0)
            throw new InvalidOperationException("Your music play limit has been reached, please upgrade your profile to premium!");

        music.play();
        playingLimit--;
    }

    @Override
    public void buyPremium (User owner, int month){
        if(month <= 0)
            throw new InvalidOperationException("month must be greater than 0!");

        owner.setBehavior(new PremiumBehavior(month));
    }

    public int getPlayingLimit() {
        return playingLimit;
    }

}
