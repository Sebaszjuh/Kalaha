package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.board.BoardFactory;
import com.sgriendt.board.BoardStatus;
import com.sgriendt.exception.KalahaIllegalGameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    private BoardController boardController;
    private PlayType playType;

    public void start() {
        playType = gameTypeInput();
        boardController = new BoardController(BoardFactory.createBoard(), playType);
        startGame();
    }

    private void startGame() {
        while (!BoardStatus.isGameEnd(boardController.board().getGameStatus())) {
            playType.playGame(boardController);
        }
        afterGameIsFinished();
        boardController.printBoard();
        boardController.printWinner();
    }


    public void afterGameIsFinished() {
        if(!BoardStatus.isGameEnd(boardController.board().getGameStatus())){
            throw new KalahaIllegalGameException("The game is not finished, illegal to cleanup");
        }
        boardController.cleanupBoard();
        boardController.updateBoardStatusWithWinner();
    }

    public PlayType gameTypeInput() {
        String template = """
                Please select which gametype u want to start
                1 - Multiplayer ( You play by yourself against an opponent)
                2 - Random int generator (For demo purposes)
                """;
        System.out.println(template);
        String input;
        do {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                input = reader.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!InputValidation.validNumberInput(input));
        int type = Integer.parseInt(input);
        if(type == 1) {
            return new MultiplayerType();
        } else {
            return new RandomIntType();
        }
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public PlayType getPlayType() {
        return playType;
    }
}
