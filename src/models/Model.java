package models;

/**
 * Models
 */
public class Model {
    private Island island;

    public Model(int size) {
        island = new Island(size);
    }

    public Island getIsland() {
        return island;
    }
}