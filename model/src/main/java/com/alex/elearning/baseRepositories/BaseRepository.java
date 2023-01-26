package com.alex.elearning.baseRepositories;

import com.alex.elearning.entities.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    List<T> findAll();

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    Iterable<T> findAll(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    Page<T> findAll(Pageable pageable);

    @Transactional
    Page<T> findAll(Specification<T> spec,  Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deleted = false")
    Optional<T> findById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.deleted = false")
    long count();

    @Transactional(readOnly = true)
    default boolean exists(ID id) {
        return findById(id) != null;
    }

    @Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
    @Transactional
    @Modifying
    void softDelete(ID id);

    @Transactional
    default void softDelete(T entity) {
        softDelete((ID) entity.getId());
    }

    @Transactional
    default void softDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> softDelete((ID) entity.getId()));
    }



    @Query("update #{#entityName} e set e.deleted = true")
    @Transactional
    @Modifying
    void softDeleteAll();


}