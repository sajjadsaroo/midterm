import mainPackage.*;

import java.util.List;

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

        User sajad = User.getAllUsers().get(0);
        User sina = User.getAllUsers().get(1);

        // بخش 2: فالو کردن و چاپ لیست‌ها
        System.out.println("\n[2] Testing follow functionality...");
        sajad.follow(sina);
        System.out.println("[2] ✅ 'sajad' is now following 'sina'.");
        System.out.println("[2] Followers of 'sina': " + listUsernames(sina.getFollowers()));
        System.out.println("[2] Following list of 'sajad': " + listUsernames(sajad.getFollowing()));
        System.out.println("[2] Followers of 'sajad': " + listUsernames(sajad.getFollowers()));

        // بخش 3: پخش موزیک برای کاربر عادی با محدودیت
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

        // بخش 4: ارتقا به پرمیوم و ادامه پخش موزیک
        System.out.println("\n[4] Upgrading 'sajad' to premium (2 months)...");
        sajad.buyPremium(2);
        sajad.playMusic(m2);
        System.out.println("[4] ✅ Premium user played music. Streams: " + m2.getNumberOfStream());

        // بخش 5: ساخت پلی‌لیست و مدیریت آن
        System.out.println("\n[5] Creating playlist and managing songs...");
        sajad.createPlaylist("MyPlaylist");
        Playlist p = sajad.getPlaylists().get(0);
        System.out.println("[5] ✅ Playlist created. Title: " + p.getTitle());

        try {
            p.addMusic(m1, "wrongpass");
        } catch (InvalidOperationException e) {
            System.out.println("[5] ❌ " + e.getMessage());
        }

        p.addMusic(m1, "12345678");
        p.addMusic(m2, "12345678");
        System.out.println("[5] ✅ Songs added to playlist: " + p.getPlaylist().size());

        // نمایش لیست موزیک‌ها
        for (Music m : p.getPlaylist()) {
            System.out.println("    - " + m.getTitle() + " by " + m.getSinger().getUsername());
        }

        // بخش 6: تست اضافه تکراری
        try {
            p.addMusic(m1, "12345678");
        } catch (InvalidOperationException e) {
            System.out.println("[6] ❌ " + e.getMessage());
        }

        // بخش 7: جستجو در پلی‌لیست
        List<Music> found = p.searchInPlaylist("Track1");
        System.out.println("[7] Search 'Track1': " + found.size());
        Music specific = p.searchInPlaylist("Track1", "sina");
        System.out.println("[7] Search 'Track1' by 'sina': " + (specific != null));

        // بخش 8: تغییر عنوان پلی‌لیست و حذف موزیک
        p.editTitle("UpdatedTitle", "12345678");
        System.out.println("[8] ✅ Title updated to: " + p.getTitle());

        p.removeMusic(m1, "12345678");
        System.out.println("[8] Removed 'Track1'. Remaining songs: " + p.getPlaylist().size());

        // بخش 9: پخش و شافل
        System.out.println("\n[9] Playing playlist:");
        p.playPlaylist();
        System.out.println("[9] Shuffling playlist:");
        p.shufflePlay();

        // بخش 10: ساخت آهنگ تکراری
        try {
            new Music("Track1", sina);
        } catch (InvalidOperationException e) {
            System.out.println("[10] ❌ " + e.getMessage());
        }

        // بخش 11: تست تغییر رمز عبور و اعتبارسنجی جدید
        sajad.changePassword("newpass123");
        System.out.println("[11] Password changed. Check new: " + (sajad.checkPassword("newpass123") == 1));

        System.out.println("\n------------------- END OF TEST -------------------");
    }

    private static String listUsernames(List<User> users) {
        if (users.isEmpty()) return "[empty]";
        StringBuilder sb = new StringBuilder();
        for (User u : users) {
            sb.append(u.getUsername()).append(", ");
        }
        return sb.toString();
    }
}
