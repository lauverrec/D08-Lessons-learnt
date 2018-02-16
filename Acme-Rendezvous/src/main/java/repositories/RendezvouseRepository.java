
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvouse;

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

}
