classDiagram
    %% Class Interactivity (Click Navigation)
    click Symbol "https://github.com" "Go to Symbol source"
    click Status "https://github.com" "Go to Status source"
    click Cell "https://github.com" "Go to Board source (Inner Cell)"
    click Board "https://github.com" "Go to Board source"
    click Move "https://github.com" "Go to Move source"
    click Player "https://github.com" "Go to Player source"
    click Game "https://github.com" "Go to Game source"
    click App "https://github.com" "Go to App source"

    %% Relationships
    Board *-- Cell : "Composition (Inner Class)"
    Game o-- Player : "Aggregation (List of 2)"
    Game *-- Board : "Composition"
    Game --> Status : "Tracks State"
    Cell --> Symbol : "References"
    Player --> Symbol : "Has a"
    Move --> Player : "Belongs to"
    Game ..> Move : "Processes"
    App ..> Game : "Orchestrates"

    class Symbol {
        <<enumeration>>
        X
        O
    }

    class Status {
        <<enumeration>>
        IN_PROGRESS
        WON
        DRAW
    }

    class Cell {
        -Symbol symbol
        +isEmpty() boolean
        +getSymbol() Symbol
        ~placeSymbol(Symbol symbol) void
    }

    class Board {
        -Cell[][] gameBoard
        -int[] row
        -int[] col
        -int diag
        -int antidiag
        -int filled
        -int n
        +Board(int n)
        -isvalid(int r, int c) void
        +getSymbol(int r, int c) Symbol
        -getCell(int r, int c) Cell
        +placeSymbol(int r, int c, Symbol symbol) void
        +hasWinningLine(int r, int c) boolean
        +isFull() boolean
        +reset() void
        +getSize() int
        +isCellEmpty(int r, int c) boolean
    }

    class Player {
        -String name
        -Symbol symbol
        +getName() String
        +getSymbol() Symbol
    }

    class Move {
        -Player player
        -int row
        -int col
        +getPlayer() Player
        +getRow() int
        +getCol() int
    }

    class Game {
        -Board board
        -Status status
        -List~Player~ players
        -Player currentPlayer
        -Player winner
        +Game(List~Player~ players, int n)
        -start() void
        +makeMove(Move move) void
        +getCurrentPlayer() Player
        +getStatus() Status
        +getWinner() Player
        +reset() void
    }

    class App {
        +main(String[] args) void
    }
