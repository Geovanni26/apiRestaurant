package com.mx.apiRestaurant.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.apiRestaurant.model.Mesas;
import com.mx.apiRestaurant.service.MesasImp;

@RestController
@RequestMapping(path="MesasWS")
@CrossOrigin
public class MesasWS {

    @Autowired
    MesasImp mesasImp;

    // URL: http://localhost:9000/MesasWS/listar
    @GetMapping("/listar")
    public List<Mesas> listar() {
        return mesasImp.listar();
    }

    // URL: http://localhost:9000/MesasWS/guardar
    @PostMapping(path = "guardar")
    public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {
        String response = mesasImp.guardar(mesa);

        if (response.equals("idExiste")) {
            return new ResponseEntity<>("No se guardó, ese ID ya existe", HttpStatus.OK);
        } else if (response.equals("numMesaExiste")) {
            return new ResponseEntity<>("No se guardó, ese número de mesa ya existe", HttpStatus.OK);
        } else if (response.equals("idMeseroNoExiste")) {
            return new ResponseEntity<>("No se guardó, el ID del mesero no existe", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mesa, HttpStatus.CREATED);
        }
    }

    // URL: http://localhost:9000/MesasWS/buscar
    @PostMapping(path = "buscar")
    public ResponseEntity<?> buscar(@RequestBody Mesas mesa) {
        Mesas mesaEnc = mesasImp.buscar(mesa.getId());

        if (mesaEnc == null) {
            return new ResponseEntity<>("La mesa no existe", HttpStatus.OK);
        }
        return new ResponseEntity<>(mesaEnc, HttpStatus.OK);
    }

    // URL: http://localhost:9000/MesasWS/editar
    @PostMapping("editar")
    public ResponseEntity<?> editarMesa(@RequestBody Mesas mesa) {
        String response = mesasImp.editar(mesa);

        if (response.equals("idNoExiste")) {
            return new ResponseEntity<>("No se actualizó, el ID de la mesa no existe", HttpStatus.OK);
        } else if (response.equals("actualizado")) {
            return new ResponseEntity<>(mesa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }

    // URL: http://localhost:9000/MesasWS/eliminar
    @PostMapping(path = "eliminar")
    public ResponseEntity<String> eliminar(@RequestBody Mesas mesa) {
        boolean response = mesasImp.eliminar(mesa.getId());

        if (!response) {
            return new ResponseEntity<>("El registro no existe", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Se eliminó con éxito", HttpStatus.OK);
        }
    }
}
