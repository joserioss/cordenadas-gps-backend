package cl.jrios.util;

import java.util.ArrayList;
import java.util.List;

import cl.jrios.model.MetadataGPS;

public class Conversor {
	
	public List<String> GMS_GD(MetadataGPS gps) {
		List<String> LatLon = new ArrayList<>();
		
		//Adquisicion de datos
		String latRef = gps.getLat_ref(); 
		String latGrad = gps.getLat_grad();
		String latMin = gps.getLat_min();
		String latSec = gps.getLat_sec();
		String lonRef = gps.getLon_ref(); 
		String lonGrad = gps.getLon_grad();
		String lonMin = gps.getLon_min();
		String lonSec = gps.getLon_sec();

		if(latGrad == null || latMin == null || latSec == null || lonGrad == null || lonMin == null || lonSec == null) {
			LatLon.add("Sin cordenadas GPS");
			LatLon.add("Sin cordenadas GPS");
			return LatLon;
		}
		
		else {
			//****************** Latitud *******************
			//Toma solo lo necesario del string
			String[] partMin = latMin.split("'");
			String[] partSec = latSec.split("\"");
			String[] partGrad = latGrad.split("°");
			
			//Realiza la formula para pasar de GradosMinutosSegundos (GMS) a Grados Decimales (GD)
			Double resultSec= (Double.parseDouble(partMin[0])*60) + (Double.parseDouble(partSec[0].replace(",", ".")));
			Double latGD = (Double.parseDouble(partGrad[0]))+(resultSec/3600); 
	
			//****************** Longitud *******************
			//Toma solo lo necesario del string
			partMin = lonMin.split("'");
			partSec = lonSec.split("\"");
			partGrad = lonGrad.split("°");
			
			//Realiza la formula para pasar de GradosMinutosSegundos (GMS) a Grados Decimales (GD)
			resultSec= (Double.parseDouble(partMin[0])*60) + (Double.parseDouble(partSec[0].replace(",", ".")));		
			Double lonGD = (Double.parseDouble(partGrad[0]))+(resultSec/3600); 
			
			
			if(("N").equals(latRef.toUpperCase())) {
				latGD = (latGD)*(-1);
			}
			if(("O").equals(lonRef.toUpperCase())) {
				latGD = (latGD)*(-1);
			}
			
			String lat = Double.toString(latGD);
			String lon = Double.toString(lonGD);
			LatLon.add(lat);
			LatLon.add(lon);
					
			return LatLon; 
		}
	}

}
