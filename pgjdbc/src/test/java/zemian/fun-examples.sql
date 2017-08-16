-- https://www.postgresql.org/docs/9.6/static/plpgsql-overview.html#PLPGSQL-ARGS-RESULTS


-- case 1: function that returns TABLE (SetOf) records
-- https://www.postgresql.org/docs/9.6/static/queries-table-expressions.html#QUERIES-TABLEFUNCTIONS
CREATE TABLE foo (fooid int, foosubid int, fooname text);

CREATE FUNCTION getfoo(int) RETURNS SETOF foo AS $$
SELECT * FROM foo WHERE fooid = $1;
$$ LANGUAGE SQL;

SELECT * FROM getfoo(1) AS t1;

SELECT * FROM foo
WHERE foosubid IN (
  SELECT foosubid
  FROM getfoo(foo.fooid) z
  WHERE z.fooid = foo.fooid
);

CREATE VIEW vw_getfoo AS SELECT * FROM getfoo(1);

SELECT * FROM vw_getfoo;

-- built in function that returns set
SELECT * FROM generate_series(2,4);


-- case 2: setof int using 'next'
CREATE FUNCTION fun2() RETURNS SETOF int AS $$
begin
  return next 1;
  return next 2;
  return next 3;
end
$$ LANGUAGE plpgsql;

select fun2();

-- examples
-- file:///C:/Users/xbbj5x6/apps/postgresql-9.6.3/doc/postgresql/html/sql-createfunction.html
CREATE FUNCTION add(integer, integer) RETURNS integer
AS 'select $1 + $2;'
LANGUAGE SQL
IMMUTABLE
RETURNS NULL ON NULL INPUT;

CREATE OR REPLACE FUNCTION increment(i integer) RETURNS integer AS $$
BEGIN
  RETURN i + 1;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION dup2(in int, out f1 int, out f2 text)
AS $$ SELECT $1, CAST($1 AS text) || ' is text' $$
LANGUAGE SQL;

SELECT * FROM dup2(42);

CREATE TYPE dup_result AS (f1 int, f2 text);

CREATE FUNCTION dup3(int) RETURNS dup_result
AS $$ SELECT $1, CAST($1 AS text) || ' is text' $$
LANGUAGE SQL;

SELECT * FROM dup3(42);

-- list of functions
SELECT NULL AS FUNCTION_CAT, n.nspname AS FUNCTION_SCHEM,
p.proname AS FUNCTION_NAME, d.description AS REMARKS,
CASE
WHEN (format_type(p.prorettype, null) = 'unknown') THEN 0
WHEN
  (left(pg_get_function_result(p.oid), 5) = 'TABLE') OR
  (left(pg_get_function_result(p.oid), 5) = 'SETOF') THEN 2
ELSE 1
END AS FUNCTION_TYPE,
p.proname || '_' || p.oid AS SPECIFIC_NAME
FROM pg_catalog.pg_proc p
INNER JOIN pg_catalog.pg_namespace n ON (p.pronamespace=n.oid)
LEFT JOIN pg_catalog.pg_description d ON (p.oid=d.objoid)
WHERE pg_function_is_visible(p.oid);


select a.proname, b.nspname from pg_proc a, pg_namespace b where a.pronamespace = b.oid;