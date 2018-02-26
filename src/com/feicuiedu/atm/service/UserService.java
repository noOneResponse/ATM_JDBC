package com.feicuiedu.atm.service;

import java.sql.SQLException;
import java.util.List;

import com.feicuiedu.atm.core.IService;
import com.feicuiedu.atm.dao.UserDao;
import com.feicuiedu.atm.entity.User;

public class UserService implements IService<User>{
	
	private UserDao userDao;
	
	/**
	 * 判断身份证号是否存在
	 * @param card
	 * @return
	 */
	public boolean hasCard(String card) {
    	Object whereSql = " where  card = ? and userType = ?  ";
    	List<User> list = null;
    	userDao = new UserDao();
    	try {
    		list = userDao.queryUserMySql(new User(),whereSql,card,"1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!(list==null||list.isEmpty())) {
    		return true;
    	}
        return false;
    }
	
	/**
	 * 传入账号判断账号是否存在
	 * @param account
	 * @return 返回boolean
	 */
    public boolean hasAccountOrCard(String account) {
    	Object whereSql = " where userType = ? and account = ? or card = ? ";
    	List<User> list = null;
    	userDao = new UserDao();
    	try {
    		list = userDao.queryUserMySql(new User(),whereSql,"1",account,account);
			//System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!(list==null||list.isEmpty())) {
    		
    		return true;
    	}
        return false;
    }
    /**
     * 查找是否有此锁定账户
     * @param account
     * @return
     */
    public boolean hasLockedAccountOrCard(String account) {
    	Object whereSql = " where userType = ? and account = ? or card = ? ";
    	List<User> list = null;
    	userDao = new UserDao();
    	try {
    		list = userDao.queryUserMySql(new User(),whereSql,"3",account,account);
			//System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!(list==null||list.isEmpty())) {
    		
    		return true;
    	}
        return false;
    }
    /**
     * 传入用户密码返回user对象
     * @param account
     * @param passwd
     * @return
     */
    public User getUserByLoginInfo(String account, String passwd,String userType) {
    	Object whereSql = " where account= ? and passwd = ? and userType = ? or card = ? ";
    	List<User> list = null;
    	userDao = new UserDao();
    	try {
    		list = userDao.queryUserMySql(new User(),whereSql,account,passwd,userType,account);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!(list==null||list.isEmpty())) {
    		return list.get(0);
    	}
        return null;
    }
    
    /**
     * 传入账号或者身份证号返回User对象
     * @param account
     * @return
     */
    public List<User> inputAccountOrCardReturnUser(String account) {
    	
    	Object whereSql = " where account = ? or card = ? ";
    	userDao = new UserDao();
    	List<User> list = null;
    	
    	try {
    		
    		list = userDao.queryUserMySql(new User(),whereSql,account,account);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(list==null||list.isEmpty()) {
    		return null;
    	}
    	
        return list;
    }


    @Override   //id,account,name,amount,gender,card,userType,passwd,birthday,address,remark
    public void create(User user) {
    	userDao = new UserDao();
        try {
        	
            userDao.insertMySql(
            		user,
            		user.getId(),
            		user.getAccount(),
            		user.getName(),
            		user.getAmount(),
            		user.getGender(),
            		user.getCard(),
            		user.getUserType(),
            		user.getPasswd(),
            		user.getBirthday(),
            		user.getAddress(),
            		user.getRemark()
            		);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void modify(User user,Object...objects) {
    	
    	userDao= new UserDao();
    	try {
    		
    		/**
    		 * objects[0] 修改的数据的列名
    		 * objects[1] 修改数据的内容
    		 * objects[2] where条件account or card
    		 */
    		userDao.updateMySql(user, objects);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public List<User> findAll(String str) {
    	
    	Object whereSql = null;
    	List<User> list = null;
    	
    	Object option = null;
    	
    	if("1".equals(str)) {
    		whereSql = " where  userType = ? ";
    		option = "1";
    	} 
    	else if("2".equals(str)) {
    		whereSql = " where  userType = ? ";
    		option = "2";
    	}
    	else if("3".equals(str)) {
    		whereSql = " where  userType = ? ";
    		option = "3";
    	}
    	
    	userDao = new UserDao();
    	try {
    		
    		list = userDao.queryUserMySql(new User(),whereSql,option);
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return list;
       
    }
    /**
     * 查找atm_user里面id的最大值
     * @return
     */
    public int findMaxId() {
    	
    	int maxId = 0;
    	userDao = new UserDao();
   
    	try {
			maxId=userDao.queryMysqlMaxId(new User());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return maxId;
    }
    /**
     *  账户状态 0：管理员 1：正常用户 2：销户 3：锁定
     * @param userTypeIn
     * @return 返回与数据库中对应的中文表达
     */
	public Object inputUserTypeReturnObject(String userTypeIn) {
		
		Object userType = null;
		if("0".equals(userTypeIn)) {
			userType="管理员";
		}
		else if("1".equals(userTypeIn)) {
			userType="普通用户";
		}
		else if("2".equals(userTypeIn)) {
			userType="已销户用户";
		}
		else if("3".equals(userTypeIn)) {
			userType="已锁定用户";
		}
		
		return userType;
	}
	/**
	 * 性别 1:男  2:女
	 * @param genderIn
	 * @return 返回与数据库中对应的中文表达
	 */
	public Object inputGenderReturnObject(int genderIn) {
		
		Object gender = null;
		
		if(1==genderIn) {
			gender="男";
		}
		else if(2==genderIn) {
			gender="女";
		}
		return gender;
	}
   
}
