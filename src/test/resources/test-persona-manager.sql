CREATE TABLE tipos_documentos (
    id_tipodocumento INT NOT NULL,
    nombre VARCHAR(200) NOT NULL,
    abreviatura VARCHAR(5) NOT NULL,
    validar_como_cuit BOOLEAN NOT NULL,
    PRIMARY KEY (id_tipodocumento)
);
INSERT INTO tipos_documentos VALUES (1, 'DOCUMENTO NACIONAL DE IDENTIDAD', 'DNI', 0);

CREATE TABLE provincias (
    id_provincia INT NOT NULL,
    nombre VARCHAR(200) NOT NULL,
    region CHAR(3) NOT NULL,
    PRIMARY KEY (id_provincia)
);
INSERT INTO provincias VALUES (1, 'SANTA FE', 'PAM');

CREATE TABLE localidades (
    id_localidad INT NOT NULL,
    nombre VARCHAR(300) NOT NULL,
    id_provincia INT NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    PRIMARY KEY (id_localidad),
    FOREIGN KEY (id_provincia) REFERENCES provincias (id_provincia)
);
INSERT INTO localidades VALUES (1, 'ROSARIO', 1, '2000');

CREATE TABLE personas (
    id_tipodocumento INT NOT NULL,
    numero_documento BIGINT NOT NULL,
    nombre_apellido VARCHAR(400) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero CHAR(1) NOT NULL,
    es_argentino BOOLEAN NOT NULL,
    correo_electronico VARCHAR(300),
    foto_cara BINARY NULL,
    id_localidad INT NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    PRIMARY KEY (id_tipodocumento, numero_documento),
    FOREIGN KEY (id_tipodocumento) REFERENCES tipos_documentos (id_tipodocumento),
    FOREIGN KEY (id_localidad) REFERENCES localidades (id_localidad)
);