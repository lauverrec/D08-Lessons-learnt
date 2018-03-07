
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id=?1")
	User findByUserAccountId(int userAccountId);

	@Query("select u from User u join u.rendezvousesCreated a where a.id=?1")
	User findUserByRendezvousId(int rendezvousId);

}
