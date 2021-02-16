package Core.Enum;

public enum DifficultyChoice {
    easy("Partie facile"),
    normal("Partie normale"),
    hard("Partie difficile");

    private String name;
    DifficultyChoice(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override public String toString() { return getName(); }

}
