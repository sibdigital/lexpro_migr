SELECT
    GROUPMODULES_GUID,
    GROUPS_GUID,
    MODULES_GUID
FROM
    GROUPMODULES
WHERE GROUPMODULES_GUID BETWEEN :start AND :end