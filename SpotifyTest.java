import java.util.Arrays;
import java.util.InputMismatchException;

public class SpotifyTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {

        System.out.println("Running sPOOtify Test Suite...\n");

        testScenario1_ContentCreation();
        testScenario2_ContentEdgeCases();
        testScenario3_PlaylistDurationCalculation();
        testScenario4_PlaylistFiltering();
        testScenario5_MenuOperations();

        System.out.println("======================================");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("======================================");
    }

    private static void assertTest(boolean condition, String testName) {

        if(condition){

            System.out.println("[PASS] " + testName);
            testsPassed++;

        }else{

            System.out.println("[FAIL] " + testName);
            testsFailed++;
        }
    }

    // 1. Scenario: Verify that content objects are created properly with correct properties
    private static void testScenario1_ContentCreation() {

        try {

            SpootifyMusic music = new SpootifyMusic(
                "Bohemian Rhapsody",
                5.9,
                Arrays.asList("Freddie Mercury"),
                Arrays.asList("Queen"),
                "Rock"
            );

            assertTest(
                music.getTitle().equals("Bohemian Rhapsody") &&
                music.getDuration() == 5.9,
                "Scenario 1: SpootifyMusic creation and property retrieval."
            );

        } catch(Exception e){

            assertTest(false, "Scenario 1: Exception thrown during valid music creation.");
        }
    }

    // 2. Scenario: Verify edge cases
    private static void testScenario2_ContentEdgeCases() {

        boolean exceptionThrown = false;

        try {

            SpootifyPodcast podcast = new SpootifyPodcast(
                "Tech Talk",
                2.0,
                "",
                " "
            );

        } catch(InputMismatchException e){

            exceptionThrown = true;

        } catch(Exception e){
        }

        assertTest(
            exceptionThrown,
            "Scenario 2: Edge Case - Blank inputs properly throw InputMismatchException."
        );
    }

    // 3. Scenario: Verify playlist duration calculation
    private static void testScenario3_PlaylistDurationCalculation() {

        try {

            SpootifyPlaylist playlist = new SpootifyPlaylist("My Workout");

            SpootifyMusic music1 = new SpootifyMusic(
                "Song A",
                2.5,
                Arrays.asList("A"),
                Arrays.asList("A"),
                "Pop"
            );

            SpootifyMusic music2 = new SpootifyMusic(
                "Song B",
                1.5,
                Arrays.asList("B"),
                Arrays.asList("B"),
                "Rock"
            );

            playlist.addContent(music1);
            playlist.addContent(music2);

            boolean durationCorrect = playlist.getDuration() == 4.0;

            assertTest(
                durationCorrect,
                "Scenario 3: Playlist duration accumulation in minutes."
            );

        } catch(Exception e){

            assertTest(false, "Scenario 3: Exception thrown during duration calculation test.");
        }
    }

    // 4. Scenario: Verify playlist filtering
    private static void testScenario4_PlaylistFiltering() {

        try {

            SpootifyPlaylist playlist = new SpootifyPlaylist("Mixed List");

            playlist.addContent(
                new SpootifyMusic(
                    "Music",
                    3.0,
                    Arrays.asList("A"),
                    Arrays.asList("A"),
                    "Pop"
                )
            );

            playlist.addContent(
                new SpootifyPodcast(
                    "Podcast",
                    4.5,
                    "Presenter",
                    "Review"
                )
            );

            playlist.addContent(
                new SpootifyAudiobook(
                    "Audiobook",
                    10.0,
                    "Narrator",
                    "Synopsis",
                    Arrays.asList("Author"),
                    "Pub"
                )
            );

            boolean onlyMusic =
                playlist.filterBy(true, false, false).size() == 1;

            boolean podsAndBooks =
                playlist.filterBy(false, true, true).size() == 2;

            assertTest(
                onlyMusic && podsAndBooks,
                "Scenario 4: Playlist filterBy() properly separates media types."
            );

        } catch(Exception e){

            assertTest(false, "Scenario 4: Exception thrown during playlist filtering test.");
        }
    }

    // 5. Scenario: Verify menu operations
    private static void testScenario5_MenuOperations() {

        try {

            SpootifyMenu menu = new SpootifyMenu();

            boolean libraryExists = menu.playlistExists("library");

            menu.addPlaylist("Chill Vibes");

            boolean customExists = menu.playlistExists("Chill Vibes");

            menu.removePlaylist("Chill Vibes");

            boolean removedSuccessfully =
                !menu.playlistExists("Chill Vibes");

            assertTest(
                libraryExists && customExists && removedSuccessfully,
                "Scenario 5: SpootifyMenu successfully manages playlist lifecycles."
            );

        } catch(Exception e){

            assertTest(false, "Scenario 5: Exception thrown during menu operations test.");
        }
    }
}