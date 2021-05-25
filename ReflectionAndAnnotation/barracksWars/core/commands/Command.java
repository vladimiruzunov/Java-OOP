package barracksWars.core.commands;


import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public abstract class Command implements Executable {

    private Repository repository;
    private UnitFactory unitFactory;
    private String[] data;

    protected Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
        this.data = data;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    public String[] getData() {
        return data;
    }
}
