-------------------------------------NAV_TREE---------------------------------------
--����
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
--�����ֶα�ע
COMMENT ON COLUMN "NAV_TREE"."ID" IS '��ʶ��';
COMMENT ON COLUMN "NAV_TREE"."PID" IS '��ID';
COMMENT ON COLUMN "NAV_TREE"."OID" IS '��ǩID';
COMMENT ON COLUMN "NAV_TREE"."EXPANDED" IS '�Ƿ�չ��0.false 1.true';
COMMENT ON COLUMN "NAV_TREE"."TEXT" IS '��ǩ��';
COMMENT ON COLUMN "NAV_TREE"."NODE_TYPE" IS '�ڵ����ͣ�0.����Tab.1�����۵�2.��������3.���νڵ�';
COMMENT ON COLUMN "NAV_TREE"."ICONCLS" IS 'ͼ��css';
COMMENT ON COLUMN "NAV_TREE"."SORT" IS '�����ֶ�';
COMMENT ON COLUMN "NAV_TREE"."GUID" IS '�����ڵ�';
COMMENT ON COLUMN "NAV_TREE"."FUNC_VIEW_UUID" IS '������ͼUUID';
--��������
DECLARE s_num NUMBER;
BEGIN
  SELECT COUNT(1) INTO s_num FROM user_sequences WHERE sequence_name= 'NAV_TREE_ID_SEQUENCE';
  IF s_num = 1 THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE NAV_TREE_ID_SEQUENCE';
  END IF;
END;
/
CREATE SEQUENCE NAV_TREE_ID_SEQUENCE
INCREMENT BY 1  --ÿ�����Ӽ���
MINVALUE 1      --��СֵΪ1
NOMAXVALUE      --���������ֵ
START WITH 1    --��1��ʼ
CACHE 10        --����
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
  
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_0/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'����'/*��ǩ��*/,0/*�ڵ�����*/,'icon22_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_A := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'���ݵ���'/*��ǩ��*/,1/*�ڵ�����*/,'icon788_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'���ݹ���'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                             
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'dr'/*��ǩID*/,0/*�Ƿ�չ��*/,'����'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'csysjtj'/*��ǩID*/,0/*�Ƿ�չ��*/,'����Ա����ͳ��'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'�������'/*��ǩ��*/,1/*�ڵ�����*/,'icon916_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                            
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'�������'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'qbrw'/*��ǩID*/,0/*�Ƿ�չ��*/,'ȫ������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'wfprw'/*��ǩID*/,0/*�Ƿ�չ��*/,'δ��������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'yfprw'/*��ǩID*/,0/*�Ƿ�չ��*/,'�ѷ�������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'��Ƭ����'/*��ǩ��*/,1/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                      
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'��Ƭ��ȡ'/*��ǩ��*/,2/*�ڵ�����*/,'icon367_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                    
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'cszp'/*��ǩID*/,0/*�Ƿ�չ��*/,'������Ƭ'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'hkpz'/*��ǩID*/,0/*�Ƿ�չ��*/,'����ƾ֤'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'ͳ�Ʒ���'/*��ǩ��*/,1/*�ڵ�����*/,'icon394_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                               
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'���շ���'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                   
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'cszxfx'/*��ǩID*/,0/*�Ƿ�չ��*/,'����ִ�з���'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'csjgfx'/*��ǩID*/,0/*�Ƿ�չ��*/,'���ս������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'ʵʱͳ��'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                 
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'jrsssj'/*��ǩID*/,0/*�Ƿ�չ��*/,'����ʵʱ����'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'�ؿ�Ӷ�����'/*��ǩ��*/,1/*�ڵ�����*/,'icon395_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                               
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'�ؿ����ݹ���'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                            
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'hksjsh'/*��ǩID*/,0/*�Ƿ�չ��*/,'�ؿ��������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'wtgshsj'/*��ǩID*/,0/*�Ƿ�չ��*/,'δͨ���������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'ytgshsj'/*��ǩID*/,0/*�Ƿ�չ��*/,'��ͨ���������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'�������ݹ���'/*��ǩ��*/,2/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'whssj'/*��ǩID*/,0/*�Ƿ�չ��*/,'δ��������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'yhssj'/*��ǩID*/,0/*�Ƿ�չ��*/,'�Ѻ�������'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_A/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'Ȩ�޹���'/*��ǩ��*/,1/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_B := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                         
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_B/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'Ա������'/*��ǩ��*/,2/*�ڵ�����*/,'icon257_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  PARAM_C := NAV_TREE_ID_SEQUENCE.CURRVAL;                                                                                                                                                                      
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_C/*��ID*/,'tjyycy'/*��ǩID*/,0/*�Ƿ�չ��*/,'�����Ӫ��Ա'/*��ǩ��*/,3/*�ڵ�����*/,''/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
  INSERT INTO NAV_TREE(ID,PID,OID,EXPANDED,TEXT,NODE_TYPE,ICONCLS,SORT,GUID,FUNC_VIEW_UUID) VALUES(NAV_TREE_ID_SEQUENCE.NEXTVAL/*��ʶ��*/,PARAM_0/*��ID*/,''/*��ǩID*/,1/*�Ƿ�չ��*/,'����'/*��ǩ��*/,0/*�ڵ�����*/,'icon538_16x16'/*ͼ��CSS*/,0/*����*/,sys_guid()/*�����ڵ�GUID*/,''/*������ͼGUID*/);
END;