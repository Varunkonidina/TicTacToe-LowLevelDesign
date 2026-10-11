classDiagram
    class Symbol {
        <<enumeration>>
        X
        O
        EMPTY
    }
    class GameStatus {
        <<enumeration>>
        IN_PROGRESS
        X_WON
        O_WON
        DRAW
    }
    class Cell {
        -int row
        -int col
        -Symbol symbol
        +isEmpty() boolean
        +setSymbol(Symbol s) void
    }
    class Board {
        -Cell[][] grid
        +initialize() void
        +makeMove(Move move) boolean
        +isFull() boolean
        +getCell(int r, int c) Cell
    }
    class Move {
        +int row
        +int col
        +Symbol symbol
    }
    class Player {
        -String name
        -Symbol symbol
        +takeTurn(Board board) Move
    }
    class GameEngine {
        -Board board
        -Player player1
        -Player player2
        -Player currentPlayer
        -GameStatus status
        +startGame() void
        +switchPlayer() void
        +checkWinner() GameStatus
        +playTurn() void
    }
    class GameUI {
        <<interface>>
        +render(Board board) void
        +showWinner(GameStatus status) void
        +getInput() Move
    }

    Board *-- Cell
    GameEngine --> Board
    GameEngine --> Player
    GameEngine --> GameStatus
    Player --> Symbol
    Cell --> Symbol
    Player ..> Move
    GameEngine ..> GameUI
    Board ..> Move
