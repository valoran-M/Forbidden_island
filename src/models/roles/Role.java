package models.roles;

public enum Role {
    Explorateur("greenPawn.png"),
    Ingenieur("redPawn.png"),
    Messager("grayPawn.png"),
    Pilote("bluePawn.png"),
    Navigateur("yellowPawn.png"),
    Plongeur("blackPawn.png");

    private String image;

    Role(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }
}