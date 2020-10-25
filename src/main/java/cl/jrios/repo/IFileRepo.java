package cl.jrios.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jrios.model.FileModel;

public interface IFileRepo extends JpaRepository<FileModel, Integer>{
	Optional<FileModel> findByName(String filename);
}
