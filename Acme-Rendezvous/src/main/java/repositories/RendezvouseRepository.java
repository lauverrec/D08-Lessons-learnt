
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Rendezvouse;

@Repository
public interface RendezvouseRepository extends JpaRepository<Rendezvouse, Integer> {

}
