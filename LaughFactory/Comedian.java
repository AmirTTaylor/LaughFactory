package LaughFactory;

import java.util.*;
import java.io.*;


public class Comedian{
    public static void main(String[] args) {
        // Welcome
        
        Scanner s = new Scanner(System.in);
        String choice;
        System.out.println("Welcome to The LaughFactory\n\n" + //
                         "░░░░░░░░░░░░░░░░░░░░░░█████████\n" + //
                         "░░███████░░░░░░░░░░███▒▒▒▒▒▒▒▒███\n" + //
                         "░░█▒▒▒▒▒▒█░░░░░░░███▒▒▒▒▒▒▒▒▒▒▒▒▒███\n" + //
                         "░░░█▒▒▒▒▒▒█░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" + //
                         "░░░░█▒▒▒▒▒█░░░██▒▒▒▒▒██▒▒▒▒▒▒██▒▒▒▒▒███\n" + //
                         "░░░░░█▒▒▒█░░░█▒▒▒▒▒▒████▒▒▒▒████▒▒▒▒▒▒██\n" + //
                         "░░░█████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" + //
                         "░░░█▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒██\n" + //
                         "░██▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒██\n" + //
                         "██▒▒▒███████████▒▒▒▒▒██▒▒▒▒▒▒▒▒██▒▒▒▒▒██\n" + //
                         "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒████████▒▒▒▒▒▒▒██\n" + //
                         "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" + //
                         "░█▒▒▒███████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" + //
                         "░██▒▒▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" + //
                         "░░████████████░░░█████████████████\n\n"+ //
                         "Would you like to hear a joke? (Y/N)");
        
        do{
            choice = s.nextLine().toLowerCase();
            
            if (choice.equals("y")){
                //Tell joke
                break;
            }
            else if (choice.equals("n")){
                System.out.println("You're no Fun, Bye");
                System.exit(0);
            }
            else{
                System.out.println("We can't get to the jokes unless you reply with 'Y' or 'y'");
            }
        }while(!choice.equals("y") || !choice.equals("n"));

        //Load all jokes from Jokes.txt into a arraylist with each index holding a joke
        ArrayList<String> jokes = new ArrayList<String>();
        try {
            Reader r = new FileReader("LaughFactory\\Jokes.txt");
            int data = r.read();
            while(data != -1){
                 StringBuilder joke = new StringBuilder();
                while(data != -1 && data != 10){
                    joke.append((char)data);
                    data = r.read();
                }
                jokes.add(joke.toString());
                data = r.read();
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Pick Random Joke from Arraylist to print
        Random random = new Random();
        String again = "";
        while(!again.equals("n" )){
            if(jokes.size()>0){
                String j = jokes.get(random.nextInt(jokes.size()));
                System.out.println(j + "\n");
                //Remove last joke told from list
                jokes.remove(j);
                System.out.println("Another? (Y/N):");
                again = s.nextLine().toLowerCase();
            }
            else{//Stop when run out of jokes
                System.out.println("We are all out of jokes :(");
                break;
            }
        }
        //Ask user to suggest a joke
        System.out.println("You got any good jokes? (Y/N)");
        String c2;
        do{
            c2 = s.nextLine().toLowerCase();

            if(c2.equals("n")){
                break;
            }else if(c2.equals("y")){
                while(c2.equals("y")){
                    System.out.println("Write your joke and punchline in one line here:");
                    String newjoke = s.nextLine();

                    try(FileWriter wr = new FileWriter("LaughFactory\\SuggestedJokes.txt",true);) {
                        wr.write(System.lineSeparator()+ newjoke);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Anymore? (Y/N)");
                    c2 = s.nextLine().toLowerCase();
                };
                break;
            }
            else{
                System.out.println("Please enter Y or N!");
            }
        }while(!c2.equals("y") || !c2.equals("n"));

        //End Program
        s.close();
        System.out.println("Okay See Ya! Come back with some more jokes for me!!");
    }
}