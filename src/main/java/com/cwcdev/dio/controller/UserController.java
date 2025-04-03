package com.cwcdev.dio.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cwcdev.dio.model.User;
import com.cwcdev.dio.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cria um novo usuário", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        System.out.println("UserController.create() - Recebido: " + userToCreate);

        if (userToCreate != null && userToCreate.getAccount() != null) {
            System.out.println("Conta associada - ID: " + userToCreate.getAccount().getId());
        }

        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();

        return ResponseEntity.created(location).body(userCreated);
    }

    @Operation(summary = "Busca um usuário por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(
            @Parameter(description = "ID do usuário a ser buscado", required = true) 
            @PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Atualiza um usuário por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(
            @Parameter(description = "ID do usuário a ser atualizado", required = true) 
            @PathVariable Long id,
            @RequestBody User userToUpdate) {
        var updatedUser = userService.update(id, userToUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Deleta um usuário por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário a ser deletado", required = true) 
            @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
