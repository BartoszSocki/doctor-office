package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.DoctorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorInfoRepository extends JpaRepository<DoctorInfo, Long> {

    @Query("SELECT i FROM DoctorInfo i INNER JOIN i.registeredUser u WHERE u.username = :username")
    Optional<DoctorInfo> findDoctorInfoByUsername(String username);

    @Query("SELECT i FROM DoctorInfo i INNER JOIN i.registeredUser u WHERE u.id = :id")
    Optional<DoctorInfo> findDoctorInfoById(Long id);

    @Query("SELECT i FROM DoctorInfo i")
    Page<DoctorInfo> findDoctors(Pageable pageable);

}
