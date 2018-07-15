package com.hkq.util;

import java.util.List;

import com.hkq.model.User;

/**
 * 用户分页对象
 * 
 * @author hkq
 */

public class UserPaging {
	private int pageSize;			// 分页大小
	/*
	 * 决定分页器
	 */
	private int totalRecord;		// 总记录数
	private int totalPage;			// 可以分多少页
	private int pageNum;			// 当前是那页
	
	/*
	 * 决定分页数据
	 */
	private List<User> data;		// not null, 如果没有数据，data为一个空List
	private boolean hasData;		// data.size() > 0
	private int recordNum;			// data.size()
	
	/**
	 * @param data not null, 如果没有数据，data为一个空List
	 */
	public UserPaging(int totalRecord, int totalPage, int pageSize, int pageNum, List<User> data) {
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.data = data;
		this.hasData = data.size() > 0;
		this.recordNum = data.size();
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public List<User> getData() {
		return data;
	}

	public boolean isHasData() {
		return hasData;
	}

	public int getRecordNum() {
		return recordNum;
	}

	public int getPageNum() {
		return pageNum;
	}	
}