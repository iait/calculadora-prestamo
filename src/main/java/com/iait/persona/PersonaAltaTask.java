package com.iait.persona;

import com.iait.entities.PersonaEntity;
import com.iait.interfaces.Persona;
import com.iait.services.PersonaService;
import com.iait.tasks.PersonaTask;

public class PersonaAltaTask implements PersonaTask<PersonaEntity> {

    private PersonaService personaService;
    private Persona persona;
    
    private PersonaAltaTask() {
    }
    
    public static PersonaAltaTask newInstance() {
        return new PersonaAltaTask();
    }
    
    public PersonaAltaTask setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
        return this;
    }
    
    public PersonaAltaTask setPersona(Persona persona) {
        this.persona = persona;
        return this;
    }
    
    @Override
    public PersonaEntity execute() {
        return personaService.save(persona);
    }

}
