import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SpotifyApp {

    public static void clearTerminal(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printSpootifyLogo(){

        System.out.println(
            "\u001B[32m\n" +
            "               ⢀⣠⣤⣤⣶⣶⣶⣶⣤⣤⣄⡀\n" +
            "            ⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⡀\n" +
            "        ⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦\n" +
            "        ⠀⢀⣾⣿⡿⠿⠛⠛⠛⠉⠉⠉⠉⠛⠛⠛⠿⠿⣿⣿⣿⣿⣿⣷⡀\n" +
            "        ⠀⣾⣿⣿⣇⠀⣀⣀⣠⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⠈⠙⠻⣿⣿⣷\n" +
            "        ⢠⣿⣿⣿⣿⡿⠿⠟⠛⠛⠛⠛⠛⠛⠻⠿⢿⣿⣶⣤⣀⣠⣿⣿⣿⡄\n" +
            "        ⢸⣿⣿⣿⣿⣇⣀⣀⣤⣤⣤⣤⣤⣄⣀⣀⠀⠀⠉⠛⢿⣿⣿⣿⣿⡇  sPOOtify\n" +
            "        ⠘⣿⣿⣿⣿⣿⠿⠿⠛⠛⠛⠛⠛⠛⠿⠿⣿⣶⣦⣤⣾⣿⣿⣿⣿⠃\n"
        );
    }

    public static void pressEnter(Scanner dataScanner){

        System.out.println("==================================================================================+++---");
        System.out.println("Press 'ENTER' to go to the menu");
        System.out.println("==================================================================================+++---");

        dataScanner.nextLine();
    }

    public static void closeScanner(Scanner scanner){

        scanner.close();
    }

    public static SpootifyMusic createMusic(Scanner dataScanner){

        Scanner stringCutter;

        String title;
        double duration;

        List<String> songwriters = new ArrayList<String>();
        List<String> interpreters = new ArrayList<String>();

        String bigSW;
        String bigIT;

        String genre;

        System.out.println("Title:");
        title = dataScanner.nextLine();

        System.out.println("Duration in minutes:");
        duration = dataScanner.nextDouble();
        dataScanner.nextLine();

        System.out.println("Genre:");
        genre = dataScanner.nextLine();

        System.out.println("Composers: Separate with ;");
        bigSW = dataScanner.nextLine();

        System.out.println("Interpreters: Separate with ;");
        bigIT = dataScanner.nextLine();

        stringCutter = new Scanner(bigSW);
        stringCutter.useDelimiter(";");

        while(stringCutter.hasNext()){

            songwriters.add(stringCutter.next());
        }

        stringCutter.close();

        stringCutter = new Scanner(bigIT);
        stringCutter.useDelimiter(";");

        while(stringCutter.hasNext()){

            interpreters.add(stringCutter.next());
        }

        stringCutter.close();

        return new SpootifyMusic(
            title,
            duration,
            songwriters,
            interpreters,
            genre
        );
    }

    public static SpootifyPodcast createPodcast(Scanner dataScanner){

        String title;
        double duration;

        String presenter;
        String review;

        System.out.println("Title:");
        title = dataScanner.nextLine();

        System.out.println("Duration in minutes:");
        duration = dataScanner.nextDouble();
        dataScanner.nextLine();

        System.out.println("Presenter:");
        presenter = dataScanner.nextLine();

        System.out.println("Description:");
        review = dataScanner.nextLine();

        return new SpootifyPodcast(
            title,
            duration,
            presenter,
            review
        );
    }

    public static SpootifyAudiobook createAudioook(Scanner dataScanner){

        Scanner stringCutter;

        String title;
        double duration;

        List<String> authors = new ArrayList<String>();

        String bigAT;
        String storyteller;
        String publisher;
        String synopsis;

        System.out.println("Title:");
        title = dataScanner.nextLine();

        System.out.println("Duration in minutes:");
        duration = dataScanner.nextDouble();
        dataScanner.nextLine();

        System.out.println("Publisher:");
        publisher = dataScanner.nextLine();

        System.out.println("Authors: Separate with ;");
        bigAT = dataScanner.nextLine();

        System.out.println("Narrator:");
        storyteller = dataScanner.nextLine();

        System.out.println("Synopsis:");
        synopsis = dataScanner.nextLine();

        stringCutter = new Scanner(bigAT);
        stringCutter.useDelimiter(";");

        while(stringCutter.hasNext()){

            authors.add(stringCutter.next());
        }

        stringCutter.close();

        return new SpootifyAudiobook(
            title,
            duration,
            storyteller,
            synopsis,
            authors,
            publisher
        );
    }

    public static void addToPlaylist(
        Scanner dataScanner,
        String playlistTitle,
        SpootifyMenu menu
    ){

        boolean wannaAdd = true;
        int actualCommand;

        while(wannaAdd){

            clearTerminal();
            printSpootifyLogo();

            if(!playlistTitle.equals("library")){

                List<SpootifyContent> libraryContents =
                    menu.getPlaylist("library").getContentList();

                if(libraryContents.isEmpty()){

                    System.out.println("Your library is empty!");
                    pressEnter(dataScanner);
                    return;
                }

                System.out.println("Select content from library");
                System.out.println("==================================================================================+++---");

                int counter = 1;

                for(SpootifyContent content : libraryContents){

                    System.out.printf(
                        "%d | %s\n",
                        counter,
                        content.toString()
                    );

                    counter++;
                }

                System.out.println("==================================================================================+++---");

                actualCommand = dataScanner.nextInt();
                dataScanner.nextLine();

                if(actualCommand > 0 &&
                   actualCommand <= libraryContents.size()){

                    menu.addContent(
                        playlistTitle,
                        libraryContents.get(actualCommand - 1)
                    );

                    System.out.println("Content added successfully!");

                }else{

                    System.out.println("Invalid option!");
                }

            }else{

                System.out.println("What kind of content do you want to add?");
                System.out.println("==================================================================================+++---");
                System.out.println("1. Music | 2. Podcast | 3. Audiobook");

                actualCommand = dataScanner.nextInt();
                dataScanner.nextLine();

                switch(actualCommand){

                    case 1:

                        try{

                            menu.getPlaylist(playlistTitle)
                                .addContent(createMusic(dataScanner));

                            System.out.println("Music added successfully!");

                        }catch(InputMismatchException e){

                            System.out.println("Invalid input!");
                        }

                        break;

                    case 2:

                        try{

                            menu.getPlaylist(playlistTitle)
                                .addContent(createPodcast(dataScanner));

                            System.out.println("Podcast added successfully!");

                        }catch(InputMismatchException e){

                            System.out.println("Invalid input!");
                        }

                        break;

                    case 3:

                        try{

                            menu.getPlaylist(playlistTitle)
                                .addContent(createAudioook(dataScanner));

                            System.out.println("Audiobook added successfully!");

                        }catch(InputMismatchException e){

                            System.out.println("Invalid input!");
                        }

                        break;
                }
            }

            System.out.println("==================================================================================+++---");
            System.out.println("Do you want to add more content?");
            System.out.println("==================================================================================+++---");
            System.out.println("1. Yes | 2. Back to menu");

            actualCommand = dataScanner.nextInt();
            dataScanner.nextLine();

            if(actualCommand != 1){

                wannaAdd = false;
            }
        }
    }

    public static void showPlaylistContent(
        Scanner dataScanner,
        String playlistTitle,
        SpootifyMenu menu
    ){

        List<SpootifyContent> filteredList =
            new ArrayList<SpootifyContent>();

        int actualCommand;
        int counter;

        clearTerminal();

        System.out.println("What do you want to list?");
        System.out.println("==================================================================================+++---");
        System.out.println("1. Music | 2. Podcasts | 3. Audiobooks | 4. All");
        System.out.println("==================================================================================+++---");

        actualCommand = dataScanner.nextInt();
        dataScanner.nextLine();

        switch(actualCommand){

            case 1:
                filteredList =
                    menu.getPlaylist(playlistTitle)
                        .filterBy(true, false, false);
                break;

            case 2:
                filteredList =
                    menu.getPlaylist(playlistTitle)
                        .filterBy(false, true, false);
                break;

            case 3:
                filteredList =
                    menu.getPlaylist(playlistTitle)
                        .filterBy(false, false, true);
                break;

            case 4:
                filteredList =
                    menu.getPlaylist(playlistTitle)
                        .getContentList();
                break;
        }

        counter = 1;

        clearTerminal();

        if(!filteredList.isEmpty()){

            System.out.println("==================================================================================+++---");
            System.out.println("These are your contents");
            System.out.println("==================================================================================+++---");

            for(SpootifyContent spootifyContent : filteredList){

                System.out.printf(
                    "%d | %s\n",
                    counter,
                    spootifyContent.toString()
                );

                counter++;
            }

        }else{

            System.out.println("There are no contents that meet this requirement :(");
        }

        pressEnter(dataScanner);
    }

    public static void createPlaylist(
        Scanner dataScanner,
        SpootifyMenu menu
    ){

        String playlistTitle;

        printSpootifyLogo();

        System.out.println("==================================================================================+++---");
        System.out.println("Enter the playlist name");
        System.out.println("==================================================================================+++---");

        playlistTitle = dataScanner.nextLine();

        if(!menu.playlistExists(playlistTitle)){

            menu.addPlaylist(playlistTitle);

            System.out.println(
                "The playlist " +
                playlistTitle +
                " was created successfully!"
            );

        }else{

            System.out.println(
                "A playlist named " +
                playlistTitle +
                " already exists!"
            );
        }

        pressEnter(dataScanner);
    }

    public static void deletePlaylist(
        Scanner dataScanner,
        SpootifyMenu menu
    ){

        String playlistTitle;

        printSpootifyLogo();

        System.out.println("==================================================================================+++---");
        System.out.println("Enter the playlist name");
        System.out.println("==================================================================================+++---");

        playlistTitle = dataScanner.nextLine();

        if(menu.playlistExists(playlistTitle) &&
           !playlistTitle.equals("library")){

            menu.removePlaylist(playlistTitle);

            System.out.println(
                "The playlist " +
                playlistTitle +
                " was removed successfully!"
            );

        }else if(playlistTitle.equals("library")){

            System.out.println("Cannot delete the library!");

        }else{

            System.out.println(
                "There is no playlist named " +
                playlistTitle +
                "!"
            );
        }

        pressEnter(dataScanner);
    }

    public static void showPlaylists(
        Scanner dataScanner,
        SpootifyMenu menu
    ){

        int counter = 1;

        if(menu.getPlaylists().size() > 1){

            System.out.println("==================================================================================+++---");
            System.out.println("These are your playlists");
            System.out.println("==================================================================================+++---");

            for(String playlistTitle : menu.getPlaylists().keySet()){

                if(!playlistTitle.equals("library")){

                    System.out.printf(
                        "%d | %s\n",
                        counter,
                        playlistTitle
                    );

                    counter++;
                }
            }

        }else{

            System.out.println("You have no playlists :(");
        }

        pressEnter(dataScanner);
    }

    public static void main(String[] args){

        SpootifyMenu myMenu = null;

        try{

            FileInputStream fileIn =
                new FileInputStream("C:\\spootify.dat");

            ObjectInputStream in =
                new ObjectInputStream(fileIn);

            myMenu = (SpootifyMenu) in.readObject();

            in.close();
            fileIn.close();

        }catch(IOException | ClassNotFoundException e){

            myMenu = new SpootifyMenu();
        }

        Scanner dataScanner = new Scanner(System.in);

        int actualCommand = 0;
        String playlistTitle;

        clearTerminal();
        printSpootifyLogo();

        System.out.println("Hello, welcome to sPOOtify!\n");

        pressEnter(dataScanner);

        while(actualCommand != 8){

            clearTerminal();
            printSpootifyLogo();

            System.out.println("Select an option");
            System.out.println("==================================================================================+++---");
            System.out.println("1. Fill your Library                    | 2. List library contents");
            System.out.println("3. View Library info                    | 4. Create a new Playlist");
            System.out.println("5. Delete a Playlist                    | 6. Add content to a Playlist");
            System.out.println("7. List Playlists                       | 8. Exit");
            System.out.println("==================================================================================+++---");

            actualCommand = dataScanner.nextInt();
            dataScanner.nextLine();

            clearTerminal();
            printSpootifyLogo();

            switch(actualCommand){

                case 1:
                    addToPlaylist(dataScanner, "library", myMenu);
                    break;

                case 2:
                    showPlaylistContent(dataScanner, "library", myMenu);
                    break;

                case 3:
                    System.out.println(
                        myMenu.getPlaylist("library").getDescription()
                    );

                    pressEnter(dataScanner);
                    break;

                case 4:
                    createPlaylist(dataScanner, myMenu);
                    break;

                case 5:
                    deletePlaylist(dataScanner, myMenu);
                    break;

                case 6:

                    System.out.println("==================================================================================+++---");
                    System.out.println("Enter the playlist name");
                    System.out.println("==================================================================================+++---");

                    playlistTitle = dataScanner.nextLine();

                    if(myMenu.playlistExists(playlistTitle)){

                        addToPlaylist(
                            dataScanner,
                            playlistTitle,
                            myMenu
                        );

                    }else{

                        System.out.println(
                            "This playlist does not exist!"
                        );

                        pressEnter(dataScanner);
                    }

                    break;

                case 7:
                    showPlaylists(dataScanner, myMenu);
                    break;

                case 8:

                    System.out.println("Saving your data...");

                    try{

                        FileOutputStream fileOut =
                            new FileOutputStream("C:\\spootify.dat");

                        ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);

                        out.writeObject(myMenu);

                        out.close();
                        fileOut.close();

                        System.out.println("Data saved successfully!");

                    }catch(IOException e){

                        e.printStackTrace();
                    }

                    System.out.println("Exiting...");
                    break;
            }
        }

        closeScanner(dataScanner);
    }
}