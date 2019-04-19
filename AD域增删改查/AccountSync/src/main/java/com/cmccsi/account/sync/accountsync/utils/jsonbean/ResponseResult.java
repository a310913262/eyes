package com.cmccsi.account.sync.accountsync.utils.jsonbean;

import java.util.List;

@SuppressWarnings("serial")
public class ResponseResult<T> implements java.io.Serializable{
	
	//返回是否成功
	private boolean success; 
	//返回状态码
	private int num;	
	//返回消息
	private String msg;	
	//返回的数据结果集
	private List<T> rows;
	//返回的数据
	private T object;
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
}
