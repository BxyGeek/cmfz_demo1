package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 接口描述信息 (管理员业务接口)
 *
 * @author : buxiaoyu
 * @date : 2019-07-17 14:14
 * @version: V_1.0.0
 */
public interface AdminService {
    /**
     * 方法描述: (管理员登陆)
     * @param admin 管理员
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */

    Map<String,Object> login(Admin admin, HttpSession session,String enCode);
}
