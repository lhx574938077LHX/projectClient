-------------------------------------NAV_TREE---------------------------------------
--建表
DECLARE t_num NUMBER;
BEGIN
  SELECT COUNT(1) INTO t_num FROM user_tables WHERE table_name='NAV_TREE';
  IF t_num = 1 THEN
    EXECUTE IMMEDIATE 'DROP TABLE "NAV_TREE"';
  END IF;
END;
/
CREATE TABLE "NAV_TREE" 
(	
	"ID" NUMBER(12,0) NOT NULL PRIMARY KEY,
	"PID" NUMBER(12,0) NULL,
	"OID" VARCHAR2(22 BYTE) NULL,
	"EXPANDED" NUMBER(1,0) NULL,
	"TEXT" VARCHAR2(66 BYTE) NULL,
	"NODE_TYPE" NUMBER(1,0) NULL,
	"ICONCLS" VARCHAR2(100 BYTE) NULL,
	"SORT" NUMBER(3,0) NULL,
	"GUID" VARCHAR2(32 BYTE) NOT NULL,
	"FUNC_VIEW_UUID" VARCHAR2(32 BYTE) NULL
);
--设置字段备注
COMMENT ON COLUMN "NAV_TREE"."ID" IS '标识列';
COMMENT ON COLUMN "NAV_TREE"."PID" IS '父ID';
COMMENT ON COLUMN "NAV_TREE"."OID" IS '标签ID';
COMMENT ON COLUMN "NAV_TREE"."EXPANDED" IS '是否展开0.false 1.true';
COMMENT ON COLUMN "NAV_TREE"."TEXT" IS '标签名';
COMMENT ON COLUMN "NAV_TREE"."NODE_TYPE" IS '节点类型：0.导航Tab.1导航折叠2.导航树形3.树形节点';
COMMENT ON COLUMN "NAV_TREE"."ICONCLS" IS '图标css';
COMMENT ON COLUMN "NAV_TREE"."SORT" IS '排序字段';
COMMENT ON COLUMN "NAV_TREE"."GUID" IS '导航节点';
COMMENT ON COLUMN "NAV_TREE"."FUNC_VIEW_UUID" IS '功能视图UUID';
--创建序列
DECLARE s_num NUMBER;
BEGIN
  SELECT COUNT(1) INTO s_num FROM user_sequences WHERE sequence_name= 'NAV_TREE_ID_SEQUENCE';
  IF s_num = 1 THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE NAV_TREE_ID_SEQUENCE';
  END IF;
END;
/
CREATE SEQUENCE NAV_TREE_ID_SEQUENCE
INCREMENT BY 1  --每次增加几个
MINVALUE 1      --最小值为1
NOMAXVALUE      --不限制最大值
START WITH 1    --从1开始
CACHE 10        --缓存
ORDER;
--------------------------------------------------------------------------------------------


DECLARE 
	PARAM_0 NUMBER(12,0);
	PARAM_A NUMBER(12,0);
	PARAM_B NUMBER(12,0);
	PARAM_C NUMBER(12,0);
	PARAM_D NUMBER(12,0);
	PARAM_E NUMBER(12,0);
	PARAM_F NUMBER(12,0);
BEGIN
  PARAM_0 := 0;
  
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_0/*父ID*/,''/*标签ID*/,1/*是否展开*/,'功能'/*标签名*/,0/*节点类型*/,'icon22_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_A := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'数据导入'/*标签名*/,1/*节点类型*/,'icon788_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'数据管理'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'dr'/*标签ID*/,0/*是否展开*/,'导入'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'csysjtj'/*标签ID*/,0/*是否展开*/,'催收员数据统计'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'任务分派'/*标签名*/,1/*节点类型*/,'icon916_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                            
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'任务管理'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'qbrw'/*标签ID*/,0/*是否展开*/,'全部任务'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'wfprw'/*标签ID*/,0/*是否展开*/,'未分配任务'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'yfprw'/*标签ID*/,0/*是否展开*/,'已分配任务'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'照片管理'/*标签名*/,1/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                      
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'照片提取'/*标签名*/,2/*节点类型*/,'icon367_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                    
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'cszp'/*标签ID*/,0/*是否展开*/,'催收照片'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'hkpz'/*标签ID*/,0/*是否展开*/,'还款凭证'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'统计分析'/*标签名*/,1/*节点类型*/,'icon394_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                               
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'催收分析'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                   
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'cszxfx'/*标签ID*/,0/*是否展开*/,'催收执行分析'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'csjgfx'/*标签ID*/,0/*是否展开*/,'催收结果分析'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'实时统计'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                 
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'jrsssj'/*标签ID*/,0/*是否展开*/,'今日实时数据'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'回款佣金管理'/*标签名*/,1/*节点类型*/,'icon395_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                               
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'回款数据管理'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                            
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'hksjsh'/*标签ID*/,0/*是否展开*/,'回款数据审核'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'wtgshsj'/*标签ID*/,0/*是否展开*/,'未通过审核数据'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'ytgshsj'/*标签ID*/,0/*是否展开*/,'已通过审核数据'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'核算数据管理'/*标签名*/,2/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'whssj'/*标签ID*/,0/*是否展开*/,'未核算数据'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'yhssj'/*标签ID*/,0/*是否展开*/,'已核算数据'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_A/*父ID*/,''/*标签ID*/,1/*是否展开*/,'权限管理'/*标签名*/,1/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_B/*父ID*/,''/*标签ID*/,1/*是否展开*/,'员工管理'/*标签名*/,2/*节点类型*/,'icon257_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                                      
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_C/*父ID*/,'tjyycy'/*标签ID*/,0/*是否展开*/,'添加运营成员'/*标签名*/,3/*节点类型*/,''/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*标识列*/,PARAM_0/*父ID*/,''/*标签ID*/,1/*是否展开*/,'帮助'/*标签名*/,0/*节点类型*/,'icon538_16x16'/*图标CSS*/,0/*排序*/,sys_guid()/*导航节点GUID*/,''/*功能视图GUID*/);
END;