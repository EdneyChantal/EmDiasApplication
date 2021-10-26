package com.praticasolucoes.emdias.gateway.security;

import com.praticasolucoes.emdias.gateway.domain.WorkSpace;

import java.util.List;

public interface WithWorkSpaces {

    List<WorkSpace> getWorksSpaces();
    void setWorkSpaces(List<WorkSpace> workspaces);

}
