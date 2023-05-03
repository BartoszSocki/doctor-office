package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotesRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n INNER JOIN n.registeredDoctor d WHERE d.id = :doctorId")
    Page<Note> findAllByDoctorId(Long doctorId, Pageable pageable);

}
