package th.co.priorsolution.springboot.novice.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import th.co.priorsolution.springboot.novice.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EmployeeRepositoryTestImpl implements EmployeeRepository{
    @Override
    public List<EmployeeEntity> findAll() {
        return null;
    }

    @Override
    public List<EmployeeEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<EmployeeEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<EmployeeEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(EmployeeEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends EmployeeEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends EmployeeEntity> S save(S entity) {
        throw new RuntimeException("xxx");
    }

    @Override
    public <S extends EmployeeEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<EmployeeEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends EmployeeEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends EmployeeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<EmployeeEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public EmployeeEntity getOne(String s) {
        return null;
    }

    @Override
    public EmployeeEntity getById(String s) {
        return null;
    }

    @Override
    public EmployeeEntity getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends EmployeeEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends EmployeeEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends EmployeeEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends EmployeeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends EmployeeEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends EmployeeEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends EmployeeEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
