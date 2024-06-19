package com.luiz.RecargaAlgar.Controller;

import com.luiz.RecargaAlgar.Entity.Client;
import com.luiz.RecargaAlgar.Service.ClientService;
import com.luiz.RecargaAlgar.dto.ClientDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Client", description = "Controller for Client")
public class ClientController {

    @Autowired
    private ClientService clientService;  // Suponha que este serviço gerencia as operações de clientes


    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return ResponseEntity.ok(new ClientDTO(client));  // Suponha que existe um construtor adequado em ClientDTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);  // Cliente encontrado, retorna 200 OK com o client DTO
        } else {
            return ResponseEntity.notFound().build();  // Cliente não encontrado, retorna 404 Not Found
        }
    }
}

