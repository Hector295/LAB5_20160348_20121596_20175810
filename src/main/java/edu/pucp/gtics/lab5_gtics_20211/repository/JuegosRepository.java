package edu.pucp.gtics.lab5_gtics_20211.repository;

import edu.pucp.gtics.lab5_gtics_20211.entity.Juegos;
import edu.pucp.gtics.lab5_gtics_20211.entity.JuegosUserDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface JuegosRepository extends JpaRepository<Juegos,Integer> {

    @Query(value = "select j.idjuego, j.nombre, j.descripcion, j.image \n" +
            "from juegos j, juegosxusuario ju \n" +
            "where j.idjuego=ju.idjuego and ju.idusuario=?1", nativeQuery = true)

    List<Juegos> obtenerJuegosPorUser(int idusuario);


     /** Completar */
}
