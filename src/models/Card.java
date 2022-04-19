package models;

public enum Card {
    AIR,
    EAU,
    FEU,
    TERRE,
    SAC,
    HELICOPTERE,
    DELUGE;

    public int getTempleID(Card card){
        switch (card) {
            case AIR:
                return 0;
            case TERRE:
                return 1;
            case FEU:
                return 2;
            case EAU:
                return 3;
            default:
                return -1;
        }
    }
}
