package game.hero;

public class Profession {
    private String professionName;
    private int professionId;

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public Profession(String professionName, int professionId) {
        this.professionName = professionName;
        this.professionId = professionId;
    }
}
