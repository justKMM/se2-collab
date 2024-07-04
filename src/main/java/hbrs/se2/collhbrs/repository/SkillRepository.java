package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.traits.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
