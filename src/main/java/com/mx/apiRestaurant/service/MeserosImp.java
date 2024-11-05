package com.mx.apiRestaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.apiRestaurant.dao.MeserosDao;
import com.mx.apiRestaurant.model.Meseros;



@Service
public class MeserosImp {
    
    @Autowired
    MeserosDao meserosDao;
    
    @Transactional(readOnly = true)
    public List<Meseros> listar() {
        return meserosDao.findAll();
    }

    @Transactional
    public String guardar(Meseros mesero) {
        boolean existeId = meserosDao.existsById(mesero.getId());
        boolean existeNombreCompleto = meserosDao.findAll().stream().anyMatch(m -> 
            m.getNombre().equalsIgnoreCase(mesero.getNombre()) &&
            m.getApp().equalsIgnoreCase(mesero.getApp()) &&
            (m.getApm() == null ? mesero.getApm() == null : m.getApm().equalsIgnoreCase(mesero.getApm()))
        );

        if (existeId) {
            return "idExistente";
        } else if (existeNombreCompleto) {
            return "nombreCompletoExistente";
        }

        meserosDao.save(mesero);
        return "guardado";
    }
    
    @Transactional(readOnly = true)
    public Meseros buscar(Long id) {
        return meserosDao.findById(id).orElse(null);
    }
    
    @Transactional
    public boolean editar(Meseros mesero) {
        if (meserosDao.existsById(mesero.getId())) {
            meserosDao.save(mesero);
            return true;
        }
        return false;
    }
    
    @Transactional
    public boolean eliminar(Long id) {
        if (meserosDao.existsById(id)) {
            meserosDao.deleteById(id);
            return true;
        }
        return false;
    }
}

