package com.iait.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.PersonaEntity;
import com.iait.executors.PersonaTaskExecutor;
import com.iait.interfaces.Persona;
import com.iait.services.PersonaService;

@Component
public class PersonaManager {
    
    @Autowired private PersonaService personaService;
    
    @Transactional
    public PersonaEntity nuevo(Persona persona) {
        
        final PersonaTaskExecutor executor = new PersonaTaskExecutor();
        
        PersonaAltaTask personaAltaTask = PersonaAltaTask.newInstance()
                .setPersonaService(personaService)
                .setPersona(persona);
        
        PersonaEntity personaEntity = executor.run(personaAltaTask);
        
        return personaEntity;
    }

}
