package com.tienda.controller;

import com.tienda.domain.Cliente;
import com.tienda.service.ClienteService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/busqueda")
    public String buscar(Model model) {
        var clientes = clienteService.buscarPorApellidos("apellidos");
        model.addAttribute("clientes", clientes);
        return "/cliente/busqueda";
    }

    @GetMapping("/cliente/resultado")
    public String resultado(Model model) {
        var clientes = clienteService.buscarPorApellidos("apellidos");
        model.addAttribute("clientes", clientes);
        return "/cliente/resultado";
    }

    @GetMapping("/cliente/listado")
    public String listado(Model model) {
        var clientes = clienteService.getClientes();

        model.addAttribute("clientes", clientes);
        return "/cliente/listado";
    }

    @GetMapping("/cliente/nuevo")
    public String clienteNuevo(Cliente cliente) {
        return "/cliente/modificar";
    }

    /* @PostMapping("/cliente/busqueda")
    public String clienteBuscar(Cliente cliente) 
    {   
        clienteService.buscarPorApellidos("Contreras Mora");
        return "redirect:/cliente/resultado";
    }*/
    @PostMapping("/cliente/guardar")
    public String clienteGuardar(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/cliente/modificar/{idCliente}")
    public String clienteModificar(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/cliente/modificar";
    }

    @GetMapping("/cliente/eliminar/{idCliente}")
    public String clienteEliminar(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }
}
