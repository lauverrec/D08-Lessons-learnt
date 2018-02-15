
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvouse;

@Repository
public interface RendezvouseRepository extends JpaRepository<Rendezvouse, Integer> {

	// devuelve las rendevouz creadas por un usuario
	@Query("select u.rendezvousesCreated from User u where u.id=?1")
	Collection<Rendezvouse> findRendezvousesCreatedByUser(int userId);

	// devuelve las rendevouz a las que asiste un usuario
	@Query("select u.rendezvousesAssisted from User u where u.id=?1")
	Collection<Rendezvouse> findRendezvousesAssitedByUser(int userId);

}
