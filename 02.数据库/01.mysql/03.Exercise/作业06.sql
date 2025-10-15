SELECT * FROM emp WHERE deptno = 30

SELECT ename, empno, deptno FROM emp WHERE job = 'CLERK'

SELECT * FROM emp WHERE IFNULL(comm,0)>sal

SELECT * FROM emp WHERE IFNULL(comm,0)>(sal * 0.6)

SELECT * FROM emp WHERE (deptno = 10 AND job = 'MANAGER') OR (deptno = 20 AND job = 'CLERK') 

SELECT * FROM emp WHERE (deptno = 10 AND job = 'MANAGER') OR (deptno = 20 AND job = 'CLERK') OR (sal >= 2000 AND (job <> 'MANAGER' AND job <> 'CLERK'))

SELECT DISTINCT job FROM emp WHERE comm IS NOT null

SELECT * FROM emp WHERE comm IS null OR (IFNULL(comm,0) <= 100)

SELECT * FROM emp WHERE LAST_DAY(hiredate) - 2 = hiredate

SELECT * FROM emp WHERE DATE_ADD(hiredate,INTERVAL 12 YEAR) < NOW()

SELECT CONCAT(LCASE(SUBSTR(ename,1,1)),SUBSTR(ename,2)) FROM emp

SELECT * FROM emp WHERE LENGTH(ename) = 5

SELECT * FROM emp WHERE ename NOT LIKE '%R%'

SELECT SUBSTR(ename,1,3) FROM emp
SELECT LEFT(ename,3) FROM emp

SELECT REPLACE(ename,'A','a') FROM emp

SELECT ename, hiredate FROM emp WHERE DATE_ADD(hiredate,INTERVAL 10 YEAR) < NOW()

SELECT * FROM emp ORDER BY ename;

SELECT ename, hiredate FROM emp ORDER BY hiredate;

SELECT ename, job, sal FROM emp ORDER BY job DESC, sal

SELECT ename, YEAR(hiredate) `year`, MONTH(hiredate) AS `month`, sal FROM emp ORDER BY `month`, `year`

SELECT FLOOR(sal/30) FROM emp

SELECT ename, MONTH(hiredate) FROM emp WHERE MONTH(hiredate) = 2

SELECT ename, DATEDIFF(NOW(),hiredate) FROM emp;

SELECT ename FROM emp WHERE ename LIKE '%A%'

SELECT ename, FLOOR(DATEDIFF(NOW(),hiredate) / 365) AS `year`, FLOOR(DATEDIFF(NOW(),hiredate) % 365 / 30) `month` , FLOOR(DATEDIFF(NOW(),hiredate) % 365 % 30) `day` FROM emp;