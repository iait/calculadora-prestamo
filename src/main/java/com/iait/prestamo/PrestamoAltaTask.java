package com.iait.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iait.entities.PrestamoEntity;
import com.iait.entities.UsuarioEntity;
import com.iait.interfaces.Prestamo;
import com.iait.services.PrestamoService;
import com.iait.tasks.PrestamoTask;

@Component
@Scope("prototype")
public class PrestamoAltaTask implements PrestamoTask<PrestamoEntity> {
    
    @Autowired private PrestamoService prestamoService;

    private Prestamo prestamo;
    private UsuarioEntity usuario;
    
    public PrestamoAltaTask setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        return this;
    }
    
    public PrestamoAltaTask setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
        return this;
    }
    
    @Override
    public PrestamoEntity execute() {
        return prestamoService.nuevo(prestamo, usuario);
    }

}
