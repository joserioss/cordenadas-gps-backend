package cl.jrios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.jrios.model.MetadataGPS;
import cl.jrios.repo.IMetadataGPSRepo;
import cl.jrios.service.IMetadatoService;

@Service
public class MetadatoGPSServiceImpl implements IMetadatoService{

	@Autowired
	private IMetadataGPSRepo repo;
	
	@Override
	public void save(MetadataGPS gps) {
		repo.save(gps);
	}

}
