import mainPackage.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------- STARTING TEST -------------------\n");

        // بخش 1: تست ساخت یوزر با شرایط مختلف
        try {
            System.out.println("[1] Creating user 'sajad' with valid password...");
            User sajad = new User("sajad", "12345678");
            System.out.println("[1] ✅ User 'sajad' created.");

            System.out.println("[1] Creating user 'sina' with valid password...");
            User sina = new User("sina", "pass1234");
            System.out.println("[1] ✅ User 'sina' created.");

            System.out.println("[1] Creating user 'nima' with INVALID password (less than 8 chars)...");
            new User("nima", "1234567");
        } catch (InvalidOperationException e) {
            System.out.println("[1] ❌ " + e.getMessage());
        }

        // بازیابی یوزرها
        User sajad = User.getAllUsers().get(0);
        User sina = User.getAllUsers().get(1);

        // بخش 2: فالو کردن
        System.out.println("\n[2] Testing follow functionality...");
        sajad.follow(sina);
        System.out.println("[2] ✅ 'sajad' is now following 'sina'.");
        System.out.println("[2] Followers of 'sina': ");
        for (User u : sina.getFollowers())
            System.out.println(u.getUsername());
        System.out.println("[2] Following list of 'sajad': ");
        for (User u : sajad.getFollowing())
            System.out.println(u.getUsername());
        System.out.println("[2] Follower list of 'sajad': ");
        if (!sajad.getFollowers().isEmpty()) {
            for (User u : sajad.getFollowers())
                System.out.println(u.getUsername());
        } else {
            System.out.println("sajjad has no followers.");
        }

        // بخش 3: ساخت موزیک و پخش با محدودیت کاربر عادی
        System.out.println("\n[3] Creating and playing music for regular user...");
        Music m1 = new Music("Track1", sina);
        Music m2 = new Music("Track2", sina);
        for (int i = 1; i <= 6; i++) {
            try {
                System.out.print("[3] Attempt " + i + " → ");
                sajad.playMusic(m1);
            } catch (InvalidOperationException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        // بخش 4: ارتقا به پرمیوم و تست مجدد پخش موزیک بدون محدودیت
        System.out.println("\n[4] Upgrading 'sajad' to premium (2 months)...");
        sajad.buyPremium(2);
        System.out.println("[4] ✅ User 'sajad' is now premium.");
        sajad.playMusic(m2);
        System.out.println("[4] ✅ Premium user played a music without restriction.");

        // بخش 5: ساخت پلی‌لیست و تست اضافه‌کردن موزیک‌ها
        System.out.println("\n[5] Creating playlist and managing songs...");
        sajad.createPlaylist("MyPlaylist");
        Playlist p = sajad.getPlaylists().get(0);
        System.out.println("[5] ✅ Playlist created. Title: " + p.getTitle());

        System.out.println("[5] Adding 'Track1' to playlist with WRONG password:");
        try {
            p.addMusic(m1, "wrongpass");
        } catch (InvalidOperationException e) {
            System.out.println("[5] ❌ " + e.getMessage());
        }

        System.out.println("[5] Adding 'Track1' and 'Track2' with correct password:");
        p.addMusic(m1, "12345678");
        p.addMusic(m2, "12345678");
        System.out.println("[5] ✅ Songs added to playlist.");

        // لیست آهنگ‌های پلی‌لیست پس از اضافه شدن
        System.out.println("[5] Current songs in playlist: ");
        for (Music m : p.getPlaylist()) {
            System.out.println("    - " + m.getTitle() + " by " + m.getSinger().getUsername());
        }

        // بخش 6: تست اضافه کردن موزیک تکراری به پلی‌لیست
        System.out.println("\n[6] Trying to add duplicate song to playlist...");
        try {
            p.addMusic(m1, "12345678");
        } catch (InvalidOperationException e) {
            System.out.println("[6] ❌ " + e.getMessage());
        }

        // بخش 7: جستجوی آهنگ در پلی‌لیست
        System.out.println("\n[7] Searching in playlist:");
        System.out.println("[7] Search by title 'Track1': " + p.searchInPlaylist("Track1").size() + " result(s). List:");
        for (Music m : p.searchInPlaylist("Track1")) {
            System.out.println("    - " + m.getTitle() + " by " + m.getSinger().getUsername());
        }
        Music found = p.searchInPlaylist("Track1", "sina");
        System.out.println("[7] Search by title+singer: 'Track1' by 'sina': " + (found != null ? "✅ Found: " + found.getTitle() : "❌ Not Found"));

        // بخش 8: پخش و شافل پلی‌لیست
        System.out.println("\n[8] Playing playlist:");
        p.playPlaylist();

        System.out.println("\n[8] Shuffling playlist:");
        p.shufflePlay();

        // بخش 9: تست ساخت موزیک تکراری
        System.out.println("\n[9] Creating duplicate music 'Track1' by 'sina':");
        try {
            new Music("Track1", sina);
        } catch (InvalidOperationException e) {
            System.out.println("[9] ❌ " + e.getMessage());
        }

        // پایان
        System.out.println("\n------------------- END OF TEST -------------------");
    }
}
