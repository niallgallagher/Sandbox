public class FootballTeam {

    private String teamName;
    private String stadiumName;
    private int stadiumCapacity;
    private String nickName;
    private static final String YESNO = "YesOrNo";
    private static final String YES = "Y";
    private static final String NO = "N";

    public FootballTeam(String teamName, String stadiumName, int stadiumCapacity, String nickName) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.stadiumCapacity = stadiumCapacity;
        this.nickName = nickName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTeamName() { return teamName; }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "teamName='" + teamName + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", stadiumCapacity='" + stadiumCapacity + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';

    }
    /*public static boolean inputChecker(String input, String inputType) {
        switch (inputType) {

            case YESNO: {
                if (input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(NO)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }*/
}

