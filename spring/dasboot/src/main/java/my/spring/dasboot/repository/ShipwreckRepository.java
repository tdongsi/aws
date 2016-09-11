package my.spring.dasboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.spring.dasboot.model.Shipwreck;

public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long> {

}
