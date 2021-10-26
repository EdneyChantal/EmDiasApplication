package com.praticasolucoes.emdias.gateway.security;

import com.praticasolucoes.emdias.gateway.domain.WorkSpace;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserComWorkSpace extends User implements WithWorkSpaces{
    List<WorkSpace> workSpaces;
    public UserComWorkSpace(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        workSpaces = new ArrayList<>();
    }

    public UserComWorkSpace(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        workSpaces = new ArrayList<>();
    }

    @Override
    public List<WorkSpace> getWorksSpaces() {
        return workSpaces;
    }

    @Override
    public void setWorkSpaces(List<WorkSpace> workspaces) {
      this.workSpaces = workspaces;
    }
}
