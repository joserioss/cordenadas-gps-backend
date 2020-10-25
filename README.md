# cordenadas-gps-backend
Backend de servicio rest para subir imágenes y analizar las coordenadas GPS en las que fueron tomadas.
Esta solucion permite ingresar imagenes ya sea desde su front-end en angular (https://github.com/joserioss/cordenadas-gps-frontend) o mediante el uso de postman.
Almacenando estas imagenes en una carpeta local del proyecto y ademas en una base de datos (postgreesql), limitandose el tamaño del archivo que se sube a 10Mb 
y ademas verificando que el archivo que se suba no exista previamente.

## Entidades
### FileModel
Los atributos de este modelo son: 
- nombre
- url
- latitud
- longitud

### MetadataGPS
- latitud en grados
- latitud en minutos
- latitud en segundos
- referencia de latitud
- longitud en grados
- longitud en minutos
- longitud en segundos
- referencia de longitud

Con la clase MetadataGPS y una clase utilitaria llamada Conversor, se realiza el traspaso de datos GMS a GD, los cuales se ingresan a la entidad FileModel

Para el caso de no poder adquirir a los metadatos de la imagen cargada, la latitud y longitud se indicara como: sin cordenadas.
Por otro lado se permite solo el ingreso de las cordenadas GPS en Grado ( double° ), minuto (double') y segundo (double'') 
para poder realizar la conversion correcta de Grados Minutos y Segundos (GMS) a Grados decimales (GD)

Es necesario indicar que se hace uso de la libreria drewnoakes/metadata-extractor (https://github.com/drewnoakes/metadata-extractor)

## servicio REST
Con la url: localhot:8080/files se permite ver todos los archivos cargados previamente, con los valores de la entidad FileModel
