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

import com.mx.apiRestaurant.model.Meseros;
import com.mx.apiRestaurant.service.MeserosImp;

@RestController
@RequestMapping("MeserosWS")
@CrossOrigin
public class MeserosWS {
    
    @Autowired
    MeserosImp meserosImp;

    // URL: http://localhost:9000/MeserosWS/listar
    @GetMapping("/listar")
    public List<Meseros> listar() {
        return meserosImp.listar();
    }

    // URL: http://localhost:9000/MeserosWS/guardar
    @PostMapping(path="guardar")
    public ResponseEntity<?> guardar(@RequestBody Meseros mesero) {
        String response = meserosImp.guardar(mesero);
        
        if (response.equals("idExistente")) {
            return new ResponseEntity<>("Ese ID ya existe", HttpStatus.OK);
        } else if (response.equals("nombreExistente")) {
            return new ResponseEntity<>("Ese nombre completo ya existe", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mesero, HttpStatus.CREATED);
        }
    }

    // URL: http://localhost:9000/MeserosWS/buscar
    @PostMapping("buscar")
    public ResponseEntity<?> buscar(@RequestBody Meseros mesero) {
        Meseros encontrado = meserosImp.buscar(mesero.getId());
        if (encontrado != null) {
            return new ResponseEntity<>(encontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mesero no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // URL: http://localhost:9000/MeserosWS/editar
    @PostMapping(path="editar")
    public ResponseEntity<?> editar(@RequestBody Meseros mesero) {
        boolean response = meserosImp.editar(mesero);
        
        if (response) {
            return new ResponseEntity<>(mesero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ID de mesero no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // URL: http://localhost:9000/MeserosWS/eliminar
    @PostMapping(path="eliminar")
    public ResponseEntity<?> eliminar(@RequestBody Meseros mesero) {
        boolean response = meserosImp.eliminar(mesero.getId());
        
        if (response) {
            return new ResponseEntity<>("Mesero eliminado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ID de mesero no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

