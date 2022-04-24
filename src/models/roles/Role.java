package models.roles;

/**
 * enum for all Role
 */
public enum Role {
    Explorateur("greenPawn.png"),
    Ingenieur("redPawn.png"),
    Messager("purplePawn.png"),
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