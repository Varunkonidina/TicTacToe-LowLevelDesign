import java.util.ArrayList;
import java.util.List;

class Game {

    private final Board board;
    private Status status;
    private final List<Player> players; 
    private Player currentPlayer;
    private Player winner;
    public Game(List<Player> players, int n) {
        if (players == null || players.size() != 2)
            throw new IllegalArgumentException("Exactly two players required");
        if (players.get(0) == null ||players.get(1)==null)
            throw new IllegalArgumentException("invalid players");
        if (players.get(0).getSymbol() == null ||players.get(1).getSymbol()==null)
            throw new IllegalArgumentException("Player must have a symbol.");

        if (players.get(0).getSymbol() == players.get(1).getSymbol())
            throw new IllegalArgumentException("Players must have different symbols");
        this.board = new Board(n);
        this.players = new ArrayList<>(players);
        start();
    }
     private void start() {
        board.reset();
        status = Status.IN_PROGRESS;
        winner = null;
        currentPlayer = players.get(0);
    }
    public void makeMove(Move move)
    {
        if (status != Status.IN_PROGRESS)
            throw new IllegalStateException("Game has ended");

        if (move == null || !players.contains(move.getPlayer()))
            throw new IllegalArgumentException("Invalid move");

        Player player = move.getPlayer();
        int row = move.getRow();
        int col = move.getCol();

        if (!currentPlayer.equals(player))
            throw new IllegalArgumentException("Not this player's turn");

        if (row < 0 || row >= board.getSize() ||
            col < 0 || col >= board.getSize())
            throw new IllegalArgumentException("Invalid position");

        if (!board.isCellEmpty(row, col))
            throw new IllegalArgumentException("Cell is already occupied");

        board.placeSymbol(row, col, player.getSymbol());

        if (board.hasWinningLine(row, col)) {
            status = Status.WON;
            winner = player;
        } else if (board.isFull()) {
            status = Status.DRAW;
        } else {
            currentPlayer = players.get(1 - players.indexOf(currentPlayer));
        }
    }
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
    public Status getStatus()
    {
        return status;
    }
    public Player getWinner()
    {
        return winner;
    }
    public void reset()
    {
        start();
    }
}
