DESC dept
#SELECT * FROM dept

DESC emp
#SELECT * FROM emp

SELECT dname FROM dept

SELECT ename, (sal + IFNULL(comm, 0)) * 13 AS "年收入" FROM emp
	
SELECT ename, sal FROM emp WHERE sal > 2850
#SELECT ename, sal > 2850 FROM emp --错误的

SELECT ename,sal FROM emp WHERE sal < 1500 OR sal > 2850

SELECT ename,deptno FROM emp WHERE empno=7566

SELECT ename,sal FROM emp WHERE deptno IN(10, 30) AND sal > 1500
SELECT ename,sal FROM emp WHERE (deptno =10 OR deptno = 30) AND sal > 1500

SELECT ename,job FROM emp WHERE mgr IS NULL

SELECT ename,job,hiredate FROM emp WHERE hiredate BETWEEN '1991-02-01' AND '1991-05-01' ORDER BY hiredate
SELECT ename,job,hiredate FROM emp WHERE hiredate >= '1991-02-01' AND hiredate <= '1991-05-01' ORDER BY hiredate

SELECT ename,sal,comm FROM emp WHERE comm IS NOT null ORDER BY sal DESC