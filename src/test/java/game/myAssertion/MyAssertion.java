package game.myAssertion;

public class MyAssertion {

    public static void assertDoesNotThrow(FailingRunnable action){
        try{
            action.run();
        }
        catch(Exception ex){
            throw new Error("expected action not to throw, but it did!", ex);
        }
    }

    @FunctionalInterface public interface FailingRunnable { void run() throws Exception; }
}
