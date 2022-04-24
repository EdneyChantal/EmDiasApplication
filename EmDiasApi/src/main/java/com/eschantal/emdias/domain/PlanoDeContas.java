package com.eschantal.emdias.domain;


import com.eschantal.emdias.domain.enumeration.TypeStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="tbplanodecontas")
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

public class PlanoDeContas  implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_workspace" , referencedColumnName = "id_workspace"))
    @NotNull(message = "Workspace não pode ser nulo")
    private WorkSpace workSpace;

    @Column
    @NotNull(message = "Descrição do Plano de Contas não pode Ser nulo")
    private String descricao;

    @Column
    @NotNull(message = "Status é obrigatório")
    private TypeStatus status ;

}
