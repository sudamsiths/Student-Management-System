package org.example.repository;

import org.example.entity.AttendanceEntity;
import org.example.utill.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByStudentId(Long studentId);

    @Query("SELECT a FROM AttendanceEntity a WHERE a.student.id = :studentId AND a.date BETWEEN :startDate AND :endDate")
    List<AttendanceEntity> findByStudentIdAndDateBetween(@Param("studentId") Long studentId,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

    List<AttendanceEntity> findByDate(LocalDate date);

    List<AttendanceEntity> findByStatusAndDate(AttendanceStatus status, LocalDate date);

    @Query("SELECT a.status, COUNT(a) FROM AttendanceEntity a WHERE a.student.id = :studentId GROUP BY a.status")
    List<Object[]> getAttendanceStatistics(@Param("studentId") Long studentId);
}
