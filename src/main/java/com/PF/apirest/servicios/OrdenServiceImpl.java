package com.PF.apirest.servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PF.apirest.modelo.orden;
import com.PF.apirest.repositories.InterfzOrdenRepository;

@Service
public class OrdenServiceImpl implements InterfzOrdenService {

    @Autowired
    private InterfzOrdenRepository ordenRepository;

    @Override
    public orden save(orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<orden> findAll() {
        return ordenRepository.findAll();    
    }

    public String generarNumeroOrden() {
        String numeroConcatenado = "";
        int numero = 0;

        List<orden> ordenes = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if(ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10 ) {
            numeroConcatenado="0000000"+String.valueOf(numero);
        } else if(numero<100){
            numeroConcatenado ="000000"+String.valueOf(numero);
        } else if(numero<1000){
            numeroConcatenado ="00000"+String.valueOf(numero);
        } else if(numero<10000){
            numeroConcatenado ="0000"+String.valueOf(numero);
        } else if(numero<100000){
            numeroConcatenado ="000"+String.valueOf(numero);
        } else if(numero<1000000){
            numeroConcatenado ="00"+String.valueOf(numero);
        } else if(numero<10000000){
            numeroConcatenado ="0"+String.valueOf(numero);
        } else {
            numeroConcatenado = String.valueOf(numero);
        }


        return numeroConcatenado;
    }

    
}
