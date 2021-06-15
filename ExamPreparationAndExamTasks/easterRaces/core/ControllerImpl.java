package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> riderRepository;
    private Repository<Car> motorcycleRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        //CreateDriver {name}
        if (riderRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver driverToAdd = new DriverImpl(driver);
        riderRepository.add(driverToAdd);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        //•   CreateCar {car type} {model} {horsepower}
        if (motorcycleRepository.getByName(model) != null){
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car = null;
        switch (type){
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }

        motorcycleRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        //•   AddCarToDriver {driver name} {car name}
        Driver driver = riderRepository.getByName(driverName);
        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = motorcycleRepository.getByName(carModel);
        if (car == null){
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        //•   AddDriverToRace {race name} {driver name}
        Race race = raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = riderRepository.getByName(driverName);
        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        //•   CreateRace {name} {laps}
        Race race = raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> list = race.getDrivers().stream()
                .sorted((a, b) -> Double.compare(b.getCar().calculateRacePoints(race.getLaps()), a.getCar().calculateRacePoints(race.getLaps())))
                .limit(3)
                .collect(Collectors.toList());

        String first = String.format(DRIVER_FIRST_POSITION, list.get(0).getName(), raceName);
        String second = String.format(DRIVER_SECOND_POSITION, list.get(1).getName(), raceName);
        String third = String.format(DRIVER_THIRD_POSITION, list.get(2).getName(), raceName);

        raceRepository.remove(race);

        return String.format("%s%n%s%n%s",first, second, third);
    }

    @Override
    public String createRace(String name, int laps) {
        //•   StartRace {race name}
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}