package com.apress.dao;

import com.apress.entity.State;
import com.apress.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class TaskDao {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public TaskDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private final List<Task> tasks = new ArrayList<Task>(
            Arrays.asList(
                    new Task("Buy a ticket", State.ACTIVE),
                    new Task("Wipe the table", State.ACTIVE),
                    new Task("Fill the water barrel", State.DONE)
            )
    );

    public List<Task> findAllTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT t FROM Task t");
            List<Task> tasks = query.getResultList();

            entityManager.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }

    public void saveTask(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(task);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void finishTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setState(State.DONE);
                break;
            }
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("UPDATE Task t SET t.state = :state WHERE t.id = :id");
            query.setParameter("state", State.DONE);
            query.setParameter("id", id);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

    public void deleteTask(int id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("DELETE FROM Task t WHERE t.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
