package com.e_com.Dao;

import java.util.Map;

import com.e_com.Domain.UserLogs;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserLogsDto;

/**
 * Title: StockDao.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 16:47:32
 * @version 1.0
 **/

public interface UserLogsDao extends BaseDao<UserLogs>{
	
	UserLogsDto login(UserLogsDto userLogsDto);
	
	UserLogsDto save(UserLogsDto userLogsDto);
	
	PaginatedResponseDto getAllPageUserLogs(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);

}
