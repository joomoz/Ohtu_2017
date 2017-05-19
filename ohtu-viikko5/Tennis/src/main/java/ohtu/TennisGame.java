package ohtu;

public class TennisGame {

    private int player_1_score = 0;
    private int player_2_score = 0;
    private final String player_1_name;
    private final String player_2_name;

    public TennisGame(String name1, String name2) {
        this.player_1_name = name1;
        this.player_2_name = name2;
    }

    public void wonPoint(String name) {
        if (name.equals(player_1_name)) {
            player_1_score++;
        } else {
            player_2_score++;
        }
    }

    public String getScore() {
        String score;
        if (player_1_score == player_2_score) {
            score = getTieScore(player_1_score);
        } else if (player_1_score >= 4 || player_2_score >= 4) {
            score = getAdvantageSituation();
        } else {
            score = getScoreWord(player_1_score) + "-" + getScoreWord(player_2_score);
        }
        return score;
    }

    private String getAdvantageSituation() {
        String advantage;
        int difference = player_1_score - player_2_score;
        if (difference == 1) {
            advantage = "Advantage " + player_1_name;
        } else if (difference == -1) {
            advantage = "Advantage " + player_2_name;
        } else if (difference >= 2) {
            advantage = "Win for " + player_1_name;
        } else {
            advantage = "Win for " + player_2_name;
        }
        return advantage;
    }

    private String getTieScore(int score) {
        String tieScore;
        switch (score) {
            case 0:
                tieScore = "Love-All";
                break;
            case 1:
                tieScore = "Fifteen-All";
                break;
            case 2:
                tieScore = "Thirty-All";
                break;
            case 3:
                tieScore = "Forty-All";
                break;
            default:
                tieScore = "Deuce";
                break;
        }
        return tieScore;
    }

    private String getScoreWord(int score) {
        String word = "";
        switch (score) {
            case 0:
                word = "Love";
                break;
            case 1:
                word = "Fifteen";
                break;
            case 2:
                word = "Thirty";
                break;
            case 3:
                word = "Forty";
                break;
        }
        return word;
    }

}
