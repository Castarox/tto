package game;

import java.io.InputStream;
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

    public Integer getInteger() throws NumberFormatException{
        Scanner scanner = new Scanner(getStreamIn());
        return scanner.nextInt();
    }
}
