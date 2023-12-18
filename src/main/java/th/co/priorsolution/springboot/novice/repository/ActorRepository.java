package th.co.priorsolution.springboot.novice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.priorsolution.springboot.novice.entity.ActorEntity;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {
}
