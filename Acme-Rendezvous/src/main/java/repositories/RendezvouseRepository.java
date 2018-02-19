
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvouse;
import domain.User;

@Repository
public interface RendezvouseRepository extends JpaRepository<Rendezvouse, Integer> {

	// devuelve las rendevouz creadas por un usuario y que no esten eliminadas
	@Query("select r from User u join u.rendezvousesCreated r where u.id=?1 and r.deleted=false")
	Collection<Rendezvouse> findRendezvousesCreatedByUser(int userId);
	// devuelve las rendevouz a las que asiste un usuario y que no esten eliminadas
	@Query("select r from User u join u.rendezvousesCreated r where u.id=?1 and r.deleted=false")
	Collection<Rendezvouse> findRendezvousesAssitedByUser(int userId);

	// devuelve las rendevouz a las que asiste un usuario sin comprobar que sea el principal y que no esten eliminadas
	@Query("select r from User u join u.rendezvousesCreated r where u.id=?1 and r.deleted=false")
	Collection<Rendezvouse> findRendezvousesAssitedByUser2(int userId);

	//Te devuelve todos los asistentes dada una cita concreta 
	@Query("select r.assistants from Rendezvouse r where r.id=?1")
	Collection<User> findAllAssistantsByRendezvous(int rendezvousId);

	@Query("select r from Rendezvouse r where r.organisedMoment>CURRENT_TIMESTAMP")
	Collection<Rendezvouse> AllRendezvousesICanAssist();

	//select u from User u join u.rendezvousesAssisted r where 45 not member of u.rendezvousesAssisted and r.deleted=false and r.organisedMoment>CURRENT_TIMESTAMP;
}
