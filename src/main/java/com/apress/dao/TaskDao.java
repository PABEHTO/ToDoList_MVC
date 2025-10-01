package com.apress.dao;

import com.apress.entity.State;
import com.apress.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional //теперь каждый метод будет в транзакции
public class TaskDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Task> findAllTasks() {
        Query query = entityManager.createQuery("SELECT t FROM Task t");
        List<Task> tasks = query.getResultList();
        return tasks;
    }

    public void saveTask(Task task) {
        entityManager.persist(task);
    }

    public void finishTask(int id) {
        Query query = entityManager.createQuery("UPDATE Task t SET t.state = :state WHERE t.id = :id");
        query.setParameter("state", State.DONE);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void deleteTask(int id) {
        Query query = entityManager.createQuery("DELETE FROM Task t WHERE t.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
