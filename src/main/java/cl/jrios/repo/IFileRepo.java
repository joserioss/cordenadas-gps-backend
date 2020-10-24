package cl.jrios.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jrios.model.File;

public interface IFileRepo extends JpaRepository<File, Integer>{

}
