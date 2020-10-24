package cl.jrios.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import cl.jrios.model.FileModel;
import cl.jrios.model.MetadataGPS;
import cl.jrios.repo.IFileRepo;
import cl.jrios.repo.IMetadataGPSRepo;
import cl.jrios.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {
	
	@Autowired
	private IFileRepo repo;

	@Autowired
	private IMetadataGPSRepo repoGPS;
	
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("No se puede inicializar la carpeta uploads");
        }
    }

    @Override
    public FileModel saveFile(MultipartFile file) {
    	FileModel newFile = new FileModel();
        try {
        	Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        	File file_metadata = new File(this.root.resolve(file.getOriginalFilename()).toString());
            
        	String name = file.getOriginalFilename();
            String url = "localhost:8080/files/" + name;
            newFile.setName(name);
            newFile.setUrl(url);
            saveDB(newFile);
            try {
				Metadata metadata = ImageMetadataReader.readMetadata(file_metadata);
				MetadataGPS gps = new MetadataGPS();
				
				for (Directory directory : metadata.getDirectories()) {
				    for (Tag tag : directory.getTags()) {
				    	if(directory.getName().equals("GPS")) {
				    		if(tag.getTagName().equals("GPS Latitude Ref")) {
				    			gps.setLat_ref(tag.getDescription());
				    		}
				    		if(tag.getTagName().equals("GPS Latitude")) {
				    			String latitud = tag.getDescription();
				    			String[] parts = latitud.split(" ");
				    			String grado = parts[0]; 
				    			gps.setLat_grad(grado);
				    			String minuto = parts[1]; 
				    			gps.setLat_min(minuto);
				    			String segundo = parts[2];
				    			gps.setLat_sec(segundo);
				    		}
				    		if(tag.getTagName().equals("GPS Longitude Ref")) {
				    			gps.setLon_ref(tag.getDescription());
				    		}
				    		if(tag.getTagName().equals("GPS Longitude")) {
				    			String longitud = tag.getDescription();
				    			String[] parts = longitud.split(" ");
				    			String grado = parts[0]; 
				    			gps.setLon_grad(grado);
				    			String minuto = parts[1]; 
				    			gps.setLon_min(minuto);
				    			String segundo = parts[2];
				    			gps.setLon_sec(segundo);
				    		}
				    	}		
				    	repoGPS.save(gps);
				    	
				    }
				    if (directory.hasErrors()) {
				        for (String error : directory.getErrors()) {
				            System.err.format("ERROR: %s", error);
				        }
				    }
				}
			} catch (ImageProcessingException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
        	System.out.println(e.getMessage());
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
        return newFile;
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllFile() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAllFile(){
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }

    @Override
    public String deleteFile(String filename){
        try {
            Files.deleteIfExists(this.root.resolve(filename));
                return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "Error Borrando ";
        }
    }
    
    @Override
    public void saveDB(FileModel file) {
    	repo.save(file);
    }

	@Override
	public List<FileModel> LoadAllDB() {
		return repo.findAll();
	}

//	@Override
//	public void deleteDBAll() {
//		repo.deleteAll();
//	}

}
