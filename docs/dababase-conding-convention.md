
# Database Naming Convention

1.  Use plain old English whenever possible.
2.  Consistent letter case -> separate words by underscore.
    *   In Unix-like OS, filenames are usually case-sensitive
    *   MacOS, by default, uses case-insensitive, but case-preserving
    *   FAT32 is not case-sensitive, but are case-preserving
    *   NTFS is internally case-sensitive, but behave as case-insensitive.
    ->  lowercase recommended  

3.  Singular. Both table and column names are singular.
    *   people (not persons), men (not mans), mice (not mouses)
    *   sheep (not sheeps), series (not serieses)
    *   criterion (not criterias or criteriaes)
    *   tomatoes (not tomatos), photos (not photoes)
    *   volcanoes or volcanos are both correct.
    ->  waste of synapses and code  

4.  Single-column primary key should be named `id`.

5.  Constraint name: table name, constraint type, names of columns involved.
    *   `table_pk` for primary key constraints
    *   `table_fk_related_table` for foreign key constraints
    *   `table_unique_column` for unique constraints
    *   `table_check_column` for check constraints
    *   `table_index_column` for index constraints
    *   `table_before_insert_column` for before-insert triggers
    *   `table_after_update_column` for after-update triggers
    *   `table_before_delele_column` for before-delete triggers

