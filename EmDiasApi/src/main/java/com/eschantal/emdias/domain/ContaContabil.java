package com.eschantal.emdias.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbcontacontabil")
public class ContaContabil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nomedaconta")
    @NotNull(message = "Nome da Conta não pode ser nulo ")
    private String nomeConta;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_workspace" , referencedColumnName = "id_workspace"))
    private WorkSpace workSpace;


    @ManyToOne
    @JoinColumns(@JoinColumn(name = "idplanodecontas"))
    private PlanoDeContas planoDeContas;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "idcontasomar"))
    private ContaContabil contaASomar;



}
