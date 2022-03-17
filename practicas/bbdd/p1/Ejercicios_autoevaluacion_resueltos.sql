1.-

select * 
from emp;

2.-

select *
from dept;

3.-

select * 
from emp
where job ='CLERK';

4.-

select * 
from emp
where job ='CLERK'
order by ename;

5.-

select * 
from emp
where job in ('CLERK')
order by ename;

6.-

select empno,ename,sal
from emp;

7.-

select dname
from dept;

8.-

select dname
from dept
order by dname;

9.-

select dname
from dept
order by loc;

10.-

select dname
from dept
order by loc desc;

11.-

select ename,job
from emp
order by sal;

12.-

select ename,job
from emp
order by job,sal;

13.-

select ename,job
from emp
order by job desc,sal;

14.-

select sal,comm
from emp
where deptno = 30;

15.-

select sal,comm
from emp
where deptno = 30
order by comm;

16.- 

a)

select comm
from emp;

b)

select distinct comm
from emp;


17.-

select distinct ename,comm
from emp;

18.-

select distinct ename,sal
from emp;

19.-

select distinct comm,deptno
from emp;

20.-

select ename,(sal+1000)
from emp;

21.-

select ename,sal,(sal+1000) as NUEVO_SALARIO
from emp;

22.-

select ename
from emp
where comm > (sal/2);

23.-

select ename
from emp
where comm is null or comm <= sal*0.25;

24.-

select 'Nombre: '|| ename as ENAME, 'Salario: '|| sal as SAL
from emp;

25.-

select empno,sal,comm
from emp
where empno > 7500;

26.-

select *
from emp
where ename like 'J%';

27.-

SELECT sal,comm,sal+comm
FROM emp
WHERE comm is not null
ORDER by empno;

28.-

SELECT sal,comm,sal AS "SALARIO_TOTAL"
FROM emp
WHERE comm is null

29.-

SELECT ename
FROM emp 
WHERE sal>1000 and mgr=7698;

30.-

SELECT ename
FROM emp
WHERE sal<1000 and mgr !=7698;

31.-

SELECT ename, ROUND(comm *100/sal,2) AS "COMISION_EN_%"       
FROM emp
WHERE comm is not null
ORDER BY "COMISION_EN_%";

32.-

SELECT *
FROM emp
WHERE deptno=10 AND ename LIKE '%LA%';

33.-

SELECT *
FROM emp
WHERE mgr is null;

34.-

SELECT dname
FROM dept
WHERE dname!='SALES' AND dname!='RESEARCH'
ORDER BY loc;

35.-
SELECT ename,deptno
FROM emp
WHERE job= 'CLERF' AND sal>800
ORDER BY hiredate;

36.-

SELECT ename,ROUND (sal/comm,2) AS "Salario/comision"
FROM emp
WHERE comm is not null and comm!=0
ORDER BY ename;

37.-

SELECT *
FROM emp
WHERE ename LIKE '_____';

38.-

SELECT *
FROM emp
WHERE ename LIKE '_____%';
   ò LENGTH (ename)>=5;

39.-

SELECT *
FROM emp
WHERE ename LIKE 'A%' AND sal>1000
UNION
SELECT *
FROM emp
WHERE comm is not null AND deptno=30;

40.-

SELECT ename,sal,sal+comm AS 'SUELDO_TOTAL'
FROM emp
WHERE comm is not null
UNION
SELECT ename,sal,sal AS 'SUELDO_TOTAL'
FROM emp
WHERE comm is null
ORDER BY sal, 'SUELDO_TOTAL';

41.-

SELECT ename,sal,comm
FROM emp
WHERE comm is not null AND sal BETWEEN comm/2 AND comm;

42.-

SELECT ename,sal,comm
FROM emp
WHERE comm is not null AND sal< comm/2 OR sal>comm;

43.-

SELECT ename,job
FROM emp
WHERE job LIKE '%MAN' AND ename LIKE 'A%';

44.-

SELECT ename,job
FROM emp
WHERE ename||job LIKE 'A%MAN';

45.-

SELECT ename
FROM emp
WHERE length(ename)<=5;

46.-

SELECT ename,sal+comm AS sal_total_actual,ROUND((sal+comm)*1.06,2)
AS sal_total_proximo,ROUND((sal+comm)*1.06*1.07,2) AS
sal_total_2_anyo,'SI' as tiene_comision
FROM emp
WHERE comm is not null
UNION                                                                                                       SELECT ename,sal,ROUND(sal*1.06,2) AS sal_proximo,ROUND(sal*1.06 *1.07,2) AS sal_2_anyo,'NO' AS tiene_comision
FROM emp
WHERE comm is null;


47.-

SELECT ename,hiredate
FROM emp
WHERE job not in ('salesman');

48.-

SELECT *
FROM emp
WHERE empno=7844 OR empno=7900 OR empno=7521 OR empno=7782 OR
empno=7934 OR empno=7678 OR empno=7369;

49.-

SELECT *
FROM emp
ORDER BY deptno, empno DESC;

50.-

SELECT *
FROM emp
WHERE mgr>empno AND ((sal between 1000 AND 200) OR deptno=30);

51.-

select max(sal),sum(comm),count(empno)
from emp;

52.-

select *
from emp
where sal > (select sal from emp where empno = 7934)
order by sal;

53.-

select ename,job,sal
from emp
where sal >= ANY (select sal from emp where ename = 'ALLEN' OR ename = 'ALLEN');


54.-

select max(ename)
from emp;

55.-

select max(sal),min(sal),max(sal)-min(sal)
from emp;

56.-

select ename,sal,job
from emp
where sal = ALL (select max(sal) from emp) OR sal = ALL (select min(sal) from emp);

select ename,sal,job
from emp
where sal = ANY (select max(sal),min(sal) from emp);


57.-

select dname,deptno,avg(sal)
from emp natural join dept
where sal>900 and sal<5000
group by deptno,dname;/*OJO hay que acordarse de poner el dname*/

select d.dname,d.deptno,avg(sal)
from emp e join dept d on e.deptno = d.deptno
where e.sal>900 and e.sal<5000
group by d.deptno,dname;


select deptno,avg(sal)
from emp
where sal>900 and sal<5000
group by deptno


58.-

select e.ename,d.loc
from emp e join dept d on e.deptno = d.deptno
where LENGTH (d.loc)>5
order by d.loc desc,e.ename;

o cambiando una linea por otra
where d.loc LIKE '______%'

59.-

select *
from emp
where sal >= (select avg(sal) from emp);


60.-

select *
from emp a
where sal > ALL (select sal from emp where deptno = a.deptno AND empno <> a.empno);


61.-

select count(distinct job),count(empno),count(distinct sal),sum(sal)
from emp
where deptno = 30;


62.-

select count(comm)
from emp;

/*para que los muestre, no los cuenta*/
select *
from emp
where comm>=0;

63.-

select count(*)
from emp
where deptno = 20;


64.-

select deptno, count(*)
from emp
group by deptno
having count(*)>3;

66.-

select ename
from emp
where ;


67.-

select *
from emp
where job = ANY (select job from emp where deptno = 30);


68.-

select job,count(*)
from emp
group by job;


69.-

select deptno,sum(sal)
from emp
group by deptno;

70.-




71.-




72.-




73.-




74.-




75.-




76.-

select ename,sal,deptno
from emp a
where sal > ALL (select max(sal) from emp where deptno = a.deptno AND empno <> a.empno);


77.-





78.-





79.-


 



80.-



60

select ename
from emp a
where sal=(select max(sal) from emp e where a.deptno=e.deptno);


64

select deptno,count(ename) 
from emp 
group by deptno
having count (ename)>3;



65

select ename,deptno,job
from emp
where deptno=10 AND (job = ANY (select job from emp e join dept d on e.deptno=d.deptno where d.dname='SALES'));

66

select a.ename 
from emp a
where 0<(select count(ename) from emp e where a.empno=e.mgr);

67

select *
from emp 
 where job = ANY (select job from emp e join dept d on e.deptno=d.deptno where d.loc='CHICAGO');


68

select count(ename),job 
from emp
group by job;

69

select deptno,sum(sal) 
from emp
group by deptno;

70
select *
from dept
where deptno NOT IN (select deptno from emp);


select *
from dept d
where 0= (select count(b.ename) from emp b where d.deptno=b.deptno);


71

select e.ename 
from emp e
where e.deptno NOT IN (select d.mgr from emp d);


72 
select d.dname,count(e.ename),avg(e.sal)*12
from emp e join dept d on e.deptno=d.deptno
group by d.deptno,d.dname;


74
select e.ename 
from emp e join dept d on e.deptno=d.deptno
where (d.loc='DALLAS' OR d.loc='NEW YORK');

75

select ename,(select ename from emp e where e.empno=d.mgr)
from emp d;
 

select e.ename,j.ename 
from emp e  left join emp j on e.mgr=j.empno;


ENAME      ENAME
---------- ----------
FORD       JONES
SCOTT      JONES
JAMES      BLAKE
TURNER     BLAKE
MARTIN     BLAKE
WARD       BLAKE			coge todos los de la izq ya que el king no tiene jefe
ALLEN      BLAKE
MILLER     CLARK
ADAMS      SCOTT
CLARK      KING
BLAKE      KING

ENAME      ENAME
---------- ----------
JONES      KING
SMITH      FORD
KING

76

select ename,sal,dname,empno
from emp a join dept  on a.deptno=dept.deptno
where a.sal=(select max(sal) from emp deptno=a.deptno);


/******************************************/
/******* Notas sobre las practicas ********/
/******************************************/



select max(sal),deptno
from emp
group by deptno
order by max(sal) desc;


select ename,max(sal),deptno
from emp			estaria mal porque el ename no apare en el group
group by deptno
order by max(sal) desc;


select ename,max(sal),deptno
from emp
group by deptno,ename
order by ename,max(sal) desc;


select job,count(*)
from emp
group by job;

select deptno,job,count(*)
from emp
group by job,deptno
order by deptno,job;


--------------------------------
select max(sal),deptno
from emp
group by deptno
having deptno in (10,20);

es equivalente a 

select max(sal),deptno
from emp
where deptno in (10,20)
group by deptno;
---------------------------------

select *
from emp
where sal > (select avg(sal) from emp);


select *
from emp
where sal <> ALL (select sal from emp where deptno = 10);




select ename
from emp a
where (select count(*) from emp where hiredate < a.hiredate)<5;

---------------------------------
select ename,sal,job
from emp a
where sal = any (select max(sal) from emp group by a.job);

deberian ser iguales

select ename,sal,job
from emp
where (sal,job) = any (select max(sal),job from emp group by job);
---------------------------------

/* sentencias con JOIN */

select ename,loc
from emp join dept on emp.deptno = dept.deptno;


select ename,loc,emp.deptno
from emp join dept
on emp.deptno = dept.deptno;
---------------------------------
select ename,loc,d.deptno
from emp e join dept d on e.deptno = d.deptno
where d.loc = 'CHICAGO';

misma forma, mejor hacer la de arriba

select ename,loc,d.deptno
from emp e,dept d
where d.loc = 'CHICAGO' and e.deptno = d.deptno;

IMPORTANTE: No olvidar d poner la condicion
---------------------------------

SELECT a.empno, a.ename, a.sal, a.comm
FROM emp a INNER JOIN emp b
ON a.sal > b.sal
WHERE b.empno = 7844;

select ename,loc,deptno,dname
from emp natural join dept;


/* OUTER JOIN */

select dname, count(*)
from emp right join dept on emp.deptno = dept.deptno
group by dept.deptno,dname;
/*Asi me cuenta las filas y me da un 1 en operations*/

select dname, count(empno)
from emp right join dept on emp.deptno = dept.deptno
group by dept.deptno,dname;
/*Asi me cuenta los empno que son clave primaria y me da un 1 en operations*/

