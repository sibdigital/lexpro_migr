package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "id_map")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class IdMap {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "ID_MAP_SEQ_GEN", sequenceName = "id_map_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MAP_SEQ_GEN")
    private Long id;
    @Column(name = "entity_name")
    private String entityName;
    @Column(name = "new_id")
    private Long newId;
    @Column(name = "old_id")
    private Long oldId;
}
