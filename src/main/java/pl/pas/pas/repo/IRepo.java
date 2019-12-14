package pl.pas.pas.repo;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRepo<T> {

    void add(T t);

    Optional<T> getById(UUID id);

    List<T> getAll();

    void delete(T t);

    void update(T t);
}
