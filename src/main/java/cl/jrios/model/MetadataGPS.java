package cl.jrios.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MetadataGPS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String lat_ref;
	private String lat_grad;
	private String lat_min;
	private String lat_sec;
	private String lon_ref;
	private String lon_grad;
	private String lon_min;
	private String lon_sec;

	public MetadataGPS() {
		super();
	}

	public MetadataGPS(Integer id, String lat_ref, String lat_grad, String lat_min, String lat_sec,
			String lon_ref, String lon_grad, String lon_min, String lon_sec) {
		super();
		this.id = id;
		this.lat_ref = lat_ref;
		this.lat_grad = lat_grad;
		this.lat_min = lat_min;
		this.lat_sec = lat_sec;
		this.lon_ref = lon_ref;
		this.lon_grad = lon_grad;
		this.lon_min = lon_min;
		this.lon_sec = lon_sec;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLat_ref() {
		return lat_ref;
	}

	public void setLat_ref(String lat_ref) {
		this.lat_ref = lat_ref;
	}

	public String getLat_grad() {
		return lat_grad;
	}

	public void setLat_grad(String lat_grad) {
		this.lat_grad = lat_grad;
	}

	public String getLat_min() {
		return lat_min;
	}

	public void setLat_min(String lat_min) {
		this.lat_min = lat_min;
	}

	public String getLat_sec() {
		return lat_sec;
	}

	public void setLat_sec(String lat_sec) {
		this.lat_sec = lat_sec;
	}

	public String getLon_ref() {
		return lon_ref;
	}

	public void setLon_ref(String lon_ref) {
		this.lon_ref = lon_ref;
	}

	public String getLon_grad() {
		return lon_grad;
	}

	public void setLon_grad(String lon_grad) {
		this.lon_grad = lon_grad;
	}

	public String getLon_min() {
		return lon_min;
	}

	public void setLon_min(String lon_min) {
		this.lon_min = lon_min;
	}

	public String getLon_sec() {
		return lon_sec;
	}

	public void setLon_sec(String lon_sec) {
		this.lon_sec = lon_sec;
	}

	
}
