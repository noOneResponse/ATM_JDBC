package com.feicuiedu.atm.service;

import java.sql.SQLException;
import java.util.List;

import com.feicuiedu.atm.core.IService;
import com.feicuiedu.atm.dao.TradeRecordDao;
import com.feicuiedu.atm.entity.TradeRecord;

public class TradeRecordService implements IService<TradeRecord>{
	
	private TradeRecordDao tradeRecordDao;
	
	@Override
	public void create(TradeRecord tradeRecord) {
		
		tradeRecordDao = new TradeRecordDao();
		try {
			
			tradeRecordDao.insertMySql(
					
					tradeRecord,
					tradeRecord.getId(),
					tradeRecord.getAccount(),
					tradeRecord.getTargetAccount(),
					tradeRecord.getTradeType(),
					tradeRecord.getTradeDate(),
					tradeRecord.getTradeAmount(),
					tradeRecord.getAmount()
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	@Override
    public void modify(TradeRecord tradeRecord,Object...objects) {
    	
    };
    
    @Override
    public List<TradeRecord> findAll(String account){
    	String WhereSql=" where account = ? ";
    	tradeRecordDao = new TradeRecordDao();
    	List<TradeRecord> list = null;
    	
    	try {
    		list = tradeRecordDao.queryUserMySql(new TradeRecord(),WhereSql,account);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    };
    
    @Override
    public int findMaxId() {
    	
    	int maxId = 0;
    	tradeRecordDao = new TradeRecordDao();
   
    	try {
			maxId=tradeRecordDao.queryMysqlMaxId(new TradeRecord());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return maxId;
    }
    /**
     * 输入记账类型返回具体Object
     * @param TradeTypeIn
     * @return
     */
    public Object inpuTradeTypeReturnObject(int TradeTypeIn) {
    	
    	Object TradeType = null;
    	
		if(1==TradeTypeIn) {
			TradeType="存款";
		}
		else if(2==TradeTypeIn) {
			TradeType="取款";
		}
		else if(3==TradeTypeIn) {
			TradeType="转账-支出";
		}
		else if(4==TradeTypeIn) {
			TradeType="转账-收入";
		}
		
		return TradeType;
    }
}
