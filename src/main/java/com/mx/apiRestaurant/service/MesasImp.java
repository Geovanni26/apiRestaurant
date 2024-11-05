package com.mx.apiRestaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.apiRestaurant.dao.MeserosDao;
import com.mx.apiRestaurant.dao.MesasDao;
import com.mx.apiRestaurant.model.Meseros;
import com.mx.apiRestaurant.model.Mesas;

@Service
public class MesasImp {

    @Autowired
    MesasDao mesasDao;
    
    @Autowired
    MeserosDao meserosDao;

    @Transactional(readOnly = true)
    public List<Mesas> listar() {
        return mesasDao.findAll();
    }

    @Transactional
    public String guardar(Mesas mesa) {
        boolean idExistente = false;
        boolean numMesaExistente = false;
        boolean meseroExiste = false;
        
        // Validar existencia de idMesero
        for (Meseros mesero : meserosDao.findAll()) {
            if (mesero.getId().equals(mesa.getMesero().getId())) {
                meseroExiste = true;
                break;
            }
        }
        
        // Validar que ID y numMesa no se repitan
        for (Mesas m : mesasDao.findAll()) {
            if (m.getId().equals(mesa.getId())) {
                idExistente = true;
                break;
            } else if (m.getNumMesa().equals(mesa.getNumMesa())) {
                numMesaExistente = true;
                break;
            }
        }
        
        if (!meseroExiste) {
            return "idMeseroNoExiste";
        } else if (idExistente) {
            return "idExistente";
        } else if (numMesaExistente) {
            return "numMesaExistente";
        }
        
        mesasDao.save(mesa);
        return "guardado";
    }

    @Transactional(readOnly = true)
    public Mesas buscar(Long id) {
        return mesasDao.findById(id).orElse(null);
    }

    @Transactional
    public String editar(Mesas mesa) {
        boolean mesaExiste = false;
        boolean meseroExiste = false;

        // Validar que el id de la mesa exista
        for (Mesas m : mesasDao.findAll()) {
            if (m.getId().equals(mesa.getId())) {
                mesaExiste = true;
                break;
            }
        }
        
        // Validar que el id del mesero exista
        for (Meseros mesero : meserosDao.findAll()) {
            if (mesero.getId().equals(mesa.getMesero().getId())) {
                meseroExiste = true;
                break;
            }
        }

        if (!mesaExiste) {
            return "idMesaNoExiste";
        }
        if (!meseroExiste) {
            return "idMeseroNoExiste";
        }

        mesasDao.save(mesa);
        return "actualizado";
    }

    @Transactional
    public boolean eliminar(Long id) {
        boolean mesaExiste = false;

        // Verificar si la mesa existe
        for (Mesas m : mesasDao.findAll()) {
            if (m.getId().equals(id)) {
                mesasDao.deleteById(id);
                mesaExiste = true;
                break;
            }
        }

        return mesaExiste;
    }
}

