package game.ui;

import game.enums.Seed;
import game.iterators.BoardIterator;
import game.model.Cell;

import java.io.PrintStream;
import java.util.List;

public class ViewConsole {

    public ViewConsole(PrintStream outputStream){
        System.setOut(outputStream);
    }

    public void printBoard(List<List<Cell>> board){
        BoardIterator boardIterator = new BoardIterator(board);
        Integer seedPrinted = 0;
        StringBuilder boardToPrint = new StringBuilder();
        while (boardIterator.hasNext()){
            Character seedToAdd = chooseCrossNoughtOrEmpty(boardIterator.next());
            boardToPrint.append(seedToAdd);
            seedPrinted++;
            String wallFloorOrEnd = chooseWallFloorOrEnd(seedPrinted);
            boardToPrint.append(wallFloorOrEnd);
        }
        System.out.println(boardToPrint.toString());
    }

    public void getNameMessage(Integer playerIndex){
        Integer playerNumber = playerIndex + 1;
        System.out.println("Player " + playerNumber.toString() + " enter Name");
    }

    void wrongInputMessage(){
        System.out.println("Wrong input try again");
    }

    public void getRowMessage(){
        System.out.println("Enter row number (1,3)");
    }

    public void getColumnMessage(){
        System.out.println("Enter column number (1,3)");
    }

    public void winMessage(String name){
        System.out.println("Player " + name + " WIN!!!");
    }

    void drawMessage(){
        System.out.println("DRAW");
    }

    public void turnMessage(String name){
        System.out.println("Player " + name + " turn");
    }

    public void errorMessage(String error){
        System.out.println(error);
    }

    public void startMessage(){
        System.out.println("Welcome to tic tac toe game\n" +
                "Rules:" +
                "1) If you try to cheat you lose move\n" +
                "2) GAME HAVE ONLY 9 ROUNDS");
    }

    private Character chooseCrossNoughtOrEmpty(Cell cell){
        if (cell.getSeed() == Seed.CROSS){
            return 'X';
        }
        else if (cell.getSeed() == Seed.NOUGHT) {
            return 'O';
        }
        return ' ';
    }

    private String chooseWallFloorOrEnd(Integer seedPrinted){
        if (seedPrinted == 9 ){
            return "";
        }
        else if (seedPrinted % 3 == 0){
            return "\n-----\n";
        }
        return "|";
    }
}
