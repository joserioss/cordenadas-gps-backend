package cl.jrios.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import cl.jrios.model.FileModel;

public interface IFileService {

	/**
	 * Metodo para crear la carpeta donde vamos a guardar los archivos
	 */

	public void init();
	
	/**
	 * Metodo para cargar un archivo
	 */
	public Resource loadFile(String filename);
	

	/**
	 * Metodo para guardar los archivos
	 */
	public FileModel saveFile(MultipartFile file);
	
	public void saveDB(FileModel file);

	
	/**
	 * Metodo para Cargar todos los archivos
	 */
	public Stream<Path> loadAllFile();
	
	public List<FileModel> LoadAllDB();

	
	/**
	 * Metodo para borrar todos los archivos cada vez que se inicie el servidor
	 */
	public void deleteAllFile();


	/**
	 * Metodo para Borrar un archivo
	 */
	public String deleteFile(String filename);

	
}
