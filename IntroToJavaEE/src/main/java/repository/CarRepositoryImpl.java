package repository;

public class CarRepositoryImpl implements CarRepository {
    @Override
    public String findCarById(String id) {
        return "BMW";
    }
}
