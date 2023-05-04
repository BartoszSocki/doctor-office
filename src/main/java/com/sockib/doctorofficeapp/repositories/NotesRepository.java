package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n INNER JOIN n.registeredDoctor d WHERE d.username = :username")
    Page<Note> findAllByDoctorUsername(String username, Pageable pageable);

    @Query("SELECT n FROM Note n INNER JOIN n.registeredDoctor d WHERE d.username = :username AND n.id = :id")
    Optional<Note> findNoteByDoctorUsernameAndId(String username, Long id);

}
