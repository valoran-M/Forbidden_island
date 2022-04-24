package models;

/**
 * Card enum
 */
public enum Card {
    AIR,
    EAU,
    FEU,
    TERRE,
    SAC,
    HELICOPTERE,
    DELUGE;

    /**
     * retun temple Id when is key for temple
     * 
     * @param card
     * @return
     */
    public int getTempleID(Card card) {
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

    /**
     * return temple card with temple id
     * 
     * @param id
     * @return
     */
    public static Card getCardTemple(int id) {
        switch (id) {
            case 0:
                return AIR;
            case 1:
                return TERRE;
            case 2:
                return FEU;
            case 3:
                return EAU;
            default:
                return null;
        }
    }
}
