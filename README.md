<table>
 <tr>
    <td width="100px"><img src="https://github.com/OctavioBernalGH/BTC_Reus2022_UD16/blob/main/dou_logo.png" alt="Team DOU"/></td>
  <td width="1000px"> <h2> Spring + JPA + H2 + Maven Ejercicio 4 Unidad 26 </h2> </td>
  
 </tr>
</table>

[![Java](https://img.shields.io/badge/Java-FrontEnd-informational)]()
[![JBuilder](https://img.shields.io/badge/JBuilder-View-critical)]()
[![JUnit 5](https://img.shields.io/badge/JUnit%205-Testing-success)]()
[![GitHub](https://img.shields.io/badge/GitHub-Repository-lightgrey)]()
[![SQL](https://img.shields.io/badge/SQL-DataBase-yellowgreen)]()
[![Spring](https://img.shields.io/badge/Spring-infrastructure-brightgreen)]()
[![Maven](https://img.shields.io/badge/Maven-ProjectStructure-blueviolet)]()

Este ejercicio ha sido realizado por los miembros del equipo 1. Dicho equipo esta formado por:

  [- Ixabel Justo Etxeberria](https://github.com/Kay-Nicte)<br>
  [- J.Oriol López Bosch](https://github.com/mednologic)<br>
  [- Octavio Bernal](https://github.com/OctavioBernalGH)<br>
  [- David Dalmau](https://github.com/DavidDalmauDieguez)

<p align="justify">Se crea un proyecto Maven utilizando la tecnología spring, se definen como componentes los spring services, la base de datos H2 y JPA. Se crea la estructura de proyecto en capas definiendo los paquetes de controllers, dao, dto y services. Para proseguir se crean las entidades 'facultad', 'Equipos', 'Investigadores' y 'Reservas' con una relación de uno a muchos (one to many). Se definen las columnas y mediante anotaciones se mapea con los atributos de la entidad.</p>

<p align="justify">Antes de proceder con la creación de paquetes y estructuras se definirán los parámetros de acceso a la base de datos MYSQL, para ello se añaden las siguiente línas en el fichero 'application.propierties' ubicado en 'src/main/resources/.</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/103035621/169700491-41d000c8-495c-4212-8570-200d49e1d857.png">
</p>
 
 La estructura de proyecto generada será la siguiente:
 
<p align="center">
  <img src="https://user-images.githubusercontent.com/103035621/169700570-4f7bff69-7bf7-404f-b13a-d621cad7a47e.png">
</p>

Para comenzar se deberá crear el modelo relacional de las tablas, una vez realizado se procederá a generar el código SQL necesario para la creación de tablas y inserts de diferentes datos para comprobar la integridad referencial:

<details>
 
 <summary>Código SQL generado</summary>
 
 <br>

```sql
DROP DATABASE  IF EXISTS `UD26_Ejercicio_4`;

CREATE DATABASE `UD26_Ejercicio_4`;

USE `UD26_Ejercicio_4`;

CREATE TABLE `facultad` (
    `id` int auto_increment,
    `nombre` VARCHAR(100),
    PRIMARY KEY (`id`)
);

create table `investigadores`
(
	`id` int auto_increment,
    `nom_apels` varchar(100),
    `dni` varchar(100),
    `fk_facultad` int,
    primary key (`id`),
    foreign key (`fk_facultad`) references `facultad`(`id`)
);

create table `equipos`
(
	`id` int auto_increment,
	`num_serie` varchar(100),
    `nombre` varchar(100),
    `fk_facultad` int,
    primary key (`id`),
    foreign key (`fk_facultad`) references `facultad`(`id`)
);

create table `reservas`
(
	`id` int auto_increment,
    `comienzo` date,
    `fin` date,
    `fk_id_investigador` int,
    `fk_id_equipo` int,
    primary key (`id`),
    foreign key (`fk_id_investigador`) references `investigadores`(`id`),
    foreign key (`fk_id_equipo`) references `equipos`(`id`)
);

-- INSERT FACULTAD --
insert into `facultad`(`nombre`) values ('Facultad Doctor Jose');
insert into `facultad`(`nombre`) values ('Facultad Doctor Casa');
insert into `facultad`(`nombre`) values ('Facultad San Roberto');
insert into `facultad`(`nombre`) values ('Facultad Pedro Pedrote');
insert into `facultad`(`nombre`) values ('Facultad Pepe pepe');

-- INVESTIGADORES --
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000X', 1);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Daviduvi' , '0000A', 2);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000B', 3);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000C', 4);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000D', 5);

-- EQUIPOS --
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S01', 'Equipo A', 1);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S02', 'Equipo B', 2);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S03', 'Equipo C', 3);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S04', 'Equipo D', 4);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S05', 'Equipo E', 5);

-- RESERVAS --
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-10-10', '2023-10-10', 1, 5);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-09-10', '2023-04-04', 2, 4);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-08-10', '2023-05-05', 3, 3);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-07-10', '2023-08-07', 4, 2);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-06-10', '2023-09-09', 5, 1);
```

 </details>
 
A continuación se procederá a crear las diferentes entidades, en este caso son 4 entidades, facultad, investigadores, equipos y reservas con sus relaciones entre ellas. En el siguiente fragmento de código se podrá observar la clase facultad generada:

<details>
 
 <summary>Código entidad Facultad</summary>
 
 <br>
 
```java
package com.crud.spring.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/** Se define como una entidad y se relaciona con una tabla llamada facultad */
@Entity
@Table(name = "facultad")

public class Facultad {

	/**
	 * Se define el campo ID y se mapea con el atributo código indicando que es la
	 * clave primaria de la entidad. El generationType indica la forma de
	 * incremento, si en el mysql viene definido como auto_incremental se definirá
	 * como incremental automático aquí.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Relación de uno a muchos con la tabla investigadores, esta relación se
	 * realiza mediante la columna codigo (identificador de esta entidad).
	 */
	@OneToMany
	@JoinColumn(name = "id")
	private List<Investigadores> investigadores;

	/**
	 * Relación de uno a muchos con la tabla equipos, esta relación se realiza
	 * mediante la columna codigo (identificador de esta entidad).
	 */
	@OneToMany
	@JoinColumn(name = "id")
	private List<Equipos> equipos;

	/** Constructor vacío */
	public Facultad() {
	}



	/**
	 * @param id
	 * @param nombre
	 * @param investigadores
	 * @param equipos
	 */
	public Facultad(Long id, String nombre, List<Investigadores> investigadores, List<Equipos> equipos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.investigadores = investigadores;
		this.equipos = equipos;
	}



	/**
	 * @return the codigo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Mediante Json ignore eliminamos posibles bucles infinitos debido a la
	 * recursividad.
	 * 
	 * @return the investigadores
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Investigadores")
	public List<Investigadores> getInvestigadores() {
		return investigadores;
	}

	/**
	 * @param investigadores the investigadores to set
	 */
	public void setInvestigadores(List<Investigadores> investigadores) {
		this.investigadores = investigadores;
	}

	/**
	 * Mediante Json ignore eliminamos posibles bucles infinitos debido a la
	 * recursividad.
	 * 
	 * @return the equipos
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Equipos")
	public List<Equipos> getEquipos() {
		return equipos;
	}

	/**
	 * @param equipos the equipos to set
	 */
	public void setEquipos(List<Equipos> equipos) {
		this.equipos = equipos;
	}

}

```

 </details>
 
 <details>
 
 <summary>Código entidad Investigadores</summary>
 
 <br>
```java
package com.crud.spring.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Se define como una entidad y se relaciona con una tabla llamada
 * investigadores
 */
@Entity
@Table(name = "investigadores")
public class Investigadores {

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo nombreApels
	 * en la clase actual.
	 */

	@Column(name = "nom_apels")
	private String nomApels;

	/**
	 * Se define que la columna nombre_apels hace referencia al atributo dni en la
	 * clase actual.
	 */

	@Column(name = "dni")
	private String dni;
	/**
	 * Se genera una relación many to one con la tabla facultad mediante la columna
	 * de la clave foranea fk_facultad.
	 */

	@ManyToOne
	@JoinColumn(name = "fk_facultad")
	private Facultad facultad;

	/**
	 * Se genera una relación one to many con la tabla reserva teniendo como
	 * referencia el identificador de esta clase.
	 */

	@OneToMany
	@JoinColumn(name = "dni")
	private List<Reservas> reservas;

	/** Constructor vacío */
	public Investigadores() {
	}

	
	/**
	 * @param id
	 * @param nomApels
	 * @param dni
	 * @param facultad
	 * @param reservas
	 */
	public Investigadores(Long id, String nomApels, String dni, Facultad facultad, List<Reservas> reservas) {
		super();
		this.id = id;
		this.nomApels = nomApels;
		this.dni = dni;
		this.facultad = facultad;
		this.reservas = reservas;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}


	/**
	 * @return the facultad
	 */
	public Facultad getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad the facultad to set
	 */
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	/**
	 * En el getter relacional del One To Many se introduce la anotación JsonIgnore
	 * para evitar la recursividad. Mediante esta anotación evitamos posibles
	 * bucles.
	 * 
	 * @return the reservas
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Reservas")
	public List<Reservas> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the nomApels
	 */
	public String getNomApels() {
		return nomApels;
	}


	/**
	 * @param nomApels the nomApels to set
	 */
	public void setNomApels(String nomApels) {
		this.nomApels = nomApels;
	}
	
}

```
  
 </details>

<details>
 
 <summary>Código entidad Equipo</summary>
 
 <br>  
  
```java
package com.crud.spring.dto;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/** Se define como una entidad y se relaciona con una tabla llamada equipos */
@Entity
@Table(name = "equipos")
public class Equipos {

	/** Se define que el campo Id hará referencia al atributo numSerie */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Se define que la columna nombre hace referencia al atributo nombre en la
	 * clase actual.
	 */
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "num_serie")
	private String numSerie;
	
	/**
	 * Se genera una relación many to one con la tabla facultad mediante la columna
	 * de la clave foranea fk_facultad.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_facultad")
	private Facultad facultad;

	/**
	 * Se genera una relación one to many con la tabla reserva teniendo como
	 * referencia el identificador de esta clase.
	 */
	@OneToMany
	@JoinColumn(name = "id")
	private List<Reservas> reservas;

	/** Constructor vacío */
	public Equipos() {
	}



	/**
	 * @param id
	 * @param nombre
	 * @param numSerie
	 * @param facultad
	 * @param reservas
	 */
	public Equipos(Long id, String nombre, String numSerie, Facultad facultad, List<Reservas> reservas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numSerie = numSerie;
		this.facultad = facultad;
		this.reservas = reservas;
	}



	/**
	 * @return the numSerie
	 */
	public String getNumSerie() {
		return numSerie;
	}

	/**
	 * @param numSerie the numSerie to set
	 */
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the facultad
	 */
	public Facultad getFacultad() {
		return facultad;
	}

	/**
	 * @param facultad the facultad to set
	 */
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	/**
	 * Mediante JsonIgnore eliminamos la recursividad para evitar generar bucles
	 * infinitos
	 * 
	 * @return
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Reservas")
	public List<Reservas> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}


```
 
</details>

 <details>
 
 <summary>Código entidad Reservas</summary>
 
 <br>
  
```java
package com.crud.spring.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Se define como una entidad y se relaciona con una tabla llamada reservas */
@Entity
@Table(name = "reservas")
public class Reservas{

	/** Se define que el campo Id hará referencia al atributo dni */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Se define que la columna comienzo hace referencia al atributo dni en la
	 * clase actual.
	 */

	@Column(name = "comienzo")
	private Date comienzo;

	/**
	 * Se define que la columna fin hace referencia al atributo fin en la clase
	 * actual.
	 */
	@Column(name = "fin")
	private Date fin;

	/**
	 * Se genera una relación many to one con la tabla investigadores mediante la
	 * columna de la clave foranea fk_investigadores.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_id_investigador")
	private Investigadores investigadores;

	/**
	 * Se genera una relación many to one con la tabla equipos mediante la columna
	 * de la clave foranea fk_equipos.
	 */
	@ManyToOne
	@JoinColumn(name = "fk_id_equipo")
	private Equipos equipos;

	/** Constructor vacío */
	public Reservas() {
	}

	/**
	 * @param id
	 * @param comienzo
	 * @param fin
	 * @param investigadores
	 * @param equipos
	 */
	public Reservas(Long id, Date comienzo, Date fin, Investigadores investigadores, Equipos equipos) {
		super();
		this.id = id;
		this.comienzo = comienzo;
		this.fin = fin;
		this.investigadores = investigadores;
		this.equipos = equipos;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the comienzo
	 */
	public Date getComienzo() {
		return comienzo;
	}

	/**
	 * @param comienzo the comienzo to set
	 */
	public void setComienzo(Date comienzo) {
		this.comienzo = comienzo;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return the investigadores
	 */
	public Investigadores getInvestigadores() {
		return investigadores;
	}

	/**
	 * @param investigadores the investigadores to set
	 */
	public void setInvestigadores(Investigadores investigadores) {
		this.investigadores = investigadores;
	}

	/**
	 * @return the equipos
	 */
	public Equipos getEquipos() {
		return equipos;
	}

	/**
	 * @param equipos the equipos to set
	 */
	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}

}

```

 </details>
  
Ahora para continuar se generarán las interfaces DAO que heredarán la clase JpaRepository con los métodos propios de Jpa, dichos métodos son los CRUD básicos para la base de datos. En el siguiente fragmento de código están insertadas las diferentes interaces:

<details>
 
 <summary>Código capa DAO</summary>

 <br>
 
```java
package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Facultad;

@Repository
public interface FacultadDAO extends JpaRepository<Facultad, Long>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * Integer como parámetro para la entidad Facultad.
	 */
}


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Equipos;

@Repository
public interface EquiposDAO extends JpaRepository<Equipos, Long> {

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * String como parámetro para la entidad Equipos.
	 */
}

package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Investigadores;

@Repository
public interface InvestigadoresDAO extends JpaRepository<Investigadores, Long>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * String como parámetro para la entidad Investigadores.
	 */
}

package com.crud.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.spring.dto.Reservas;

@Repository
public interface ReservasDAO extends JpaRepository<Reservas, Long>{

	/**
	 * Se heredan los métodos CRUD básicos de la clase JpaRepository se utiliza un
	 * String como parámetro para la entidad Reservas.
	 */
}

```
 
</details>
	
Cuando esté finalizada la capa DTO y DAO se procederá a crear las diferentes interfaces services las cuales se implementarán en las clases que forman la capa service. Al ser poco código se mostrará todo junto en el siguiente spoiler:
	
<details>
	
  <summary>Interfaces capa Service</summary>
	
  <br>

  ```java
	package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Facultad;

/** Interfaz con la plantilla de métodos */
public interface FacultadServices {

	/** Método para listar TODAS las facultades */
	public List<Facultad> listarFacultades();

	/** Método para buscar una facultad por ID */
	public Facultad buscarFacultadXIdentificador(Long id);

	/** Método para crear una nueva facultad */
	public Facultad crearFacultad(Facultad facultad);

	/** Método para modificar una facultad ya existente */
	public Facultad modificarFacultadExistente(Facultad facultad);

	/** Método para eliminar una facultad ya existente */
	public void eliminarFacultadExistente(Long id);
}

	
	package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Equipos;

/** Interfaz con la plantilla de métodos */
public interface EquiposServices {

	/** Método para listar TODOS los equipos */
	public List<Equipos> listarEquipos();

	/** Método para buscar un equipo por ID */
	public Equipos buscarEquipoXIdentificador(Long id);

	/** Método para crear un nuevo equipo */
	public Equipos crearNuevoEquipo(Equipos equipos);

	/** Método para modificar un equipo ya existente */
	public Equipos modificarEquipoExistente(Equipos equipos);

	/** Método para eliminar un equipo ya existente */
	public void eliminarEquipoExistente(Long id);

}

	
	package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Investigadores;

/** Interfaz con la plantilla de métodos */
public interface InvestigadoresServices {

	/** Método para listar TODOS los investigadores */
	public List<Investigadores> listarInvestigadores();

	/** Método para buscar un investigador por ID */
	public Investigadores buscarInvestigadorXIdentificador(Long id);

	/** Método para crear un investigador */
	public Investigadores crearNuevoInvestigador(Investigadores investigadores);

	/** Método para modificar un investigador ya existenten */
	public Investigadores modificarInvestigadorExistente(Investigadores investigadores);

	/** Método para eliminar un investigador ya existente */
	public void eliminarInvestigadorExistente(Long id);
}
	
	
	package com.crud.spring.services;

import java.util.List;
import com.crud.spring.dto.Reservas;

/** Interfaz con la plantilla de métodos */
public interface ReservasServices {

	/** Método para listas TODAS las reservas */
	public List<Reservas> listasReservas();

	/** Método para buscar una reserva por ID */
	public Reservas buscarReservaXIdentificador(Long id);

	/** Método para crear una nueva reserva */
	public Reservas crearNuevaReserva(Reservas reservas);

	/** Método para modificar una reserva existente */
	public Reservas modificarReservaExistente(Reservas reservas);

	/** Método para eliminar una reserva existente */
	public void eliminarReservaExistente(Long id);

}
  ```
	
</details>

A continuación se procederá a generar el código de las diferentes clases de la capa service, en este caso serán 4 clases que corresponden a las dierentes entidades:
	
<details>
	
   <summary>Códigio generado en FacultadServicesImpl</summary>
	
   <br>
	
  ```java
package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.ReservasDAO;
import com.crud.spring.dto.Reservas;

@Service
/**
 * Se implementan los métodos de la interfaz @FacultadServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class ReservasServicesImpl implements ReservasServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @ReservasDAO.
	 */
	@Autowired
	ReservasDAO reservasDAO;

	@Override
	public List<Reservas> listasReservas() {
		// Listar todas las reservas.
		return reservasDAO.findAll();
	}

	@Override
	public Reservas buscarReservaXIdentificador(Long id) {
		// Buscar reserva de equipo.
		return reservasDAO.findById(id).get();
	}

	@Override
	public Reservas crearNuevaReserva(Reservas reservas) {
		// Crear una nueva reserva.
		return reservasDAO.save(reservas);
	}

	@Override
	public Reservas modificarReservaExistente(Reservas reservas) {
		// Modificar una reserva existente.
		return reservasDAO.save(reservas);
	}

	@Override
	public void eliminarReservaExistente(Long id) {
		// Eliminar reserva existente
		reservasDAO.deleteById(id);
		
	}

}
	
  ```
	
</details>

	
	
	
<details>
	
   <summary>Códigio generado en InvestigadoresServicesImpl</summary>
	
   <br>
	
  ```java
package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.InvestigadoresDAO;
import com.crud.spring.dto.Investigadores;

@Service
/**
 * Se implementan los métodos de la interfaz @InvestigadoresServices. Se utiliza
 * la anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class InvestigadoresServicesImpl implements InvestigadoresServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @InvestigadoresDAO.
	 */
	@Autowired
	InvestigadoresDAO investigadoresDAO;

	@Override
	public List<Investigadores> listarInvestigadores() {
		// Listar todos los investigadores.
		return investigadoresDAO.findAll();
	}

	@Override
	public Investigadores buscarInvestigadorXIdentificador(Long id) {
		// Buscar investigador por identificador.
		return investigadoresDAO.findById(id).get();
	}

	@Override
	public Investigadores crearNuevoInvestigador(Investigadores investigadores) {
		// Crear un nuevo investigador.
		return investigadoresDAO.save(investigadores);
	}

	@Override
	public Investigadores modificarInvestigadorExistente(Investigadores investigadores) {
		// Modificar un investigador existente.
		return investigadoresDAO.save(investigadores);
	}

	@Override
	public void eliminarInvestigadorExistente(Long id) {
		// Eliminar un investigador existente.
		investigadoresDAO.deleteById(id);
		System.out.println("Se ha eliminado el investigador satisfactoriamente");

	}

}
	
  ```
	
</details>
	
	
<details>
	
   <summary>Códigio generado en EquiposServicesImpl</summary>
	
   <br>
	
  ```java
package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.EquiposDAO;
import com.crud.spring.dto.Equipos;

@Service
/**
 * Se implementan los métodos de la interfaz @EquiposServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class EquiposServicesImpl implements EquiposServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en EquiposDAO.
	 */
	@Autowired
	EquiposDAO equiposDAO;

	@Override
	public List<Equipos> listarEquipos() {
		// Listar todos los equipos.
		return equiposDAO.findAll();
	}

	@Override
	public Equipos buscarEquipoXIdentificador(Long id) {
		// Buscar equipo por ID.
		return equiposDAO.findById(id).get();
	}

	@Override
	public Equipos crearNuevoEquipo(Equipos equipos) {
		// Crear un nuevo equipo.
		return equiposDAO.save(equipos);
	}

	@Override
	public Equipos modificarEquipoExistente(Equipos equipos) {
		// Modificar equipo existente.
		return equiposDAO.save(equipos);
	}

	@Override
	public void eliminarEquipoExistente(Long id) {
		// Eliminar equipo existente.
		equiposDAO.deleteById(id);
		System.out.println("Se ha eliminado el equipo satisfactoriamente");

	}

}
	
  ```
	
</details>
	
	
<details>
	
   <summary>Códigio generado en ReservasServicesImpl</summary>
	
   <br>
	
  ```java
package com.crud.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.spring.dao.ReservasDAO;
import com.crud.spring.dto.Reservas;

@Service
/**
 * Se implementan los métodos de la interfaz @FacultadServices. Se utiliza la
 * anotación @Service para indicar que esta clase pertenece a la capa de
 * servicios
 */
public class ReservasServicesImpl implements ReservasServices {

	/**
	 * Se utiliza la anotación @Autowired para inyectar las dependencias del
	 * JpaRepository heredado en @ReservasDAO.
	 */
	@Autowired
	ReservasDAO reservasDAO;

	@Override
	public List<Reservas> listasReservas() {
		// Listar todas las reservas.
		return reservasDAO.findAll();
	}

	@Override
	public Reservas buscarReservaXIdentificador(Long id) {
		// Buscar reserva de equipo.
		return reservasDAO.findById(id).get();
	}

	@Override
	public Reservas crearNuevaReserva(Reservas reservas) {
		// Crear una nueva reserva.
		return reservasDAO.save(reservas);
	}

	@Override
	public Reservas modificarReservaExistente(Reservas reservas) {
		// Modificar una reserva existente.
		return reservasDAO.save(reservas);
	}

	@Override
	public void eliminarReservaExistente(Long id) {
		// Eliminar reserva existente
		reservasDAO.deleteById(id);
		
	}
}
	
  ```
	
</details>
