# Spotify-Like Music Player (OOP Midterm Project)

## 📌 Project Overview

This project simulates a basic music player application inspired by Spotify. It has been developed with a strong focus on **Object-Oriented Programming (OOP)** principles in Java, including:

- **Encapsulation**
- **Polymorphism**
- **Interfaces & Strategy Pattern**
- **Exception Handling**

---

## 🌟 Key Features

### ✅ User Types
- **Regular Users**
  - Can play up to 5 songs
  - Cannot create playlists
- **Premium Users**
  - Unlimited playback
  - Can create, manage and shuffle playlists

### 🎵 Music
- Each `Music` object contains a title, a singer (`User`), and a stream counter
- Static `search()` methods for lookup by title or title + singer
- Prevents duplicate songs (same title & singer)

### 📂 Playlist
- Owned by a user
- Editable only with the correct password
- Prevents duplicate tracks
- Supports title editing, adding/removing music, and both sequential & shuffled playback
- Search functionality inside playlists

### 👥 Social
- Users can follow/unfollow each other
- Followers/following relationships are tracked

---

## 🧪 Testing

The `Main.java` file includes a **full test suite**, covering:

- Valid & invalid user creation
- Follower system validation
- Music creation, playback limits
- Behavior switching via `buyPremium()`
- Playlist creation, password validation, and management
- Song duplication prevention
- Stream count tracking
- Search functionality for songs and playlists
- Password change verification

All output is formatted with step-by-step indicators and clear messages.

---

## 🗂 Project Structure

```
src/
├── Main.java
└── mainPackage/
    ├── InvalidOperationException.java
    ├── Music.java
    ├── Playlist.java
    ├── PremiumBehavior.java
    ├── RegularBehavior.java
    ├── User.java
    ├── UserBehavior.java
```

---

## 🚀 How to Run

1. Compile all `.java` files in the `src` folder:
   ```bash
   javac src/mainPackage/*.java src/Main.java
   ```

2. Run the main test:
   ```bash
   java -cp src Main
   ```

3. Review the output in the terminal.

---

## 📚 Notes

- All exception messages are clear and informative
- The entire project is modular, testable, and easy to maintain
- Written using best practices for Java OOP and clean code

---

## 🧑‍💻 Author

Sajjad Sarookhani — Midterm Project for Object-Oriented Programming, 1403-1404

