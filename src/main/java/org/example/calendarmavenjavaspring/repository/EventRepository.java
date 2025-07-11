package org.example.calendarmavenjavaspring.repository;

import org.example.calendarmavenjavaspring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUserId(Long userId);

}
