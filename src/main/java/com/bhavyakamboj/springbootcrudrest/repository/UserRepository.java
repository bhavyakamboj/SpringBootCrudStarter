package com.bhavyakamboj.springbootcrudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhavyakamboj.springbootcrudrest.model.User;
/*
@Repository annotation tells Spring to bootstrap the repository during a component scan.

UserRepository interface extends JpaRepository which provides below methods to deal with database operations:
    List<T> findAll();
    List<T> findAll(Sort sort);
    List<T> findAllById(Iterable<ID> ids);
    <S extends T> List<S> saveAll(Iterable<S> entities);
    void flush();
    <S extends T> S saveAndFlush(S entity);
    void deleteInBatch(Iterable<T> entities);
    void deleteAllInBatch();
    T getOne(ID id);
    @Override
    <S extends T> List<S> findAll(Example<S> example);
    <S extends T> List<S> findAll(Example<S> example, Sort sort);
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}