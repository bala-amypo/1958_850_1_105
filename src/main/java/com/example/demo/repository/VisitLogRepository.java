package com.example.demo.repository;
import com.example.demo.entity.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitLogRepository extends JpaRepository<VisitLog, Long> {
    List<VisitLog> findByCheckOutTimeIsNull();
    List<VisitLog> findByHostId(Long hostId);
    List<VisitLog> findByVisitorId(Long visitorId);
    Optional<VisitLog> findByVisitorIdAndCheckOutTimeIsNull(Long visitorId);
}
