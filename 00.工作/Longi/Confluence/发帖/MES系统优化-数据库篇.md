## 一、查询Oracle数据库占用I/O较高的操作（查找问题语句）

**背景**：部分SQL执行占用服务器资源较大，影响用户操作，直接体现为卡顿，查询时间久，从SQL优化的方向考虑，查出占用IO高的语句，针对语句进行功能和SQL的优化，提高查询效率，提升用户满意度。

逻辑读TOP10：
```sql
SELECT
	INST_ID,
	UPPER(username) username,
	--数据库用户  
	SQL_ID,
	--逻辑ID  
	a.buffer_gets buffer_gets,
	--SQL执行获得的内存数据块数量  
	DISK_READS,
	--磁盘资源使用(KB)  
	LAST_LOAD_TIME,
	--执行计划载入library cache库缓存的时间  
	LAST_ACTIVE_TIME,
	--SQL最新一次执行的时间  
	a.executions,
	--监控范围内的执行次数  
	PARSE_CALLS,
	--SQL分析的次数  
	VERSION_COUNT,
	--SQL的版本数  
	loads,
	(ELAPSED_TIME / 1000000) ELAPSED_TIME,
	--监控范围内SQL执行次数的总和时间(单位：s)  
	round(a.buffer_gets / DECODE(a.executions, 0, 1, a.executions), 3) buffer_gets_per_exec,
	--单次执行获得的内存数据块数量  
	round(a.disk_reads / DECODE(a.executions, 0, 1, a.executions), 3) disk_reads_per_exec,
	--单次占用磁盘资源  
(a.ELAPSED_TIME / 1000000 / DECODE(a.executions, 0, 1, a.executions)) ELAPSED_TIME_per_exec,
	--SQL执行单次平均时间(单位：s)  
	client_info,
	--调用事件  
	a.sql_text sql_text,
	--执行的SQL内容  
	substr(a.sql_text, 0, 6) sql_text
	--sql类型  
FROM
	(
	SELECT
		ai.INST_ID,
		ai.buffer_gets,
		ai.DISK_READS,
		ai.executions,
		ai.PARSE_CALLS,
		ai.sql_text,
		ai.parsing_user_id,
		ai.SQL_ID,
		ai.ELAPSED_TIME,
		ai.LAST_LOAD_TIME,
		ai.LAST_ACTIVE_TIME,
		PARSING_SCHEMA_NAME username,
		VERSION_COUNT,
		loads,
		ai.MODULE || '--' || ai.ACTION client_info,
		DENSE_RANK() OVER(ORDER BY ai.buffer_gets DESC) rank_order
	FROM
		gv$sqlarea ai
	WHERE
		buffer_gets > 1000
		AND ai.PARSING_SCHEMA_NAME NOT IN ('SYS', 'SYSTEM', 'PUBLIC', 'MDSYS', 'DBSNMP', 'SCOTT', 'LHR', 'LHR2', 'DB_MONITOR', 'OUTLN', 'MGMT_VIEW', 'FLOWS_FILES', 'ORDSYS', 'EXFSYS', 'WMSYS', 'APPQOSSYS', 'APEX_030200', 'OWBSYS_AUDIT', 'ORDDATA', 'CTXSYS', 'ANONYMOUS', 'SYSMAN', 'XDB', 'ORDPLUGINS', 'OWBSYS', 'SI_INFORMTN_SCHEMA', 'OLAPSYS', 'ORACLE_OCM', 'XS$NULL', 'BI', 'PM', 'MDDATA', 'IX', 'SH', 'DIP', 'OE', 'APEX_PUBLIC_USER', 'HR', 'SPATIAL_CSW_ADMIN_USR', 'SPATIAL_WFS_ADMIN_USR', 'APEX_040200', 'DVSYS', 'LBACSYS', 'GSMADMIN_INTERNAL', 'AUDSYS', 'OJVMSYS', 'SYS$UMF', 'GGSYS', 'DBSFWUSER', 'DVF', 'GSMCATUSER', 'SYSBACKUP', 'REMOTE_SCHEDULER_AGENT', 'GSMUSER', 'SYSRAC', 'SYSKM', 'SYSDG')
		AND ai.SQL_TEXT NOT LIKE '/* SQL Analyze(%' ) a
WHERE
	rank_order <= 10
ORDER BY
	INST_ID,
	a.buffer_gets DESC;  
```

物理读：TOP10
```sql
SELECT
	INST_ID,
	UPPER(username) username,
	SQL_ID,
	a.buffer_gets buffer_gets,
	DISK_READS,
	LAST_LOAD_TIME,
	LAST_ACTIVE_TIME,
	a.executions,
	PARSE_CALLS,
	VERSION_COUNT,
	loads,
	((ELAPSED_TIME / 1000000)) ELAPSED_TIME,
	round(a.buffer_gets / DECODE(a.executions, 0, 1, a.executions), 3) buffer_gets_per_exec,
	round(a.disk_reads / DECODE(a.executions, 0, 1, a.executions), 3) disk_reads_per_exec,
	(a.ELAPSED_TIME / 1000000 / DECODE(a.executions, 0, 1, a.executions)) ELAPSED_TIME_per_exec,
	client_info,
	a.sql_text sql_text,
	substr(a.sql_text, 0, 6) sql_text
FROM
	(
	SELECT
		ai.INST_ID,
		ai.buffer_gets,
		ai.DISK_READS,
		ai.executions,
		ai.PARSE_CALLS,
		ai.sql_text,
		ai.parsing_user_id,
		ai.SQL_ID,
		ai.ELAPSED_TIME,
		ai.LAST_LOAD_TIME,
		ai.LAST_ACTIVE_TIME,
		PARSING_SCHEMA_NAME username,
		VERSION_COUNT,
		loads,
		ai.MODULE || '--' || ai.ACTION client_info,
		DENSE_RANK() OVER(ORDER BY ai.DISK_READS DESC) rank_order
	FROM
		gv$sqlarea ai
	WHERE
		buffer_gets > 1000
		AND ai.PARSING_SCHEMA_NAME NOT IN ('SYS', 'SYSTEM', 'PUBLIC', 'MDSYS', 'DBSNMP', 'SCOTT', 'LHR', 'LHR2', 'DB_MONITOR', 'OUTLN', 'MGMT_VIEW', 'FLOWS_FILES', 'ORDSYS', 'EXFSYS', 'WMSYS', 'APPQOSSYS', 'APEX_030200', 'OWBSYS_AUDIT', 'ORDDATA', 'CTXSYS', 'ANONYMOUS', 'SYSMAN', 'XDB', 'ORDPLUGINS', 'OWBSYS', 'SI_INFORMTN_SCHEMA', 'OLAPSYS', 'ORACLE_OCM', 'XS$NULL', 'BI', 'PM', 'MDDATA', 'IX', 'SH', 'DIP', 'OE', 'APEX_PUBLIC_USER', 'HR', 'SPATIAL_CSW_ADMIN_USR', 'SPATIAL_WFS_ADMIN_USR', 'APEX_040200', 'DVSYS', 'LBACSYS', 'GSMADMIN_INTERNAL', 'AUDSYS', 'OJVMSYS', 'SYS$UMF', 'GGSYS', 'DBSFWUSER', 'DVF', 'GSMCATUSER', 'SYSBACKUP', 'REMOTE_SCHEDULER_AGENT', 'GSMUSER', 'SYSRAC', 'SYSKM', 'SYSDG')
		AND ai.SQL_TEXT NOT LIKE '/* SQL Analyze(%' ) a
WHERE
	rank_order <= 10
ORDER BY
	INST_ID,
	a.DISK_READS DESC;  
```
  
根据上文语句中查出来的SQLID查找具体sql文本 ：
```sql
select * from dba_hist_sqltext where sql_id in ('fq5antz8vwxyk');
```

## 二、关于锁表
  
**背景**:代码没有问题,也在正常运行,跟数据库正常交互后，数据表内数据无变化。排除接口/网络等异常，一切正常的情况下，考虑是否锁表。

查询是否有锁表发生 ：
```sql
select * from v$locked_object a,dba_objects b where b.object_id=a.object_id;
```
 
查询锁表操作的SID和流水号：
```sql
SELECT
	a.OS_USER_NAME,
	c.owner,
	c.object_name,
	b.sid,
	b.serial#,
	to_char(logon_time, 'yyyy-MM-dd hh24:mi:ss')
FROM
	v$locked_object a,
	v$session b,
	dba_objects c
WHERE
	a.session_id = b.sid
	AND a.object_id = c.object_id
ORDER BY
	b.logon_time;
```

数据库级杀锁表进程：
```sql
alter system kill session '1704,7221' immediate; --1704 为锁表操作的SID 7221 为锁表操作的流水号  
```
注：该操作建议联系DBA！！！！！！不要私自操作，正常情况下，我们用的数据库账号是没有该权限的，需要DBA在OS层级来杀。

## 三、查询单表占用多少空间

```sql
SELECT
	t.segment_name,
	t.segment_type,
	sum(t.bytes / 1024 / 1024) "占用空间(M)"
FROM
	dba_segments t
WHERE
	t.segment_type = 'TABLE'
	AND t.segment_name IN (  
'SEC_CX_SORTER_AM_G1',  
'SEC_CX_SORTER_ATW_G2',  
'SEC_CX_SORTER_ATW_G3',  
'SEC_CX_SORTER_HNK',  
'SEC_CX_SORTER_TZ',  
'SEC_SORTER_PULL_HISTORY',  
'SEC_SORTER_ERR_MESSAGE'  
)
GROUP BY
	OWNER,
	t.segment_name,
	t.segment_type;
```

说明：
- `SEC_CX_SORTER_AM_G1`：为要查询占用多少空间的用户表。
- `dba_segments`：ORACLE自带的系统表，该表内存储了用户表的相关信息。