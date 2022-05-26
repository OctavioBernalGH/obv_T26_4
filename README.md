<table>
<tr>
<td width="100px"><img src="https://user-images.githubusercontent.com/103035621/170483040-a88d598b-145b-4903-accb-948ceff05811.png" alt="Team DOU"/></td>
<td width="1100px"> <h2>MSKA: Spring + JPA + MYSQL + Spring Security UD26_Ejercicio-4</h2> </td>

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

<p align="center">
<img src="https://user-images.githubusercontent.com/103035621/169703370-8a0deb8d-8091-43eb-a78b-c0d84a0f25d8.png">
</p>


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
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/169703462-a498ee18-a9c0-414b-9be7-3dcaa2a2bbe4.png">
</p>

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
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/169703564-9aa9b4f7-c0b9-461d-afc8-daba64692184.png">
</p>

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

<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/169703683-abac88bd-70e6-4272-b47f-aa20d058624c.png">	
</p>
	
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

A continuación se procederá a la creación de los controladores, se generará 1 controllador por entidad como mínimo. Es muy importante el uso de anotaciones puesto que mediante ellas se generará el mapeo / enrutamiento con las diferentes funcionalides.
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/169703762-2f153860-4843-4b02-82fb-ef081d5600b6.png">
</p>

<details>
	
<summary>Codigo generado en Facultad Controller</summary>
	
<br>
	
```java
	
package com.crud.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.spring.dto.Facultad;
import com.crud.spring.services.FacultadServicesImpl;

@RestController
@RequestMapping("/api")

public class FacultadController {

/** Utilizamos el autowired para inyectar las dependencias */
@Autowired
FacultadServicesImpl facultadServicesImpl;

/** Listar todas las facultades */
@GetMapping("/facultades")
public List<Facultad> listarFacultades() {
return facultadServicesImpl.listarFacultades();
}

/** Buscar facultad por identificador */
@GetMapping("/facultades/{id}")
public Facultad buscarFacultadXIdentificador(@PathVariable(name = "id") Long id) {
return facultadServicesImpl.buscarFacultadXIdentificador(id);
}

/** Crear una nueva facultad */
@PostMapping("/facultades")
public Facultad crearNuevaFacultad(@RequestBody Facultad facultad) {
return facultadServicesImpl.crearFacultad(facultad);
}

/** Modificar una facultad existente */
@PutMapping("/facultades/{id}")
public Facultad modificarFacultadExistente(@PathVariable(name = "id") Long id,
@RequestBody Facultad facultad) {
/** Instancia de la clase Facultad */
Facultad facultad_a_modificar = new Facultad();
Facultad modificacion = new Facultad();

/** Recuperar una facultad */
facultad_a_modificar = facultadServicesImpl.buscarFacultadXIdentificador(id);

/** Modificar los valores */
facultad_a_modificar.setId(facultad.getId());
facultad_a_modificar.setNombre(facultad.getNombre());
facultad_a_modificar.setEquipos(facultad.getEquipos());
facultad_a_modificar.setInvestigadores(facultad.getInvestigadores());

/** Volcar los nuevos datos */
modificacion = facultadServicesImpl.modificarFacultadExistente(facultad_a_modificar);
		
/** Devolver la nueva factultad ya modificada */
return modificacion;
}

/** Eliminar una facultad existente */
@DeleteMapping("/facultades/{id}")
public void eliminarFacultad(@PathVariable(name = "id") Long id) {
facultadServicesImpl.eliminarFacultadExistente(id);
}

}

```
</details>

<details>

<summary>Codigo generado en Investigadores Controller</summary>

<br>

```java
	
package com.crud.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.spring.dto.Investigadores;
import com.crud.spring.services.InvestigadoresServicesImpl;

@RestController
@RequestMapping("/api")

public class InvestigadoresController {

/** Utilizamos el autowired para inyectar las dependencias */
@Autowired
InvestigadoresServicesImpl investigadoresServicesImpl;

/** Listar todos los investigadores */
@GetMapping("/investigadores")
public List<Investigadores> listarInvestigadores() {
return investigadoresServicesImpl.listarInvestigadores();
}

/** Buscar un investigador por identificador */
@GetMapping("/investigadores/{id}")
public Investigadores buscarInvestigadorXIdentificador(@PathVariable(name = "id") Long id) {
return investigadoresServicesImpl.buscarInvestigadorXIdentificador(id);
}

/** Crear un nuevo investigador */
@PostMapping("/investigadores")
public Investigadores crearNuevoInvestigador(@RequestBody Investigadores investigadores) {
return investigadoresServicesImpl.crearNuevoInvestigador(investigadores);
}

/** Modificar un investigador existente */
@PutMapping("/investigadores/{id}")
public Investigadores modificarInvestigadorExistente(@PathVariable(name = "id") Long id,
@RequestBody Investigadores investigadores) {
/** Instancia de un investigador */
Investigadores investigador_a_modificar = new Investigadores();
Investigadores modificacion = new Investigadores();

/** Recuperar un investigador para modificar */
investigador_a_modificar = investigadoresServicesImpl.buscarInvestigadorXIdentificador(id);

/** Actualizar valores */
investigador_a_modificar.setId(investigadores.getId());
investigador_a_modificar.setDni(investigadores.getDni());
investigador_a_modificar.setFacultad(investigadores.getFacultad());
investigador_a_modificar.setReservas(investigadores.getReservas());
investigador_a_modificar.setNomApels(investigadores.getNomApels());
/** Volcar valores actualizados */
modificacion = investigadoresServicesImpl.modificarInvestigadorExistente(investigador_a_modificar);

/** Devolver el nuevo investigador */
return modificacion;
}

/** Eliminar un investigador existente */
@DeleteMapping("/investigadores/{id}")
public void Investigadores(@PathVariable(name = "id") Long id) {
investigadoresServicesImpl.eliminarInvestigadorExistente(id);
}
}

```
</details>
	
<details>
	
<summary>Codigo generado en Equipos Controller</summary>
	
<br>
	
```java
	
package com.crud.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.spring.dto.Equipos;
import com.crud.spring.services.EquiposServicesImpl;

@RestController
@RequestMapping("/api")

public class EquiposController {

/** Utilizamos el autowired para inyectar las dependencias */
@Autowired
EquiposServicesImpl equiposServicesImpl;

/** Listar equipos */
@GetMapping("equipos")
public List<Equipos> listarEquipos(){
return equiposServicesImpl.listarEquipos();
}

/** Buscar equipo por ID */
@GetMapping("equipos/{id}")
public Equipos buscarEquiposXIdentificador(@PathVariable (name = "id") Long id) {
return equiposServicesImpl.buscarEquipoXIdentificador(id);
}

/** Crear un nuevo equipo */
@PostMapping("/equipos")
public Equipos crearNuevoEquipo(@RequestBody Equipos equipos) {
return equiposServicesImpl.crearNuevoEquipo(equipos);
}

/** Modificar un equipo existente */
@PutMapping("/equipos/{id}")
public Equipos modificarEquipoExistente(@PathVariable (name = "id") Long id, @RequestBody Equipos equipos) {
/** Instancia de la clase equipo */
Equipos equipo_a_modificar = new Equipos();
Equipos modificacion = new Equipos();

/** Recuperar un equipo */
equipo_a_modificar = equiposServicesImpl.buscarEquipoXIdentificador(id);

/** Introducir los nuevos valores */
equipo_a_modificar.setId(equipos.getId());
equipo_a_modificar.setNumSerie(equipos.getNumSerie());
equipo_a_modificar.setNombre(equipos.getNombre());
equipo_a_modificar.setFacultad(equipos.getFacultad());
equipo_a_modificar.setReservas(equipos.getReservas());

/** Volcamos los datos nuevos */
modificacion = equiposServicesImpl.modificarEquipoExistente(equipo_a_modificar);

/** Devolvemos el nuevo equipo */
return modificacion;
}

/** Eliminar un equipo */
@DeleteMapping("/equipos/{id}")
public void eliminarEquipos(@PathVariable (name = "id") Long id) {
equiposServicesImpl.eliminarEquipoExistente(id);
	}
}
	
```
	
</details>
	
<details>
	
<summary>Codigo generado en Reservas Controller</summary>
	
<br>
	
```java
	
package com.crud.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.spring.dto.Reservas;
import com.crud.spring.services.ReservasServicesImpl;

@RestController
@RequestMapping("/api")

public class ReservasController {

	/** Utilizamos el autowired para inyectar las dependencias */
	@Autowired
	ReservasServicesImpl reservasServicesImpl;
	
	/** Listar todas las reservas */
	@GetMapping("/reservas")
	public List<Reservas> listarReservas(){
		return reservasServicesImpl.listasReservas();
	}
	
	/** Buscar una reserva por identificador */
	@GetMapping("/reservas/{id}")
	public Reservas buscarReservaXIdentificador(@PathVariable (name = "id") Long id) {
		return reservasServicesImpl.buscarReservaXIdentificador(id);
	}
	
	/** Crear una nueva reserva */
	@PostMapping("/reservas")
	public Reservas crearNuevaReserva(@RequestBody Reservas reservas) {
		return reservasServicesImpl.crearNuevaReserva(reservas);
	}
	
	/** Modificar una reserva existente */
	@PutMapping("/reservas/{id}")
	public Reservas modificarReservaExistente(@PathVariable (name = "id") Long id , @RequestBody Reservas reservas) {
		/** Instancias de la clase Reservas */
		Reservas reserva_a_modificar = new Reservas();
		Reservas modificacion = new Reservas();
		
		/** Se recoge una reserva */
		reserva_a_modificar = reservasServicesImpl.buscarReservaXIdentificador(id);
		
		/** Se modifican sus valores */
		reserva_a_modificar.setId(reservas.getId());
		reserva_a_modificar.setComienzo(reservas.getComienzo());
		reserva_a_modificar.setFin(reservas.getFin());
		reserva_a_modificar.setInvestigadores(reservas.getInvestigadores());
		reserva_a_modificar.setEquipos(reservas.getEquipos());
		
		/** Se vuelcan los valores actualizados */
		modificacion = reservasServicesImpl.modificarReservaExistente(reserva_a_modificar);
		
		/** Se devuelve la nueva reserva */
		return modificacion;
	}
	
	/** Se elimina una reserva */
	@DeleteMapping("/reservas/{id}")
	public void eliminarReservas(@PathVariable (name = "id") Long id) {
		reservasServicesImpl.eliminarReservaExistente(id);
	}
	
}
	
```
</details>
	
A continuación se ejecutará el aplicativo spring boot y no se mostrará ningún error.
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/169703859-7a704af4-6d0b-4d5c-a591-5d072fb963c3.png">
</p>
	
Se verificará el correcto funcionamiento de aplicativo utilizando postman como gestor de peticiones HTTP, a la hora de probar los difentes métodos habra que seleccionar en el desplegable el tipo de método y en el área de texto introducir la ruta del endpoint de la api. En el siguiente ejemplo se puede observar un ejemplo del método GET:

<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704011-e315a309-bf63-4678-be58-1927d545244a.PNG">
</p>

<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704021-23097277-bf60-45fa-8bf1-8a0f79ec1944.PNG">
</p>

<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704028-88b829aa-cfca-4a17-bc09-d74209c01758.PNG">
</p>

<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704037-f75adbf3-8060-43f0-a40a-d9a3c74a8793.PNG">
</p>

Las siguientes imagenes mostrarán como crear un nuevo investigador, equipo, y facultad. Para realizar dicha acción se utilizará el método HTTP Post seguido de la ruta del endpoint de la api.
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704111-c258316e-fb47-4579-b63a-3c6f043d5e42.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704124-dea68b7c-7fa7-4765-ad38-498eea0e4b4b.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704137-2b4da540-41cb-48ec-9c6f-5ac74af53ac0.PNG">
</p>

A continuación se mostrará como buscar un investigador, equipo, facultad y reservar mediante el identificador propio, para ello se utilizará el método HTTP GET seguido de la ruta del endpoint, y ubicando en la ruta el identificador a filtrar:
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704223-735a2932-5241-4607-a831-41e0fa87d0fa.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704230-db317853-5704-4bfe-b994-ad82ed2c8d78.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704241-bff1765b-b5af-43e7-a60b-8b623b7a5942.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704234-809294f6-7476-4ce9-90e6-e4c757aab32b.PNG">
</p>
	
La siguiente verificación será eliminar un equipo, facultad, investigador y reservas. En las tres primeras imagenes se podrá observar un error al eliminar los elementos debido a la correcta integridad referencial. El único elemento que se podrá eliminar será reservas. El método para eliminar un registro es el HTTP DELETE acompañado de la URI de la api y el identificador del objeto a eliminar.
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704418-bfcd465f-2577-46fa-b95b-9937e6b986ae.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704425-ed284d16-32a5-4130-8e02-7a951c202c1d.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704430-5a9d8456-64ad-4ea9-943f-b1fddb44b203.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704449-7fda35ab-4d62-40c3-a784-64c34a99ad99.PNG">
</p>

Por último se muestran varios ejemplos de modificación de registros, para mostrar esta verificación se ha utilizado las entidades facultad, investigadores y equipo. La sentencia HTTP PUT es la utilizada para modificar registros, acompañada de la URI de la api referente a la eliminación y el identificador del objeto a modificar.
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704606-7b9a1004-1739-4d84-9afb-2b7e8d5c8195.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704616-c22c9cf5-4fdf-4768-8948-5b1e681251f2.PNG">
</p>
	
<p align="center">
   <img src="https://user-images.githubusercontent.com/103035621/169704635-4d9a14a5-1933-4f72-ad95-8201a2bee151.PNG">
</p>
