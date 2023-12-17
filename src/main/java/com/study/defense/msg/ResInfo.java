package com.study.defense.msg;

/**
 * @describe 描述
 * @author 老汪 caixian.wang@ezcharting.cn
 * @date 创建时间：2023年9月12日 下午4:15:03
 * 
 */
public class ResInfo {
	public static final String SUCCESS = "Y";//
	public static final String OUT = "O";// session过期
	public static final String FAIL = "F";// 业务操作失败
	public static final String ERROR = "E";// 系统错误
	
	private Integer code = 20000;
	private String msg;
	private String status = SUCCESS;

	/**
	 * 返回前端的数据
	 */
	private Object res;
	private TokenInfo data = new TokenInfo();
	

	public TokenInfo getData() {
		return data;
	}

	public void setData(TokenInfo data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public ResInfo setCode(Integer code) {
		this.code = code;
		return this;
	}


	public String getStatus() {
		return status;
	}

	public ResInfo setStatus(String status) {
		this.status = status;
		return this;
	}

	

	/**
	 * @return the res
	 */
	public Object getRes() {
		return res;
	}

	/**
	 * @param res
	 *            the res to set
	 */
	public ResInfo setRes(Object res) {
		this.res = res;
		return this;
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
	public ResInfo setMsg(String msg) {
		this.msg = msg;
		return this;
	}

}
