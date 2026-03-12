/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caso1.service;

import com.caso1.domain.Cliente;
import com.caso1.domain.Producto;
import com.caso1.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jason
 */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    
    @Transactional(readOnly = true)
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Cliente> getCliente(Integer IdCliente){
        return clienteRepository.findById(IdCliente);
    }
}
