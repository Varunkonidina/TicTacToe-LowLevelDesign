class Board {
    private class Cell {
    private Symbol symbol;

    public boolean isEmpty() {
        return symbol == null;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    void placeSymbol(Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol cannot be null");
        }
        if (!isEmpty()) {
            throw new IllegalStateException("Cell is already occupied");
        }
        this.symbol = symbol;
    }

}
    private Cell[][] gameBoard;
    private int row[];
    private int col[];
    private int diag = 0;
    private int antidiag = 0;
    private int filled = 0;
    private int n;

    public Board(int n) {
        if(n<=0)
            throw new IllegalArgumentException("invalied board size");
        this.gameBoard = new Cell[n][n];
        this.n = n;
        this.row = new int[n];
        this.col = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                gameBoard[i][j] = new Cell();
    }
    private void isvalid(int r,int c)
    {
        if (r >= n || r < 0 || c >= n || c < 0)
            throw new IndexOutOfBoundsException();
    }
    public Symbol getSymbol(int r,int c)
    {
        isvalid(r, c);
        return gameBoard[r][c].getSymbol();
    }
    private Cell getCell(int r, int c) {

        return gameBoard[r][c];
    }

    public void placeSymbol(int r, int c, Symbol symbol) {
        isvalid(r, c);
        this.getCell(r, c).placeSymbol(symbol);
        if (symbol == Symbol.X) {
            row[r]++;
            col[c]++;
            if (r == c)
                diag++;
            if (r + c == gameBoard.length - 1)
                antidiag++;
        } else {
            row[r]--;
            col[c]--;
            if (r == c)
                diag--;
            if (r + c == gameBoard.length - 1)
                antidiag--;
        }
        filled++;

    }

    public boolean hasWinningLine(int r, int c) {

         isvalid(r, c);
        
        if (Math.abs(this.row[r]) == n || Math.abs(this.col[c]) == n || Math.abs(this.diag) == n
                || Math.abs(this.antidiag) == n)
            return true;
        return false;
    }

    public boolean isFull() {
        return filled == n * n;
    }

    public void reset() {
        this.gameBoard = new Cell[n][n];
        this.row = new int[n];
        this.col = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                gameBoard[i][j] = new Cell();
        diag = 0;
        antidiag = 0;
        filled = 0;
    }

    public int getSize() {
        return n;
    }
    public boolean isCellEmpty(int r,int c)
    {
        return getCell(r, c).isEmpty();
    }
}
