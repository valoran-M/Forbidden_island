package models.roles;

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

    public String getString(Role role) {
        
        switch (role) {
            case Explorateur:

                break;

            case Ingenieur:

                break;

            case Messager:

                break;

            case Pilote:

                break;

            case Navigateur:

                break;

            case Plongeur:

                break;

            default:
                break;
        }
        return role.toString();
    }
}