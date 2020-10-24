package cl.jrios.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jrios.model.FileModel;

public interface IFileRepo extends JpaRepository<FileModel, Integer>{

}
