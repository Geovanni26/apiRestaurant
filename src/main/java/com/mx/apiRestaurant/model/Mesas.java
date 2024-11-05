package com.mx.apiRestaurant.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 CREATE TABLE MESAS(
 ID NUMBER PRIMARY KEY,
 NUM_MESA NUMBER NOT NULL,
 NUM_SILLAS NUMBER NOT NULL,
 ID_MESERO NUMBER NOT NULL,
 FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
 )
 */

@Entity
@Table(name = "MESAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesas {
    
    @Id
    private Long id;
    
    private Integer numMesa;
    
    private Integer numSillas;
    
    // Llave foránea ID_MESERO
    // Muchos mesas están asignadas a un mesero
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MESERO")
    Meseros mesero;  // Relación con la clase `Mesero`
}

