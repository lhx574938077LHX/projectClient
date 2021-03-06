--创建RESULT_INTEGRAL_TRIGGER触发器
DECLARE t_num NUMBER;
BEGIN
  SELECT COUNT(1) INTO t_num FROM user_triggers WHERE trigger_name='RESULT_INTEGRAL_TRIGGER';
  IF t_num = 1 THEN
    EXECUTE IMMEDIATE 'DROP TRIGGER RESULT_INTEGRAL_TRIGGER';
  END IF;
END;
/
CREATE TRIGGER RESULT_INTEGRAL_TRIGGER
BEFORE INSERT ON SEARCH_RESULT FOR EACH ROW
DECLARE   
REQ_COMPANY_ID NUMBER(12,0);
REQ_COMPANY_CODE VARCHAR(18);

V_BORROW_TYPE NUMBER(2,0);
V_BORROW_STATE NUMBER(2,0);
V_BORROW_AMOUNT NUMBER(2,0);
V_CONTRACT_DATE NUMBER(2,0);
V_LOAN_PERIOD NUMBER(2,0);
V_REPAY_STATE NUMBER(2,0);
V_ARREARS_AMOUNT NUMBER(2,0);
V_INTEGRAL_SUM NUMBER(2,0);
--SELECT * FROM SEARCH_RESULT;
BEGIN 
  V_INTEGRAL_SUM:=0;
  
  SELECT REQ_COMPANY_ID,REQ_COMPANY_CODE INTO REQ_COMPANY_ID,REQ_COMPANY_CODE FROM SEARCH_LOG WHERE ID=:NEW.SEARCH_LOG_ID;
  
  /*
	borrowType;	//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	borrowState;	//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	borrowAmount;	//借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
	contractDate;	//合同日期
	loanPeriod;	//批贷期数
	repayState;	//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
	arrearsAmount;	//欠款金额
  */
  
  IF :NEW.BORROW_TYPE>0 AND :NEW.BORROW_TYPE<=4 THEN V_BORROW_TYPE := 1;  ELSE V_BORROW_TYPE := 0; END IF;
  IF :NEW.BORROW_STATE>0 AND :NEW.BORROW_STATE<=6 THEN V_BORROW_STATE := 1; ELSE V_BORROW_STATE := 0; END IF;
  IF :NEW.BORROW_AMOUNT<>0 THEN V_BORROW_AMOUNT := 1; ELSE V_BORROW_AMOUNT := 0; END IF;
  IF :NEW.CONTRACT_DATE>=0 THEN V_CONTRACT_DATE := 1; ELSE V_CONTRACT_DATE := 0; END IF;
  IF :NEW.LOAN_PERIOD>0 THEN V_LOAN_PERIOD := 1; ELSE V_LOAN_PERIOD := 0; END IF;
  IF :NEW.REPAY_STATE>0 AND :NEW.REPAY_STATE<=9 THEN V_REPAY_STATE := 1; ELSE V_REPAY_STATE := 0; END IF;
  IF :NEW.ARREARS_AMOUNT>0 THEN V_ARREARS_AMOUNT := 1; ELSE V_ARREARS_AMOUNT := 0; END IF;
  
  V_INTEGRAL_SUM := V_BORROW_TYPE+V_BORROW_STATE+V_BORROW_AMOUNT+V_CONTRACT_DATE+V_LOAN_PERIOD+V_REPAY_STATE+V_ARREARS_AMOUNT;
  
  IF :NEW.DATA_TYPE = 1 THEN
    UPDATE COMPANY_INFO SET INTEGRAL=INTEGRAL-V_INTEGRAL_SUM WHERE ID = REQ_COMPANY_ID;
    INSERT INTO INTEGRAL_LOG(ID,REQ_COMPANY_ID,REQ_COMPANY_CODE,RSP_COMPANY_ID,RSP_COMPANY_CODE,SEARCH_LOG_ID,SEARCH_TRX_NO,BORROW_TYPE,BORROW_STATE,BORROW_AMOUNT,CONTRACT_DATE,LOAN_PERIOD,REPAY_STATE,ARREARS_AMOUNT,INTEGRAL_SUM,REC_TIME)
    VALUES (INTEGRAL_LOG_ID_SEQUENCE.NEXTVAL,REQ_COMPANY_ID,REQ_COMPANY_CODE,:NEW.RSP_COMPANY_ID,:NEW.RSP_COMPANY_CODE,:NEW.SEARCH_LOG_ID,:NEW.SEARCH_TRX_NO,V_BORROW_TYPE,V_BORROW_STATE,V_BORROW_AMOUNT,V_CONTRACT_DATE,V_LOAN_PERIOD,V_REPAY_STATE,V_ARREARS_AMOUNT,V_INTEGRAL_SUM,:NEW.REC_TIME); 
  END IF;
  
   IF :NEW.DATA_TYPE = 2 THEN
    UPDATE COMPANY_INFO SET INTEGRAL=INTEGRAL+V_INTEGRAL_SUM WHERE ID = :NEW.RSP_COMPANY_ID;
    UPDATE COMPANY_INFO SET INTEGRAL=INTEGRAL-V_INTEGRAL_SUM WHERE ID = REQ_COMPANY_ID;    
    INSERT INTO INTEGRAL_LOG(ID,REQ_COMPANY_ID,REQ_COMPANY_CODE,RSP_COMPANY_ID,RSP_COMPANY_CODE,SEARCH_LOG_ID,SEARCH_TRX_NO,BORROW_TYPE,BORROW_STATE,BORROW_AMOUNT,CONTRACT_DATE,LOAN_PERIOD,REPAY_STATE,ARREARS_AMOUNT,INTEGRAL_SUM,REC_TIME)
    VALUES (INTEGRAL_LOG_ID_SEQUENCE.NEXTVAL,REQ_COMPANY_ID,REQ_COMPANY_CODE,:NEW.RSP_COMPANY_ID,:NEW.RSP_COMPANY_CODE,:NEW.SEARCH_LOG_ID,:NEW.SEARCH_TRX_NO,V_BORROW_TYPE,V_BORROW_STATE,V_BORROW_AMOUNT,V_CONTRACT_DATE,V_LOAN_PERIOD,V_REPAY_STATE,V_ARREARS_AMOUNT,V_INTEGRAL_SUM,:NEW.REC_TIME); 
  END IF;
END;