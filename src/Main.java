import mainPackage.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("------------------- STARTING TEST -------------------\n");

        // 1 ─ USER CREATION & BASIC VALIDATION
        try {
            System.out.println("[1] Creating user 'sajad' (valid) …");
            User sajad = new User("sajad", "12345678", "Sajad Karimi");
            System.out.println("[1] ✅ created.");

            System.out.println("[1] Creating user 'sina' (valid) …");
            User sina  = new User("sina",  "pass1234",  "Sina Ahmadi");
            System.out.println("[1] ✅ created.");

            System.out.println("[1] Creating user 'nima' (weak password) …");
            new User("nima",  "1234567",  "Nima Duplicated");
        } catch (InvalidOperationException e) {
            System.out.println("[1] ❌ " + e.getMessage());
        }


        /* we know index‑order because two valid users above succeeded */
        User sajad = User.getAllUsers().get(0);
        User sina  = User.getAllUsers().get(1);

        /* 2 ─ FOLLOW & LISTS */
        System.out.println("\n[2] FOLLOW tests …");
        sajad.follow(sina);
        System.out.println("[2] ✅ followers(sina)  → " + listUsernames(sina.getFollowers()));
        System.out.println("[2] ✅ following(sajad) → " + listUsernames(sajad.getFollowing()));
        try { sajad.follow(sina);   } catch (InvalidOperationException e) { System.out.println("[2] ❌ duplicate follow: " + e.getMessage()); }
        try { sajad.follow(null);   } catch (InvalidOperationException e) { System.out.println("[2] ❌ null follow: "     + e.getMessage()); }

        /* 3 ─ REGULAR PLAY LIMIT (6 attempts, limit = 5) */
        System.out.println("\n[3] Regular‑user play limit …");
        Music m1 = new Music("Track1", sina);
        Music m2 = new Music("Track2", sina);
        for (int i = 1; i <= 6; i++) {
            try { System.out.print("   play #" + i + " → "); sajad.playMusic(m1); }
            catch (InvalidOperationException e) { System.out.println("❌ " + e.getMessage()); }
        }

        /* 4 ─ PREMIUM UPGRADE & EXTENSION */
        System.out.println("\n[4] Upgrade to premium …");
        sajad.buyPremium(2);                     // upgrade
        sajad.playMusic(m2);                     // should work now
        sajad.buyPremium(1);                     // extend by 1
        System.out.println("[4] ✅ premium streams count Track2 → " + m2.getNumberOfStream());
        try { sajad.buyPremium(0); }             // invalid month
        catch (InvalidOperationException e) { System.out.println("[4] ❌ " + e.getMessage()); }

        /* 5 ─ PLAYLIST CREATE / ADD / DUPLICATE / PASSWORD */
        System.out.println("\n[5] Playlist creation & guards …");
        sajad.createPlaylist("MyPlaylist");
        Playlist pl = sajad.getPlaylists().get(0);
        try { pl.addMusic(m1, "wrongpass"); }
        catch (InvalidOperationException e) { System.out.println("[5] ❌ wrong password: " + e.getMessage()); }
        pl.addMusic(m1, "12345678");
        pl.addMusic(m2, "12345678");
        System.out.println("[5] ✅ playlist size → " + pl.getPlaylist().size());
        try { pl.addMusic(m1, "12345678"); }
        catch (InvalidOperationException e) { System.out.println("[5] ❌ duplicate song: " + e.getMessage()); }

        /* 6 ─ SEARCH IN PLAYLIST & GLOBAL */
        System.out.println("\n[6] Search tests …");
        System.out.println("[6] playlist 'Track1' size → " + pl.searchInPlaylist("Track1").size());
        System.out.println("[6] playlist 'Track1' by 'sina' → " + (pl.searchInPlaylist("Track1", "sina") != null));
        System.out.println("[6] global search 'Track2' → " +
                (Music.search("Track2", "sina") != null));

        /* 7 ─ PLAYLIST TITLE EDIT, REMOVE, EMPTY REMOVE */
        System.out.println("\n[7] Edit / remove …");
        pl.editTitle("UpdatedTitle", "12345678");
        System.out.println("[7] new title → " + pl.getTitle());
        pl.removeMusic(m1, "12345678");
        System.out.println("[7] after remove size → " + pl.getPlaylist().size());
        try { pl.removeMusic(m1, "12345678"); }
        catch (InvalidOperationException e) { System.out.println("[7] ❌ remove missing song: " + e.getMessage()); }

        /* 8 ─ PLAYLIST PLAY & SHUFFLE */
        System.out.println("\n[8] play ordered:");
        pl.playPlaylist();
        System.out.println("    play shuffled:");
        pl.shufflePlay();

        /* 9 ─ DUPLICATE MUSIC GUARD */
        System.out.println("\n[9] duplicate music guard …");
        try { new Music("Track1", sina); }
        catch (InvalidOperationException e) { System.out.println("[9] ❌ " + e.getMessage()); }

        /* 10 ─ CHANGE PASSWORD & RECHECK PROTECTED OPS */
        System.out.println("\n[10] change password flow …");
        sajad.changePassword("newpass123");
        System.out.println("[10] check new pwd → " + (sajad.checkPassword("newpass123")==1));
        try { pl.addMusic(m1, "12345678"); }
        catch (InvalidOperationException e) { System.out.println("[10] ❌ old password rejected"); }
        pl.addMusic(m1, "newpass123");
        System.out.println("[10] ✅ add with new password ok | final size → " + pl.getPlaylist().size());

        /* 11 ─ STREAM COUNTS SUMMARY */
        System.out.println("\n[11] stream counts:");
        System.out.println("    Track1 → " + m1.getNumberOfStream());
        System.out.println("    Track2 → " + m2.getNumberOfStream());

        System.out.println("\n------------------- END OF TEST -------------------");
    }

    /* single helper kept simple */
    private static String listUsernames(List<User> users) {
        if (users.isEmpty()) return "[empty]";
        StringBuilder sb = new StringBuilder();
        for (User u : users) sb.append(u.getUsername()).append(", ");
        return sb.toString();
    }
}
