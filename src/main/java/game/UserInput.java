package game;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private InputStream streamIn;

    public UserInput(InputStream stremIn){
        this.streamIn = stremIn;
    }

    public InputStream getStreamIn() {
        return streamIn;
    }

    public String  getName() {
        Scanner scanner = new Scanner(getStreamIn());
        return scanner.nextLine();
    }

    public Integer getInteger() throws InputMismatchException{
        try {
            Scanner scanner = new Scanner(getStreamIn());
            return scanner.nextInt();
        }catch (InputMismatchException e){
            throw new InputMismatchException("Wrong input my friend");
        }
    }
}
