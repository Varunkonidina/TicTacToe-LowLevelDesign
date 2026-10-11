import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Player p1 = new Player("Varun", Symbol.X);
        Player p2 = new Player("Rahul", Symbol.O);

        Game game = new Game(List.of(p1, p2), 3);
        

        game.makeMove(new Move(p1, 0, 0));
        game.makeMove(new Move(p2, 1, 1));

    }
}
