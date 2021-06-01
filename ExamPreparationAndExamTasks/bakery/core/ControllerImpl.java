package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.common.enums.BakedFoodType;
import bakery.common.enums.DrinkType;
import bakery.common.enums.TableTYpe;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {


    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;

        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.totalIncome = 0.0;
    }


    @Override
    public String addFood(String type, String name, double price) {
       BakedFood bakedFood = this.foodRepository.getByName(name);
       if (bakedFood != null){
           throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST
                   ,type,  name));
       }
        BakedFoodType bakedFoodType = BakedFoodType.valueOf(type);
       if (bakedFoodType.equals(BakedFoodType.Bread)){
           bakedFood = new Bread(name, price);
       }else if (bakedFoodType.equals(BakedFoodType.Cake)){
           bakedFood = new Cake(name,price);
       }
       this.foodRepository.add(bakedFood);
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = this.drinkRepository.getByNameAndBrand(name, brand);
        if (drink != null){
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        DrinkType drinkType = DrinkType.valueOf(type);
        if (drinkType.equals(DrinkType.Tea)){
            drink = new Tea(name, portion, brand);
        }else if (drinkType.equals(DrinkType.Water)){
            drink = new Water(name, portion, brand);
        }
        this.drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table != null){
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }
        TableTYpe tableTYpe = TableTYpe.valueOf(type);
        if (tableTYpe.equals(tableTYpe.InsideTable)){
        table = new InsideTable(tableNumber, capacity);
        }else if (tableTYpe.equals(tableTYpe.OutsideTable)){
            table = new OutsideTable(tableNumber, capacity);
        }
        this.tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        for (Table table : this.tableRepository.getAll()) {
            if (!table.isReserved() && table.getCapacity() >= numberOfPeople){
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null|| !table.isReserved()){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood bakedFood = this.foodRepository.getByName(foodName);
        if (bakedFood == null){
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }
        table.orderFood(bakedFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null){
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }
        table.orderDrink(drink);
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        double bill = table.getBill();
        this.totalIncome += bill;
        table.clear();
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder sb = new StringBuilder();
        for (Table table : this.tableRepository.getAll()) {
            if (!table.isReserved()){
                sb.append(table.getFreeTableInfo())
                        .append(System.lineSeparator());
            }

        }
        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, this.totalIncome);
    }
}
