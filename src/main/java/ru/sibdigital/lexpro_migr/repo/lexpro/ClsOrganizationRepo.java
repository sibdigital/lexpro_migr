package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsOrganization;

import java.util.List;

@Repository
public interface ClsOrganizationRepo extends JpaRepository<ClsOrganization, Long>, JpaSpecificationExecutor<ClsOrganization> {

    List<ClsOrganization> findAllByOrderByIdAsc();

    List<ClsOrganization> findAllByIsDeletedAndIsActivatedOrderByIdAsc(Boolean isDeleted, Boolean isActivated);

    @Query(value = "with recursive organizations_from_parents as\n" +
            "       (\n" +
            "           select co.id, co.name, cast('{}' as int[]) as parents, 0 as level\n" +
            "           from cls_organization co\n" +
            "           where co.id_parent is NULL\n" +
            "\n" +
            "           union all\n" +
            "\n" +
            "           select c.id, c.name, p.parents || c.id_parent, p.level+1\n" +
            "           from      organizations_from_parents p\n" +
            "                         join cls_organization c\n" +
            "                              on c.id_parent = p.id\n" +
            "           where not c.id = any(p.parents)\n" +
            "       ),\n" +
            "        organizations_from_children as\n" +
            "       (\n" +
            "           select c.id_parent,\n" +
            "                  cast(json_agg(jsonb_build_object('value', c.name) || jsonb_build_object('id', c.path)) as jsonb) as js\n" +
            "           from organizations_from_parents tree\n" +
            "                    join cls_organization c using(id)\n" +
            "           where tree.level > 0 and not c.id = any(tree.parents)\n" +
            "           group by c.id_parent\n" +
            "\n" +
            "           union all\n" +
            "\n" +
            "           select c.id_parent,\n" +
            "                  jsonb_build_object('value', c.name)\n" +
            "                      || jsonb_build_object('data', tree.js)\n" +
            "                      || jsonb_build_object('id', c.path) as js\n" +
            "\n" +
            "           from organizations_from_children tree\n" +
            "                    join cls_organization c on c.id = tree.id_parent\n" +
            "       )\n" +
            "select jsonb_pretty(jsonb_agg(ofc.js))\n" +
            "from organizations_from_children ofc\n" +
            "where ofc.id_parent IS NULL;", nativeQuery = true)
    String getOrganizationTree();
}
