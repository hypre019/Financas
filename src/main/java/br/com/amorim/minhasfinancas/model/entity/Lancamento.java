package br.com.amorim.minhasfinancas.model.entity;


import br.com.amorim.minhasfinancas.model.enums.StatusLancamento;
import br.com.amorim.minhasfinancas.model.enums.TipoLancamento;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Entity
@Table(name = "lancamento", schema = "financas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo")
    private TipoLancamento tipo;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusLancamento status;
}
