package com.abociné.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.abociné.dao.User;

public class UserServiceImpl implements UserService {

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("WebService");
	EntityManager em = emf.createEntityManager();

	public User addUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}

	public List<User> findAllUser() {
		Query query = em.createQuery("FROM User c");
		List<User> results = query.getResultList();
		return results;
	}

	@Override
	public User getUserById(int id) {
		Query query = em.createQuery("FROM User c where c.id=:id")
				.setParameter("id", id);
		User results = (User) query.getSingleResult();
		return results;
	}

	@Override
	public void deleteUser(int id) {
		User user = getUserById(id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}

	@Override
	public void updateUser(int id, User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();

	}

	@Override
	public List<User> searchUser(String input) {

		Query query = em.createQuery("FROM User c where c.nom LIKE :nom OR c.prenom LIKE :prenom OR c.email LIKE :email")
				.setParameter("nom",'%'+ input +'%').setParameter("prenom",'%'+ input +'%').setParameter("email",'%'+ input +'%');
		return query.getResultList();
	}

}
