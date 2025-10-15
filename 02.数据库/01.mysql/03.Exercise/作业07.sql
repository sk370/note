SELECT COUNT(*) c,deptno FROM emp GROUP BY deptno HAVING c >= 1

SELECT * FROM emp WHERE sal > (SELECT sal FROM EMP WHERE ename = 'SMITH')

SELECT worker.*,leader.hiredate FROM emp worker, emp AS leader WHERE worker.hiredate > leader.hiredate AND worker.mgr = leader.empno

SELECT emp.*, dname FROM emp RIGHT JOIN dept ON emp.deptno = dept.deptno

SELECT emp.*, dname FROM emp,dept WHERE emp.deptno = dept.deptno AND emp.job='CLERK'

SELECT sal,job FROM emp WHERE sal > 1500 GROUP BY job-- 错误，没有对最低工资进行筛选
SELECT MIN(sal) AS min_sal, job FROM emp GROUP BY job HAVING min_sal>1500

SELECT ename, dname FROM emp, dept WHERE emp.deptno = dept.deptno AND dname ='sales'

SELECT ename, sal, AVG(sal) AS avg_sal FROM emp WHERE sal > avg_sal-- 错误
SELECT ename, sal FROM emp WHERE sal > (SELECT AVG(sal) FROM emp)

SELECT * FROM emp WHERE job = (SELECT job FROM emp WHERE ename = 'SCOTT')

SELECT ename, sal FROM emp WHERE sal > (SELECT MAX(sal) FROM emp WHERE deptno=30)

SELECT COUNT(ename),AVG(sal),FORMAT(AVG(YEAR(NOW())-YEAR(hiredate)),2), deptno FROM emp GROUP BY deptno
SELECT COUNT(ename),AVG(sal),FORMAT(AVG(DATEDIFF(NOW(),hiredate)/365),2), deptno FROM emp GROUP BY deptno

SELECT ename,dname,sal FROM emp,dept WHERE emp.deptno=dept.deptno

SELECT dept.*, temp.人数 FROM dept LEFT JOIN (SELECT COUNT(ename) AS '人数',deptno FROM emp GROUP BY deptno) AS temp ON dept.deptno = temp.deptno

SELECT MIN(sal),job FROM emp GROUP BY job

SELECT MIN(sal),job FROM emp WHERE job = 'MANAGER'

SELECT ename, sal + IFNULL(comm,0) AS total_sal FROM emp ORDER BY total_sal